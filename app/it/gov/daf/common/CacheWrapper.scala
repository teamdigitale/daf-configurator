package it.gov.daf.common

import com.google.common.cache.CacheBuilder
import com.google.inject.Singleton
import play.api.Logger
import play.api.mvc.Cookie
import scalacache._
import scalacache.guava.GuavaCache
import javax.inject.Inject

import scala.concurrent.duration._


@Singleton
class CacheWrapper @Inject() () {

//  require(cookieTtlMin.nonEmpty , "CacheWrapper configuration error: cookies ttl must be set")
//  require(credentialTtlMin.nonEmpty, "CacheWrapper configuration error: credentials ttl must be set")

  Logger.logger.info(s"CacheWrapper initialized with cookieTtlMin:$cookieTtlMin and credentialTtlMin $credentialTtlMin")

  private val cookieTtlMin = 480
  private val credentialTtlMin = 30

  private val guavaCache = CacheBuilder.newBuilder().maximumSize(10000L).build[String, Object]
  private implicit val cache = ScalaCache(GuavaCache(guavaCache))

  //private def get(key:String):Option[String] = sync.get(key)
  //private def delete(key:String) = remove(key)
  //private def put(key:String,value:String,d:Duration) = sync.cachingWithTTL(key)(d){value}

  def getCookie(appName: String, userName: String): Option[Cookie] = sync.get(s"$appName-$userName")

  def getCookies(appName: String, userName: String): Option[Seq[Cookie]] = sync.get(s"$appName-${userName}multi")

  def getPwd(user:String): Option[String] = sync.get(user)

  def putCookie(appName: String, userName: String, cookie: Cookie) = sync.cachingWithTTL(s"$appName-$userName")(cookieTtlMin.minutes){cookie}

  def putCookies(appName: String, userName: String, cookies: Seq[Cookie]) = sync.cachingWithTTL(s"$appName-${userName}multi")(cookieTtlMin.minutes){ cookies }

  def putCredentials(user: String, pwd: String) = sync.cachingWithTTL(user)(credentialTtlMin.minutes){ pwd }  //TTL must be equal to jwt expiration

  def deleteCookie(appName: String, userName: String)= remove(s"$appName-$userName")

  def deleteCookies(appName: String, userName: String)= remove(s"$appName-${userName}multi")

  def deleteCredentials(user:String) = remove(user)


}
