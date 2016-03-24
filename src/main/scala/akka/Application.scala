package akka

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.duration._


object Application extends App {

  implicit val timeout = Timeout(5 seconds)

  val system = ActorSystem("BankAccountActorSystem")
  val client = system.actorOf(Props[AdvertiserClient], "client")

  Thread.sleep(500000)
  system.shutdown()

}
