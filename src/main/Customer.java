package main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String photo_proof;
    private String address_proof;
    private String phone_number;
    private String business_proof;
    private List<Account> accounts;
    private LocalDate dob;

    public Customer(String name, String lastName, String photo_proof, String address_proof, String phone_number, String business_proof, LocalDate dob) {
        this.firstName = name;
        this.lastName = lastName;
        this.photo_proof = photo_proof;
        this.address_proof = address_proof;
        this.phone_number = phone_number;
        this.dob = dob;
        this.business_proof = business_proof;
        this.accounts = new ArrayList<>();
    }

    public Customer(String name, String lastName, String photo_proof, String address_proof, String phone_number, LocalDate dob) {
        this.firstName = name;
        this.lastName = lastName;
        this.photo_proof = photo_proof;
        this.address_proof = address_proof;
        this.phone_number = phone_number;
        this.dob = dob;
        this.accounts = new ArrayList<>();
    }
    public Customer(){

    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto_proof() {
        return photo_proof;
    }

    public void setPhoto_proof(String photo_proof) {
        this.photo_proof = photo_proof;
    }

    public String getAddress_proof() {
        return address_proof;
    }

    public void setAddress_proof(String address_proof) {
        this.address_proof = address_proof;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public void setDob(String dob) {
        this.dob = LocalDate.parse(dob);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getBusiness_proof() {
        return business_proof;
    }

    public void setBusiness_proof(String business_proof) {
        this.business_proof = business_proof;
    }
}
