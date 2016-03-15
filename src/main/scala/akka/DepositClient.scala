package akka

import BankAccount.{Balance, Deposit, Done, GetBalance}
import akka.actor.{Actor, ActorRef}
import akka.event.LoggingReceive

class DepositClient(bankAccount: ActorRef) extends Actor {

  println(s"deposit sent 10")
  bankAccount ! Deposit(10)

  println(s"deposit sent 20")
  bankAccount ! Deposit(20)

  println(s"deposit sent 30")
  bankAccount ! Deposit(30)

  println(s"deposit sent 40")
  bankAccount ! Deposit(40)

  println(s"deposit sent 50")
  bankAccount ! Deposit(50)

  def receive = LoggingReceive {
    case Done =>
  }

}
