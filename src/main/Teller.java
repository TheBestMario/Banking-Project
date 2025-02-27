package main;

import java.sql.SQLException;
import java.util.*;

public class Teller {
    String firstName;
    String lastName;
    public String currentDirectory;
    public String userName;
    int teller_id;
    public Boolean loggedIN;
    public Boolean ApplicationON;
    private String password;
    private Database db;


    private Customer currentCustomer;
    private Account currentAccount;
    private List<Personal> personalAccounts;
    private List<Business> businessAccounts;

    // teller object should also store info about the customer selected as well (when they are)

    public Teller(String setDirectory, Database db){
        this.currentDirectory = setDirectory;
        this.loggedIN = false;
        this.db = db;
        this.currentCustomer = null;
        this.currentAccount = null;
        this.ApplicationON = true;
    }

    public Boolean Login(){
        return true;
    }

    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getUsername(){
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }
    public Boolean Logout(){
        return true;
    }
    public void setDatabase(Database db){
        this.db = db;
    }
    public Database getDatabase(){
        return this.db;
    }

    public int getID() {
        return this.teller_id;
    }


    public void createAccount(String firstName, String lastName, String password){

    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }
    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }
    public void addCustomerToDB() {
        db.createCustomer(currentCustomer);
    }
    public void setCurrentDirectory(String s) {
        this.currentDirectory = s;
    }
    public Account getCurrentAccount(Account account) {
        return this.currentAccount;

    }
    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }


    public void addPersonalAccount(Personal account) {
        db.createPersonalAccount(account);
    }
    public List<Personal> getPersonalAccounts() {
        if (currentCustomer != null) {
            return db.getPersonalAccount(currentCustomer.getId());
        }
        return null;
    }
    public void addBusinessAccount(Business account) {
        db.createBusinessAccount(account);
    }
    public List<Business> getBusinessAccounts() {
        if (currentCustomer != null) {
            List<Business> accounts = db.getBusinessAccount(currentCustomer.getId());
            if (accounts == null || accounts.isEmpty()) {

                return new ArrayList<>();
            }
            return accounts;
        }
        System.out.println("DEBUG: Current customer is null.");
        return new ArrayList<>(); // Return an empty list if currentCustomer is null
    }
    // clears teller session after logout for security
    public void clearUserSession(){
        this.currentCustomer = null;
        this.currentAccount = null;
        this.teller_id = 0;
        this.personalAccounts = null;
        this.businessAccounts = null;
    }

}
