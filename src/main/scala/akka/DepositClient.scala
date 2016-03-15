package akka

import BankAccount.BankAccount
import BankAccount.{Balance, Deposit, Done, GetBalance}
import akka.actor.{Actor, ActorRef}
import akka.event.LoggingReceive

class DepositClient(bankAccount: ActorRef, amount: Integer) extends Actor {

  bankAccount ! Deposit(amount)

  def receive = LoggingReceive {
    case Done => bankAccount ! GetBalance
    case Balance(balance) => context.stop(self)
  }

}
