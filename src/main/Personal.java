package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Personal extends Account {
    private static final float min_opening_balance = 1.0F;
    private int customerId;
    private String cardNumber;
    private List<String> standingOrders;
    private List<String> statmentHistory;
    private List<String> pendingPayments;
    private String bankAddress;
    private double initialBalance;
    private int paymentLimit;





    public Personal(int accountNumber, double initialBalance,int customerId, String bankAddress, int paymentLimit) {
        super(accountNumber, initialBalance);
        if (initialBalance < min_opening_balance) {
            throw new IllegalArgumentException("Initial balance cannot be less than min_opening_balance");
        }
        this.customerId = customerId;
        this.bankAddress = bankAddress;
        this.paymentLimit = paymentLimit;
        this.cardNumber = generateCardNumber();
        this.standingOrders = new ArrayList<>();
        this.statmentHistory = new ArrayList<>();
        this.pendingPayments = new ArrayList<>();
        this.initialBalance = initialBalance;

    }

    public int getPaymentLimit(){
        return paymentLimit;
    }
    public void setPaymentLimit(int paymentLimit){
        if ( paymentLimit <= 0){
            throw new IllegalArgumentException("Payment limit must be greater than 0");
        }
        this.paymentLimit = paymentLimit;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getBankAddress() {
        return bankAddress;
    }
    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }


    private String generateCardNumber() { //generate card number
        Random rand = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            number.append(rand.nextInt(10));
        }
        return number.toString();
    }

    public String getCardNumber() {
        return cardNumber;
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

    public List<String> getStandingOrders() {
        return List.copyOf(standingOrders);
    }
    public List<String> getStatmentHistory() {
        return List.copyOf(statmentHistory);
    }
    public List<String> getPendingPayments() {
        return List.copyOf(pendingPayments);
    }
    public void addStandingOrder(String standingOrder) {
        this.standingOrders.add(standingOrder);
    }
    public void removeStandingOrder(String standingOrder) {
        this.standingOrders.remove(standingOrder);
    }

    public void addStatmentHistory(String statmentHistory) {
        this.statmentHistory.add(statmentHistory);
    }
    public void addPendingPayment(String pendingPayment) {
        this.pendingPayments.add(pendingPayment);
    }


}
