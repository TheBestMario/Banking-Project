package main;

import java.util.List;

public class Teller {
    String firstName;
    String lastName;
    public String currentDirectory;
    public String userName;
    int teller_id;
    public Boolean loggedIN;
    private String password;
    private Database db;

    private Customer currentCustomer;
    private List<Personal> personalAccounts;
    private List<Business> businessAccounts;

    // teller object should also store info about the customer selected as well (when they are)

    public Teller(String setDirectory, Database db){
        this.currentDirectory = setDirectory;
        this.loggedIN = false;
        this.db = db;
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

    public void addPersonalAccount(Personal account) {
        this.personalAccounts.add(account);
    }
    public List<Personal> getPersonalAccounts() {
        return personalAccounts;
    }
    public void addBusinessAccount(Business account) {
        this.businessAccounts.add(account);
    }
    public List<Business> getBusinessAccounts() {
        return businessAccounts;
    }
}
