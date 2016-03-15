package bankaccount;

public class Messages {

    public static final class Deposit {
        public final int amount;

        public Deposit(int amount) {
            this.amount = amount;
        }
    }

    public static final class Withdraw {
        public final int amount;

        public Withdraw(int amount) {
            this.amount = amount;
        }
    }

    public static final class Done {
    }

    public static final Done DONE = new Done();

    public static final class Failed {
    }

    public static final Failed FAILED = new Failed();

    public static final class GetBalance {
    }

    public static final GetBalance GET_BALANCE = new GetBalance();

    public static final class Balance {
        public final int balance;

        public Balance(int balance) {
            this.balance = balance;
        }
    }

}
