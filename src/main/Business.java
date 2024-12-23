package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Business extends Account {
    private static final double ANNUAL_CHARGE = 120.0;
    private int customerId;
    private String cardNumber;
    private List<String> standingOrders;
    private List<String> statmentHistory;
    private List<String> pendingPayments;
    private String businessDetails;
    private boolean hasChequeBooks;
    private double initialDeposit;


    public Business(int accountNumber, double initialBalance,String businessDetails,int customerId ,boolean hasChequeBooks, double initialDeposit) {
        super(accountNumber, initialBalance);
        this.customerId = customerId;
        this.businessDetails = businessDetails;
        this.hasChequeBooks = hasChequeBooks;
        this.cardNumber = generateCardNumber();
        this.standingOrders = new ArrayList<>();
        this.statmentHistory = new ArrayList<>();
        this.pendingPayments = new ArrayList<>();
        this.initialDeposit = initialDeposit;


    }

    public double getInitialDeposit() {
        return initialDeposit;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getBusinessDetails(){
        return businessDetails;
    }
    public boolean hasChequeBooks(){
        return hasChequeBooks;
    }



    private String generateCardNumber() {
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

    public List<String> getStandingOrders() {
        return List.copyOf(standingOrders);
    }
    public List<String> getStatmentHistory() {
        return List.copyOf(statmentHistory);
    }
    public List<String> getPendingPayments() {
        return List.copyOf(pendingPayments);
    }
    public void addStandingOrder(String order){
        this.standingOrders.add(order);
    }
    public void addStatmentHistory(String order){
        this.statmentHistory.add(order);
    }
    public void addPendingPayment(String order){
        this.pendingPayments.add(order);
    }
    public void removeStandingOrder(String order){
        this.standingOrders.remove(order);
    }
    public void addStamentHistory(String order){
        this.statmentHistory.add(order);
    }

}

