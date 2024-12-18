package main;
public class Personal extends Account {
    private static final float min_opening_balance = 1.0F;

    public Personal(int accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
        if (initialBalance < min_opening_balance) {
            throw new IllegalArgumentException("Initial balance cannot be less than min_opening_balance");
        }
    }


    @Override
    public void withdraw(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative amount");
        }
        if (amount < min_opening_balance) {
            throw new IllegalArgumentException("Amount cannot be less than min_opening_balance");
        }
        if (amount > this.balance){
            throw new IllegalArgumentException("Amount cannot be greater than balance");
        }

        this.balance -= amount;
    }

    public void deposit(double amount) {
        if (amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative amount");

        }
        this.balance += amount;
        System.out.println("The balance is: " + this.balance);
    }

    public void viewBalance() {
        System.out.println("The balance is: " + this.balance);
    }


}
