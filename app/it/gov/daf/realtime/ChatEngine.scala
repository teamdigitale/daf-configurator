package it.gov.daf.realtime

import javax.inject.{Inject, Provider, Singleton}

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.stream.{ActorMaterializer, Materializer}
import akka.stream.scaladsl.{BroadcastHub, Flow, Keep, MergeHub, RunnableGraph, Sink, Source}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import play.api.libs.json.{Format, Json}
import play.engineio.EngineIOController
import play.api.libs.functional.syntax._
import play.socketio.scaladsl.SocketIO

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

/**
  * A simple chat engine.
  */
@Singleton
class ChatEngine @Inject()(socketIO: SocketIO)(implicit mat: Materializer) {

  import play.socketio.scaladsl.SocketIOEventCodec._

  // This will decode String "chat message" events coming in
  val decoder = decodeByName {
    case "chat message" => decodeJson[String]
  }

  // This will encode String "chat message" events going out
  val encoder = encodeByType[String] {
    case _: String => "chat message" -> encodeJson[String]
  }

  implicit val system: ActorSystem = ActorSystem("consumer-sample")
 // implicit val materializer: Materializer = ActorMaterializer()

  val config = system.settings.config.getConfig("akka.kafka.consumer")


  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)

  val done: Source[ConsumerRecord[String, String], Consumer.Control] = Consumer
          .plainSource(consumerSettings, Subscriptions.topics("test_reatime"))
  // .runWith(Sink.foreach(println)) // just print each message for debugging

  val extractKey: Flow[ConsumerRecord[String, String], String, NotUsed] =
    Flow[ConsumerRecord[String, String]].map((record) => {
      println("ALE");
      (Json.parse(record.value()) \ "user").as[String]

    })

  val keys: Source[String, Consumer.Control] = done.via(extractKey)


//   implicit val ec: ExecutionContextExecutor = system.dispatcher
//   done onComplete  {
//     case Success(_) => println("Done"); system.terminate()
//     case Failure(err) => println(err.toString); system.terminate()
//   }

  private val kafkaFlow: Flow[String, String, NotUsed] = {

    val a: Source[String, NotUsed] = keys.toMat(BroadcastHub.sink)(Keep.right).run()


    Flow.fromSinkAndSourceCoupled(BroadcastHub.sink[String], a)

  }


  private val chatFlow: Flow[String, String, NotUsed] = {
    // We use a MergeHub to merge all the incoming chat messages from all the
    // connected users into one flow, and we feed that straight into a
    // BroadcastHub to broadcast them out again to all the connected users.
    // See http://doc.akka.io/docs/akka/snapshot/scala/stream/stream-dynamic.html
    // for details on these features.

    val (sink, source) = MergeHub.source[String]
      .toMat(BroadcastHub.sink)(Keep.both)
      .run

    // We couple the sink and source together so that one completes, the other
    // will to, and we use this to handle our chat
    Flow.fromSinkAndSourceCoupled(sink, source)
  }

  // Here we create an EngineIOController to handle requests for our chat
  // system, and we add the chat flow under the "/chat" namespace.
  val controller: EngineIOController = socketIO.builder
    .addNamespace("/chat", decoder, encoder, kafkaFlow)
    .createController()
}