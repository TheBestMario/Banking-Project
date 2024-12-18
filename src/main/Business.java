package main;

public class Business extends Account {
    private static final double ANNUAL_CHARGE = 120.0;

    public Business(int accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);

    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (amount > this.balance){
            throw new IllegalArgumentException("Amount must be less than balance");
        }
        this.balance -= amount;

    }

    public void applyAnnualCharge() {
        if (this.balance < ANNUAL_CHARGE){
            throw new IllegalArgumentException("Insufficient Balance for Bank Charge");
        }
        this.balance -= ANNUAL_CHARGE;
    }

    public void deposit(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");

        }
        this.balance += amount;
        System.out.println("The Balance is: " + this.balance);

    }
    public void viewBalance() {
        System.out.println("The Balance is: " + this.balance);
    }

    public void makeInternationalPayments(double amount) {
        if (amount <= 0 || amount > this.balance){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.balance -= amount;
        System.out.println("The International Payment is: " + amount);

    }
}

