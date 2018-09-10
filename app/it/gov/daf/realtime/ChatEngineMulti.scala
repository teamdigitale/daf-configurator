package it.gov.daf.realtime

import javax.inject.{Inject, Singleton}

import akka.NotUsed
import akka.stream._
import akka.stream.scaladsl.{BroadcastHub, Flow, Keep, MergeHub, Sink, Source}
import play.api.libs.json.{Format, Json}
import play.engineio.EngineIOController
import play.api.libs.functional.syntax._
import play.socketio.scaladsl.SocketIO

import scala.collection.concurrent.TrieMap


@Singleton
class ChatEngineMulti @Inject()(socketIO: SocketIO)(implicit mat: Materializer) {

    import ChatProtocol._

    // All the chat rooms
    private val chatRooms = TrieMap.empty[String, (Sink[ChatEvent, NotUsed], Source[ChatEvent, NotUsed])]

    // This gets an existing chat room, or creates it if it doesn't exist
    private def getChatRoom(user: User, room: String) = {
      val (sink, source) = chatRooms.getOrElseUpdate(room, {
        // Each chat room is a merge hub merging messages into a broadcast hub.
        MergeHub.source[ChatEvent].toMat(BroadcastHub.sink[ChatEvent])(Keep.both).run
      })

      Flow.fromSinkAndSourceCoupled(
        Flow[ChatEvent]
          // Add the join and leave room events
          .prepend(Source.single(JoinRoom(Some(user), room)))
          .concat(Source.single(LeaveRoom(Some(user), room)))
          .to(sink),
        source
      )

    }

    // Creates a chat flow for a user session
    def userChatFlow(user: User): Flow[ChatEvent, ChatEvent, NotUsed] = {

      // broadcast source and sink for demux/muxing multiple chat rooms in this one flow
      // They'll be provided later when we materialize the flow
      var broadcastSource: Source[ChatEvent, NotUsed] = null
      var mergeSink: Sink[ChatEvent, NotUsed] = null

      Flow[ChatEvent] map {
        case event @ JoinRoom(_, room) =>
          val roomFlow = getChatRoom(user, room)

          // Add the room to our flow
          broadcastSource
            // Ensure only messages for this room get there
            // Also filter out JoinRoom messages, since there's a race condition as to whether it will
            // actually get here or not, so the room flow explicitly adds it.
            .filter(e => e.room == room && !e.isInstanceOf[JoinRoom])
            // Take until we get a leave room message.
            .takeWhile(!_.isInstanceOf[LeaveRoom])
            // And send it through the room flow
            .via(roomFlow)
            // Re-add the leave room here, since it was filtered out before and we want to see it ourselves
            .concat(Source.single(LeaveRoom(Some(user), room)))
            // And feed to the merge sink
            .runWith(mergeSink)

          event

        case ChatMessage(_, room, message) =>
          ChatMessage(Some(user), room, message)

        case other => other

      } via {
        Flow.fromSinkAndSourceCoupledMat(BroadcastHub.sink[ChatEvent], MergeHub.source[ChatEvent]) { (source, sink) =>
          broadcastSource = source
          mergeSink = sink
          NotUsed
        }
      }
    }

    val controller: EngineIOController = socketIO.builder
      .onConnect { (request, sid) =>
        // Extract the username from the header
        val username = request.getQueryString("user").getOrElse {
          throw new RuntimeException("No user parameter")
        }
        // And return the user, this will be the data for the session that we can read when we add a namespace
        User(username)
      }.addNamespace(decoder, encoder) {
      case (session, chat) if chat.split('?').head == "/chat" => userChatFlow(session.data)
    }
      .createController()
  }

