package akka

import akka.actor.Actor
import akka.event.LoggingReceive


object BankAccount {

  case class Deposit(amount: Int) {
    require(amount > 0)
  }

  case class Withdraw(amount: Int) {
    require(amount > 0)
  }

  case class Balance(balance: Int)

  case object GetBalance

  case object Done

  case object Failed

  case object Deactivate

  case object Activate

  case object Deactivated

}

class BankAccount extends Actor {

  import BankAccount._

  var balance = 0

  def receive = LoggingReceive {

    case GetBalance => sender ! Balance(balance)

    case Deposit(amount) =>
      balance += amount
      sender ! Done

    case Withdraw(amount) if amount <= balance =>
      balance -= amount
      sender ! Done

    case Deactivate => context.become(deactivated)

    case _ => sender ! Failed
  }

  def deactivated = LoggingReceive {
    case Activate => context.unbecome()
    case _ => sender ! Deactivated
  }

}
