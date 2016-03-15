package bankaccount;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

public class Application {

    public static void main(String[] args) throws Exception {
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));

        ActorSystem system = ActorSystem.create("BankAccountActorSystem");

        final ActorRef bankAccount = system.actorOf(Props.create(BankAccount.class), "bankAccount");
        System.out.println(Await.result(Patterns.ask(bankAccount, new Messages.Deposit(30000), timeout), timeout.duration()));
        System.out.println(Await.result(Patterns.ask(bankAccount, new Messages.Withdraw(20000), timeout), timeout.duration()));
        System.out.println(Await.result(Patterns.ask(bankAccount, new Messages.Withdraw(20000), timeout), timeout.duration()));

        system.shutdown();

    }

}
