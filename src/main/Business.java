package main;

public class Business extends Account {
    private static final double ANNUAL_CHARGE = 120.0;

    public Business(int accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        }
    }

    public void applyAnnualCharge() {
        balance -= ANNUAL_CHARGE;
    }
}
