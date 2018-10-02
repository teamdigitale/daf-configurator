package it.gov.daf.common

import java.util

import it.gov.daf.common.authentication.Authentication
import it.gov.daf.common.utils._
import org.apache.commons.codec.binary.Base64
import play.api.Logger
import play.api.libs.typedmap.TypedMap
import play.api.mvc.request.{RemoteConnection, RequestTarget}
import play.api.mvc.{Headers, Request, RequestHeader}

import scala.util.Try


object CredentialManager {

  def readBaCredentials( requestHeader:RequestHeader):UserInfoSearch= {

    val authHeader = requestHeader.headers.get("authorization").get.split(" ")
    val authType = authHeader(0)
    val authCrendentials = authHeader(1)


    if( authType.equalsIgnoreCase("basic") ){

      val pwd:String = new String(Base64.decodeBase64(authCrendentials.getBytes)).split(":")(1)
      val user:String= Authentication.getProfiles(requestHeader).head.getId
      val ldapGroups = Authentication.getProfiles(requestHeader).head.getAttribute("memberOf") match {
        case coll: util.Collection[_] => coll.asInstanceOf[util.Collection[String]].toArray()
        case s: String => Array(s)
        case _ => throw new Exception("User with no associated profiles")
      }

      val groups: Array[String] = ldapGroups.map( _.toString().split(",")(0).split("=")(1) )

      Credentials(user, pwd, groups)
    }else
      Empty()

  }


  def readBearerCredentials(requestHeader: RequestHeader):UserInfoSearch= {

    val authHeader = requestHeader.headers.get("authorization").get.split(" ")
    val authType = authHeader(0)

    if( authType.equalsIgnoreCase("bearer") ) {

      val claims = Authentication.getClaims(requestHeader).get
      val ldapGroups = claims.get("memberOf").asInstanceOf[Option[Any]].get.asInstanceOf[net.minidev.json.JSONArray].toArray
      val groups = ldapGroups.map(_.toString.split(",")(0).split("=")(1)).toSeq
      val user: String = claims("sub").toString

      Profile(user, groups)
    }else
      Empty()

  }

  def readCredentialFromRequest( requestHeader: RequestHeader ):UserInfo = {

    readBearerCredentials(requestHeader) match {
      case p:Profile => p
      case e:Empty => readBaCredentials(requestHeader) match{
        case c:Credentials => c
        case _ => throw new Exception("Authorization header not found")
      }
    }

  }

  def tryToReadCredentialFromRequest( requestHeader: RequestHeader ):Try[UserInfo] = {
    Try{readCredentialFromRequest(requestHeader)}
  }

  def isDafSysAdmin( request:Request[Any]):Boolean ={
    val groups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${groups.toList}" )
    groups.contains(SysAdmin.toString)
  }

  def isOrgAdmin( request:Request[Any], orgName:String ):Boolean ={
    val groups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${groups.toList}" )
    groups.contains(Admin.toString+orgName)
  }

  def isOrgsAdmin( request:RequestHeader, orgsNames:Seq[String] ):Boolean ={
    val userGroups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${userGroups.toList}" )
    (orgsNames.map(Admin.toString+_).toSet intersect userGroups.toSet).nonEmpty
  }

  def isAdminOfAllThisOrgs( request:Request[Any], orgsNames:Seq[String] ):Boolean ={
    val userGroups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${userGroups.toList}" )
    (orgsNames.map(Admin.toString+_).toSet intersect userGroups.toSet).size == orgsNames.length
  }

  def isOrgEditor( request:Request[Any], orgName:String ):Boolean ={
    val groups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${groups.toList}" )
    groups.contains(Editor.toString+orgName)
  }

  def isOrgsEditor( request:RequestHeader, orgsNames:Seq[String] ):Boolean ={
    val userGroups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${userGroups.toList}" )
    (orgsNames.map(Editor.toString+_).toSet intersect userGroups.toSet).nonEmpty
  }

  def isBelongingToOrgAs( request:Request[Any], orgName:String ):Option[Role] ={
    val groups = readCredentialFromRequest(request).groups.toSeq
    Logger.logger.info(s"belonging to groups: ${groups.toList}" )

    Role.pickRole(groups,orgName)
  }

  def getUserGroups( request:Request[Any]):Option[String] ={
    val groups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${groups.toList}" )

    Some(groups.mkString("|"))
  }


  def getUserAdminGroups( request:Request[Any]):Seq[String] ={

    val groups = readCredentialFromRequest(request).groups
    Logger.logger.info(s"belonging to groups: ${groups.toList}" )
    groups.filter(_.startsWith(Admin.toString)).map(_.replace(Admin.toString,""))

  }

}
