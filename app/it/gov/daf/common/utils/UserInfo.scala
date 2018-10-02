package it.gov.daf.common.utils

trait UserInfoSearch
trait UserInfo{
  def username:String
  def groups:Seq[String]
}


final case class Empty() extends UserInfoSearch

final case class Credentials(username:String,password:String,groups:Seq[String]) extends UserInfoSearch with UserInfo

final case class Profile(username:String,groups:Seq[String]) extends UserInfoSearch with UserInfo

