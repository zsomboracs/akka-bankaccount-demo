package akka

import akka.actor.{ActorSystem, Props}


object Application extends App {

  val system = ActorSystem("BankAccountActorSystem")

  val bankAccount = system.actorOf(Props[BankAccount], "bankAccount")

  system.actorOf(Props(new DepositClient(bankAccount, 10)), "depositClient1")
  system.actorOf(Props(new DepositClient(bankAccount, 20)), "depositClient2")
  system.actorOf(Props(new DepositClient(bankAccount, 30)), "depositClient3")
  system.actorOf(Props(new DepositClient(bankAccount, 40)), "depositClient4")
  system.actorOf(Props(new DepositClient(bankAccount, 50)), "depositClient5")
  system.actorOf(Props(new DepositClient(bankAccount, 60)), "depositClient6")

  Thread.sleep(2000)
  system.shutdown()

}
