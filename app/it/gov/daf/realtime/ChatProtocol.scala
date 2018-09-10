package it.gov.daf.realtime

import play.api.libs.json.{Format, Json}
import play.api.libs.functional.syntax._


object ChatProtocol {

  /**
    * A chat event, either a message, a join room, or a leave room event.
    */
  sealed trait ChatEvent {
    def user: Option[User]
    def room: String
  }

  case class ChatMessage(user: Option[User], room: String, message: String) extends ChatEvent
  object ChatMessage {
    implicit val format: Format[ChatMessage] = Json.format
  }

  case class JoinRoom(user: Option[User], room: String) extends ChatEvent
  object JoinRoom {
    implicit val format: Format[JoinRoom] = Json.format
  }

  case class LeaveRoom(user: Option[User], room: String) extends ChatEvent
  object LeaveRoom {
    implicit val format: Format[LeaveRoom] = Json.format
  }

  case class User(name: String)
  object User {
    // We're just encoding user as a simple string, not an object
    implicit val format: Format[User] = implicitly[Format[String]].inmap(User.apply, _.name)
  }

  import play.socketio.scaladsl.SocketIOEventCodec._

  val decoder = decodeByName {
    case "chat message" => decodeJson[ChatMessage]
    case "join room" => decodeJson[JoinRoom]
    case "leave room" => decodeJson[LeaveRoom]
  }

  val encoder = encodeByType[ChatEvent] {
    case _: ChatMessage => "chat message" -> encodeJson[ChatMessage]
    case _: JoinRoom => "join room" -> encodeJson[JoinRoom]
    case _: LeaveRoom => "leave room" -> encodeJson[LeaveRoom]
  }
}