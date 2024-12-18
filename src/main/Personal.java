package main;
public class Personal extends Account {
    private static final float min_opening_balance = 1.0F;

    public Personal(int accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        }
    }
}
