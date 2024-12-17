public class ISA extends Account {
    private static final double ANNUAL_APR = 0.0275;
    private double balance;

    public ISA(int accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("You cannot withdraw from an ISA account");
    }


    public void applyAnnualInterest() {
        this.balance = this.balance + (this.balance * ANNUAL_APR);
    }
}
