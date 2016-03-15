package akka

import akka.BankAccount.{Deactivate, Activate}
import akka.actor.{Actor, ActorRef}
import akka.event.LoggingReceive

import scala.concurrent.duration._

class DeactivatorClient(bankAccount: ActorRef) extends Actor {

  import context.dispatcher

  context.system.scheduler.scheduleOnce(10 seconds, bankAccount, Deactivate)
  context.system.scheduler.scheduleOnce(15 seconds, bankAccount, Activate)

  def receive = LoggingReceive {
    case _ =>
  }

}
