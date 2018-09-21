package it.gov.daf.common.utils

trait UserInfoSearch
trait UserInfo{
  def username:String
  def groups:Array[String]
}


final case class Empty() extends UserInfoSearch

final case class Credentials(username:String,password:String,groups:Array[String]) extends UserInfoSearch with UserInfo

final case class Profile(username:String,groups:Array[String]) extends UserInfoSearch with UserInfo

