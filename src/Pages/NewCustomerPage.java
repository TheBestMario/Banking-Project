package Pages;

import main.Customer;
import main.Teller;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NewCustomerPage {

    private static Customer customer;
    public static Teller display(Teller currentTeller, Scanner scanner){
        /*
        (id int primary key identity(1,1),
        firstName varchar(255),
        lastName varchar(255),
        photo_proof varchar(255),
        address_proof varchar(255),
        business_proof varchar(255),
        DOB date,
        isDeleted tinyint);
         */
        String firstName;
        String lastName;
        String photoProof;
        String addressProof;
        String businessProof;
        String dob;
        customer = new Customer();
        currentTeller.setCurrentCustomer(customer);
        scanner.nextLine();
        boolean exit = false;

        /*
        Similar set up to Router.java with the switch cases inside a while loop except they don't return. Methods called
        by each case are in this file and use the teller object to find the customer object, which changes its fields.
        The methods contain recursion and conditional logic.
         */
        while (!exit) {
            System.out.println("""
                CLI BANKING SYSTEM
                ------------------
                You are adding a new customer!
                
                Please choose the details you would like to enter
                from the list bellow, one by one.
                ------------------
                First Name     (1)
                Last Name      (2)
                Photo Proof    (3)
                Address Proof  (4)
                Business Proof (5)
                Date of Birth  (6)
                ------------------
                TO CONFIRM, enter 'confirm'
                To go back, enter zero (0)
                """);
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        currentTeller.currentDirectory = "home";
                        exit = true;
                        return currentTeller;
                    case 1:
                        System.out.println("Editing First Name...");

                        firstName = displayEditFirstName(currentTeller, scanner);
                        break;
                    case 2:
                        System.out.println("Editing Last Name...");
                        lastName = displayEditLastName(currentTeller, scanner);
                        break;

                    case 3:
                        System.out.println("Editing Photo Proof...");
                        photoProof = displayEditPhoto(currentTeller, scanner);
                        break;
                    case 4:
                        System.out.println("Editing Address Proof...");
                        addressProof = displayEditAddress(currentTeller, scanner);
                        break;
                    case 5:
                        System.out.println("Editing proof of business...");
                        businessProof = displayEditBusinessProof(currentTeller, scanner);
                        break;
                    case 6:
                        System.out.println("Editing the date of birth...");
                        dob = displayEditDOB(currentTeller, scanner);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                System.out.println("""
                        Customer details updated successfully!""");
                TimeUnit.SECONDS.sleep(1);
            }

            catch (Exception e) {
                /*

                checks if input String is confirm and big if statement checks through
                all the fields. If any are null, it will not confirm and ask the user to fill in all fields.

                 */
                if (input.equalsIgnoreCase("confirm")) {
                    if (currentTeller.getCurrentCustomer().getFirstName() == null
                            || currentTeller.getCurrentCustomer().getLastName() == null
                            || currentTeller.getCurrentCustomer().getPhoto_proof() == null
                            || currentTeller.getCurrentCustomer().getAddress_proof() == null
                            || currentTeller.getCurrentCustomer().getBusiness_proof() == null
                            || currentTeller.getCurrentCustomer().getDob() == null) {

                        System.out.println("""
                        Please fill in all the fields before confirming, or exit without saving.
                        Loading the customer creation page again...
                        """);
                        //sleeps for better user experience
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else {
                        /*
                        condition for what happens when everything is good to go.
                         */
                        System.out.println("Customer details confirmed!");

                        //sleeps for better user experience
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }

                        currentTeller.currentDirectory = "home/customers";
                        exit = true;
                        currentTeller.addCustomerToDB();
                    }
                } else {

                    System.out.println("Invalid input, use a number from the list given");
                }
            }
        }

        return currentTeller;
    }

    private static String displayEditFirstName(Teller currentTeller, Scanner scanner) {

        System.out.println("Enter the first name of the customer: ");
        String firstName = scanner.nextLine();
        if (firstName.isBlank()){
            System.out.println("The last name cannot be empty");
            return displayEditFirstName(currentTeller, scanner);
        } else if (firstName.length() > 255) {
            System.out.println("First name field cannot be greater than 255 characters");
            return displayEditFirstName(currentTeller, scanner);
        } else{
            currentTeller.getCurrentCustomer().setFirstName(firstName);
            return firstName;
        }
    }
    private static String displayEditLastName(Teller currentTeller, Scanner scanner) {

        System.out.println("Enter the last name of the customer: ");
        String lastName = scanner.nextLine();
        if (lastName.isBlank()){
            System.out.println("The last name cannot be empty");
            return displayEditLastName(currentTeller, scanner);
        } else if (lastName.length() > 255) {
            System.out.println("Last name field cannot be greater than 255 characters");
            return displayEditLastName(currentTeller, scanner);
        } else{
            currentTeller.getCurrentCustomer().setLastName(lastName);
            return lastName;
        }
    }
    private static String displayEditAddress(Teller currentTeller, Scanner scanner) {

        System.out.println("Enter the address of the customer: ");
        String address = scanner.nextLine();
        if (address.isBlank()){
            System.out.println("Address cannot be empty");
            return displayEditAddress(currentTeller, scanner);
        } else if (address.length() > 255) {
            System.out.println("Address field cannot be greater than 255 characters");
            return displayEditAddress(currentTeller, scanner);
        } else{
            currentTeller.getCurrentCustomer().setAddress_proof(address);
            return address;
        }
    }

    private static String displayEditPhoto(Teller currentTeller, Scanner scanner) {

        System.out.println("Enter ID proof of the customer: ");
        String photo = scanner.nextLine();
        if (photo.isBlank()){
            System.out.println("Proof of ID cannot be empty");
            return displayEditPhoto(currentTeller, scanner);
        }
         else{
            currentTeller.getCurrentCustomer().setPhoto_proof(photo);
            return photo;
        }
    }
    private static String displayEditDOB(Teller currentTeller, Scanner scanner) {

        System.out.println("Enter customer's birth date: ");
        String dob = scanner.nextLine();
        if (dob.isBlank()){
            System.out.println("Customer date of birth cannot be empty");
            return displayEditDOB(currentTeller, scanner);
        }
        else{
            currentTeller.getCurrentCustomer().setDob(dob);
            return dob;
        }
    }
    private static String displayEditBusinessProof(Teller currentTeller, Scanner scanner) {

        System.out.println("If opening a business account, please provide proof of business: ");
        String business = scanner.nextLine();
        if (business.isBlank()){
            System.out.println("Proof of business cannot be empty");
            return displayEditBusinessProof(currentTeller, scanner);
        }
        else{
            currentTeller.getCurrentCustomer().setBusiness_proof(business);
            return business;
        }
    }
}
