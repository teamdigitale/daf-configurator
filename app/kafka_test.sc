import akka.Done
import akka.actor.ActorSystem
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.kafka.scaladsl.Consumer
import akka.kafka.scaladsl.Consumer.DrainingControl
import akka.stream.scaladsl.{Keep, Sink}
import akka.stream.{ActorMaterializer, Materializer}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

/*
implicit val system: ActorSystem = ActorSystem("consumer-sample")
val config = system.settings.config.getConfig("akka.kafka.consumer")

implicit val materializer: Materializer = ActorMaterializer()

val consumerSettings =
  ConsumerSettings(system, new StringDeserializer, new StringDeserializer)

val done = Consumer
  .plainSource(consumerSettings, Subscriptions.topics("creationfeed"))
  .runWith(Sink.foreach(x => println("ale"))) // just print each message for debugging

implicit val ec: ExecutionContextExecutor = system.dispatcher
done onComplete  {
  case Success(_) => println("Done"); system.terminate()
  case Failure(err) => println(err.toString); system.terminate()
}


implicit val system: ActorSystem = ActorSystem("consumer-sample")
implicit val ec: ExecutionContextExecutor = system.dispatcher


val config = system.settings.config.getConfig("akka.kafka.consumer")
implicit val materializer: Materializer = ActorMaterializer()
val consumerSettings =
  ConsumerSettings(config, new StringDeserializer, new ByteArrayDeserializer)
    .withBootstrapServers("edge1.novalocal:9092")
    .withGroupId("group1")
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

val done = Consumer
  .plainSource(consumerSettings, Subscriptions.topics("creationfeed"))
  .runWith(Sink.foreach(x => println("ale"))) // just print each message for debugging



done onComplete  {
  case Success(_) => println("Done"); system.terminate()
  case Failure(err) => println(err.toString); system.terminate()
} */

println("Hello from hBaseWriter")

implicit val system: ActorSystem = ActorSystem("consumer-sample")
implicit val materializer: Materializer = ActorMaterializer()

val config = system.settings.config.getConfig("akka.kafka.consumer")

val consumerSettings =
  ConsumerSettings(config, new StringDeserializer, new StringDeserializer)
    .withBootstrapServers("edge1.novalocal:9092")
    .withGroupId("group1")
    .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

/*
val done = Consumer
  .plainSource(consumerSettings, Subscriptions.topics("creationfeed"))
  .runWith(Sink.foreach(println)) // just print each message for debugging

implicit val ec: ExecutionContextExecutor = system.dispatcher
done onComplete  {
  case Success(_) => println("Done"); system.terminate()
  case Failure(err) => println(err.toString); system.terminate()
}
*/


val done = Consumer
  .plainSource(consumerSettings, Subscriptions.topics("creationfeed"))
  .toMat(Sink.foreach(x => println("ALEEEEEEEEEEEEEEE")))(Keep.both)
  .mapMaterializedValue(DrainingControl.apply)
  .run()


// Correctly handle Ctrl+C and docker container stop
sys.addShutdownHook({
  println("Shutdown requested...")

  val shutdown = done.shutdown()

  implicit val ec: ExecutionContextExecutor = system.dispatcher
  shutdown.onComplete {
      case Success(_) => println("Exiting ...")
      case Failure(err) => println("Error", err)
    }
})
