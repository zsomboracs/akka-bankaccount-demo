package akka

import BankAccount.BankAccount
import akka.actor.{ActorSystem, Props}


object Application extends App {

  val system = ActorSystem("BankAccountActorSystem")

  val bankAccount = system.actorOf(Props[BankAccount], "bankAccount")

  system.actorOf(Props(new DepositClient(bankAccount, 34000)), "depositClient1")
  system.actorOf(Props(new WithdrawClient(bankAccount, 30000)), "withdrawClient1")
  system.actorOf(Props(new DepositClient(bankAccount, 23000)), "depositClient2")
  system.actorOf(Props(new WithdrawClient(bankAccount, 97000)), "withdrawClient2")

  Thread.sleep(2000)
  system.shutdown()

}
