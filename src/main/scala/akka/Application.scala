package akka

import akka.actor.{ActorSystem, Props}


object Application extends App {

  val system = ActorSystem("BankAccountActorSystem")

  val bankAccount = system.actorOf(Props[BankAccount], "bankAccount")

  system.actorOf(Props(new DepositClient(bankAccount)), "depositClient")

  Thread.sleep(2000)
  system.shutdown()

}
