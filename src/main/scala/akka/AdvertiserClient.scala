package akka

import akka.BankAccount.{Deposit, Withdraw}
import akka.actor.{Actor, Props}

import scala.concurrent.duration._


class AdvertiserClient extends Actor {

  val bankAccountA = context.actorOf(Props(new BankAccount("Zsombor_Acs@epam.com")), "bankAccounA")
  val bankAccountB = context.actorOf(Props(new BankAccount("Geza_Nagy@epam.com")), "bankAccounB")

  val advertiserMaster = context.actorOf(Props[AdvertiserMaster], "advertiserMaster")

  import context.dispatcher

  bankAccountA ! Deposit(10000)
  bankAccountB ! Deposit(15000)

  context.system.scheduler.schedule(2 seconds, 2 seconds, bankAccountA, Withdraw(20))
  context.system.scheduler.schedule(2 seconds, 3 seconds, bankAccountB, Withdraw(20))

  def receive = {
    case _ =>
  }

}
