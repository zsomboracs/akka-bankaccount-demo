package akka

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import org.scalatest._

class WireTransferSpec(_system: ActorSystem) extends TestKit(_system)
  with FlatSpecLike
  with ImplicitSender
  with Matchers
  with BeforeAndAfterAll {

  def this() = this(ActorSystem("WireTransferSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  it should "transfer money from A to B in case of sufficient funds on A" in {
    val accountA = TestProbe()
    val accountB = TestProbe()
    val transfer = system.actorOf(Props[WireTransfer])
    transfer ! WireTransfer.Transfer(accountA.ref, accountB.ref, 20)
    accountA.expectMsg(BankAccount.Withdraw(20))
    accountA.reply(BankAccount.Done)
    accountB.expectMsg(BankAccount.Deposit(20))
    accountB.reply(BankAccount.Done)
    expectMsg(WireTransfer.Done)
  }

  it should "not transfer money from A to B in case of insufficient funds on A" in {
    val accountA = TestProbe()
    val accountB = TestProbe()
    val transfer = system.actorOf(Props[WireTransfer])
    transfer ! WireTransfer.Transfer(accountA.ref, accountB.ref, 20)
    accountA.expectMsg(BankAccount.Withdraw(20))
    accountA.reply(BankAccount.Failed)
    expectMsg(WireTransfer.Failed)
  }


}
