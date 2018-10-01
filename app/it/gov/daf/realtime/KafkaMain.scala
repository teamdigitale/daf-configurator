package it.gov.daf.realtime

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.kafka.scaladsl.Consumer
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}
import akka.stream.{ActorMaterializer, Materializer}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import play.api.libs.json.Json

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object KafkaMain {
  def main(args: Array[String]): Unit = {
  implicit val system: ActorSystem = ActorSystem("consumer-sample")
  implicit val materializer: Materializer = ActorMaterializer()

  val config = system.settings.config.getConfig("akka.kafka.consumer")


  val consumerSettings =
    ConsumerSettings(config, new StringDeserializer, new StringDeserializer)

 /* val done = Consumer
         .plainSource(consumerSettings, Subscriptions.topics("creationfeed"))
        .runWith(Sink.foreach(println)) // just print each message for debugging

  implicit val ec: ExecutionContextExecutor = system.dispatcher
  done onComplete  {
    case Success(_) => println("Done"); system.terminate()
    case Failure(err) => println(err.toString); system.terminate()
  } */


    val extractKey: Flow[ConsumerRecord[String, String], String, NotUsed] =
      Flow[ConsumerRecord[String, String]].map((record) => {
        println("ALE");
        (Json.parse(record.value()) \ "user").as[String]
      }
      )



    val sourceConsumer= Consumer
      .plainSource(consumerSettings, Subscriptions.topics("creationfeed"))
       // .via(extractKey)

    val keys: Source[String, Consumer.Control] = sourceConsumer.via(extractKey)


    val control = keys.toMat(Sink.foreach(println))(Keep.both)
      .mapMaterializedValue(DrainingControl.apply)
      .run()

    // Correctly handle Ctrl+C and docker container stop
    sys.addShutdownHook({
      println("Shutdown requested...")

      val done = control.shutdown()

      implicit val ec: ExecutionContextExecutor = system.dispatcher
      done
        .onComplete {
          case Success(_) => println("Exiting ...")
          case Failure(err) => println("Error", err)
        }
    })


}
}

