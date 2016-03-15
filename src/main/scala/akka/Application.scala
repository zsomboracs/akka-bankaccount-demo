package akka

import akka.actor.{ActorSystem, Props}


object Application extends App {

  val system = ActorSystem("BankAccountActorSystem")

  val bankAccount = system.actorOf(Props[BankAccount], "bankAccount")

  system.actorOf(Props(new DepositClient(bankAccount, 110)), "depositClient")
  system.actorOf(Props(new DeactivatorClient(bankAccount)), "deactivatorClient")

  Thread.sleep(25000)
  system.shutdown()

}
