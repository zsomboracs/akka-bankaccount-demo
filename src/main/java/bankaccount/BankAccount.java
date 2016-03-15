package bankaccount;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class BankAccount extends AbstractActor {

    private int balance = 0;

    public BankAccount() {
        receive(ReceiveBuilder
                .match(Messages.Deposit.class, deposit -> {
                    balance += deposit.amount;
                    sender().tell(Messages.DONE, self());
                })
                .match(Messages.Withdraw.class, withdraw -> withdraw.amount < balance, withdraw -> {
                    balance -= withdraw.amount;
                    sender().tell(Messages.DONE, self());
                })
                .match(Messages.GetBalance.class, withdraw -> sender().tell(new Messages.Balance(balance), self()))
                .matchAny(m -> sender().tell(Messages.FAILED, self()))
                .build());
    }
}
