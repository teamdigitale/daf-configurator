package it.gov.daf.utils

import javax.inject.Inject
import play.api.{Configuration, Environment}

class AppConfig @Inject()(playConfig: Configuration){

  val vocPath = playConfig.getOptional[String]("voc.path")
  val cookieTtlMin = playConfig.getOptional[Long]("cookie.expiration")
  val credentialTtlMin = playConfig.getOptional[Long]("token.expiration")

}

object ConfigReader {
  private val config = new AppConfig(Configuration.load(Environment.simple()))

  require(config.cookieTtlMin.nonEmpty , "CacheWrapper configuration error: cookies ttl must be set")
  require(config.credentialTtlMin.nonEmpty, "CacheWrapper configuration error: credentials ttl must be set")

  def getVocPath = config.vocPath.getOrElse("")
  def getCookieRtlMin = config.cookieTtlMin.get
  def getCredentialTtlMin = config.credentialTtlMin.get
}
