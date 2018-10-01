package it.gov.daf.common.authentication

import com.google.inject.AbstractModule
import java.security.InvalidParameterException
import java.time.Duration

import org.pac4j.core.client.Clients
import org.pac4j.http.client.direct.{DirectBasicAuthClient, HeaderClient}
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator
import play.api.{Configuration, Environment, Logger}

import org.ldaptive.auth.{Authenticator, BindAuthenticationHandler, SearchDnResolver}
import org.ldaptive.pool._
import org.ldaptive.ssl.SslConfig
import org.ldaptive.{BindConnectionInitializer, ConnectionConfig, Credential, DefaultConnectionFactory}
import org.pac4j.core.config.Config
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration
import org.pac4j.ldap.profile.service.LdapProfileService
import org.pac4j.play.http.DefaultHttpActionAdapter
import org.pac4j.play.scala.{DefaultSecurityComponents, SecurityComponents}
import org.pac4j.play.store.{PlayCacheSessionStore, PlaySessionStore}


class SecurityModuleWeb(environment: Environment, configuration: Configuration) extends AbstractModule {


  private def getLdapAuthenticator = {
    Logger.logger.info("Daf-Configurator 1.0.0-SNAPSHOT")
    val connectionConfig = new ConnectionConfig
    connectionConfig.setConnectTimeout(Duration.ofMillis(configuration.getOptional[Long]("pac4j.ldap.connect_timeout").getOrElse(2000)))
    connectionConfig.setResponseTimeout(Duration.ofMillis(configuration.getOptional[Long]("pac4j.ldap.response_timeout").getOrElse(10000)))
    connectionConfig.setLdapUrl(
      configuration.getOptional[String]("pac4j.ldap.url").getOrElse(throw new InvalidParameterException(s"Missing mandatory parameter pac4j.ldap.url"))
    )
    connectionConfig.setConnectionInitializer(
      new BindConnectionInitializer(configuration.getOptional[String]("pac4j.ldap.bind_dn").
        getOrElse(throw new InvalidParameterException(s"Missing mandatory pac4j.ldap.bind_dn")),
        new Credential(configuration.getOptional[String]("pac4j.ldap.bind_pwd")
          .getOrElse(throw new InvalidParameterException(s"Missing mandatory pac4j.ldap.bind_pwd")))))
    connectionConfig.setUseSSL(true) //TODO Shall we keep SSL mandatory
    val sslConfig = new SslConfig()
    sslConfig.setTrustManagers() //TODO no more certificate validation, shall we keep it in this way?
    connectionConfig.setSslConfig(sslConfig)


    val connectionFactory = new DefaultConnectionFactory
    connectionFactory.setConnectionConfig(connectionConfig)
    val poolConfig = new PoolConfig
    poolConfig.setMinPoolSize(1)
    poolConfig.setMaxPoolSize(2)
    poolConfig.setValidateOnCheckOut(true)
    poolConfig.setValidateOnCheckIn(true)
    poolConfig.setValidatePeriodically(false)
    val searchValidator = new SearchValidator
    val pruneStrategy = new IdlePruneStrategy
    val connectionPool = new BlockingConnectionPool
    connectionPool.setPoolConfig(poolConfig)
    connectionPool.setBlockWaitTime(Duration.ofMillis(1000))
    connectionPool.setValidator(searchValidator)
    connectionPool.setPruneStrategy(pruneStrategy)
    connectionPool.setConnectionFactory(connectionFactory)
    connectionPool.initialize()
    val pooledConnectionFactory = new PooledConnectionFactory
    pooledConnectionFactory.setConnectionPool(connectionPool)

    val handler = new BindAuthenticationHandler( new DefaultConnectionFactory(connectionConfig) )

    val dnResolver = new SearchDnResolver(pooledConnectionFactory)
    dnResolver.setBaseDn( configuration.get[String]("pac4j.ldap.base_user_dn"))
    val usernameAttribute = configuration.get[String]("pac4j.ldap.login_attribute")
    dnResolver.setUserFilter(s"($usernameAttribute={user})")

    val ldaptiveAuthenticator = new Authenticator
    ldaptiveAuthenticator.setDnResolver(dnResolver)
    ldaptiveAuthenticator.setAuthenticationHandler(handler)
    // pac4j:
    val authenticator = new LdapProfileService(connectionFactory, ldaptiveAuthenticator, "dummy")
    authenticator.setAttributes("memberOf")
    authenticator.setUsernameAttribute(configuration.get[String]("pac4j.ldap.username_attribute"))
    authenticator
  }

  override def configure(): Unit = {

    bind(classOf[PlaySessionStore]).to(classOf[PlayCacheSessionStore])

    bind(classOf[SecurityComponents]).to(classOf[DefaultSecurityComponents])

    val authenticatorConf = configuration.getOptional[String]("pac4j.authenticator").getOrElse("ldap")

    val authenticator = authenticatorConf match {
      case "ldap" => getLdapAuthenticator
      case "test" => new SimpleTestUsernamePasswordAuthenticator
      case _ => getLdapAuthenticator
    }

    val directBasicAuthClient = new DirectBasicAuthClient(authenticator)

    val secret: String = configuration.getOptional[String]("pac4j.jwt_secret").fold[String](throw new Exception("missing secret"))(identity)


    val jwtAuthenticator = new JwtAuthenticator()
    jwtAuthenticator.addSignatureConfiguration(new SecretSignatureConfiguration(secret))

    val parameterClient = new HeaderClient("Authorization", "Bearer ", jwtAuthenticator)

    val config = new Config(new Clients(directBasicAuthClient, parameterClient))

    config.setHttpActionAdapter(new DefaultHttpActionAdapter())

    bind(classOf[Config]).toInstance(config)
    bind(classOf[JwtAuthenticator]).toInstance(jwtAuthenticator)

  }

}