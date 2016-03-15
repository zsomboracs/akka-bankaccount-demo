package akka

import BankAccount._
import akka.actor.{Actor, ActorRef}
import akka.event.LoggingReceive

class WithdrawClient (bankAccount: ActorRef, amount: Integer) extends Actor {

  bankAccount ! Withdraw(amount)

  def receive = LoggingReceive {
    case Done => bankAccount ! GetBalance
    case Failed => context.stop(self)
    case Balance(balance) => context.stop(self)
  }

}
