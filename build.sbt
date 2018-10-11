import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}
import Versions._


name := "daf-configurator"

version := "1.0.0-SNAPSHOT"

val isStaging = System.getProperty("STAGING") != null

lazy val root = (project in file(".")).enablePlugins(PlayJava, DockerPlugin)

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  guice,
  ehcache, // or cacheApi
  ws,
  filters,
  specs2 % Test,
  "org.pac4j" %% "play-pac4j" % playPac4jVersion,
  "org.pac4j" % "pac4j-http" % pac4jVersion,
  //  "org.pac4j" % "pac4j-cas" % pac4jVersion,
  //  "org.pac4j" % "pac4j-openid" % pac4jVersion exclude("xml-apis" , "xml-apis"),
  //  "org.pac4j" % "pac4j-oauth" % pac4jVersion,
  //  "org.pac4j" % "pac4j-saml" % pac4jVersion,
  //  "org.pac4j" % "pac4j-oidc" % pac4jVersion exclude("commons-io" , "commons-io"),
  //  "org.pac4j" % "pac4j-gae" % pac4jVersion,
  "org.pac4j" % "pac4j-jwt" % pac4jVersion exclude("commons-io" , "commons-io"),
  "org.pac4j" % "pac4j-ldap" % pac4jVersion,
  //  "org.pac4j" % "pac4j-sql" % pac4jVersion,
  //  "org.pac4j" % "pac4j-mongo" % pac4jVersion,
  //  "org.pac4j" % "pac4j-kerberos" % pac4jVersion,
  //  "org.pac4j" % "pac4j-couch" % pac4jVersion,
  "com.typesafe.play" % "play-cache_2.12" % playVersion,
  "org.apache.httpcomponents" % "httpclient" % "4.1.1",
  "commons-io" % "commons-io" % "2.5",
  "com.github.cb372" %% "scalacache-guava" % "0.9.4"
)


val sopts = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Xfuture",
  "-Xlint",
  "-no-java-comments"
)
val soptsNoTest = Seq(
  "-Ywarn-dead-code",
  "-Ywarn-value-discard"
)

scalacOptions in (Compile, doc) ++= sopts ++ soptsNoTest
scalacOptions in Test ++= sopts

resolvers ++= Seq(Resolver.mavenLocal, "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases", "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/", "Shibboleth releases" at "https://build.shibboleth.net/nexus/content/repositories/releases/")

routesGenerator := InjectedRoutesGenerator

fork in run := true

import com.typesafe.sbt.packager.MappingsHelper._
mappings in Universal ++= directory(baseDirectory.value / "data")

//dockerBaseImage := "anapsix/alpine-java:8_jdk_unlimited"
dockerBaseImage := "openjdk:8u171-jdk-slim"

//dockerCommands := dockerCommands.value.flatMap {
//  case cmd@Cmd("FROM", _) => List(cmd,
//    Cmd("RUN", "apk update && apk add bash krb5-libs krb5"),
//    Cmd("RUN", "ln -sf /etc/krb5.conf /opt/jdk/jre/lib/security/krb5.conf")
//  )
//  case other => List(other)
//}

dockerExposedPorts := Seq(9000)

dockerEntrypoint := {Seq(s"bin/${name.value}", "-Dconfig.file=conf/production.conf")}

dockerRepository := { if(isStaging)Option("nexus.teamdigitale.test") else Option("nexus.daf.teamdigitale.it") }


publishTo in ThisBuild := {
  val nexus = if(isStaging) "http://nexus.teamdigitale.test:8081/repository/"
  else "http://nexus.daf.teamdigitale.it:8081/repository/"

  if (isSnapshot.value)
    Some("snapshots" at nexus + "maven-snapshots/")
  else
    Some("releases"  at nexus + "maven-releases/")
}

credentials += {if(isStaging) Credentials(Path.userHome / ".ivy2" / ".credentialsTest") else Credentials(Path.userHome / ".ivy2" / ".credentials")}


