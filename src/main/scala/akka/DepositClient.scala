package akka

import akka.BankAccount.Deposit
import akka.actor.{Actor, ActorRef}
import akka.event.LoggingReceive

import scala.concurrent.duration._

class DepositClient(bankAccount: ActorRef, amount: Integer) extends Actor {

  import context.dispatcher

  context.system.scheduler.schedule(0 seconds, 2 seconds, bankAccount, Deposit(amount))

  def receive = LoggingReceive {
    case _ =>
  }

}
