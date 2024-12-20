package Pages;

import main.Customer;
import main.Teller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                Date of Birth  (5)
                Mobile Number  (6)
                Email Address  (7)
                ------------------
                TO Finish, enter '/confirm'
                To go Back, enter '/back'
                """);
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
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
                        System.out.println("Editing the date of birth...");
                        dob = displayEditDOB(currentTeller, scanner);
                        break;
                    case 6:
                        System.out.println("Editing the phone number...");
                        int phone = displayEditPhoneNumber(currentTeller, scanner);
                        break;
                    case 7:
                        System.out.println("Editing the email address...");
                        String email = displayEditEmail(currentTeller, scanner);
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
                if (input.equalsIgnoreCase("/confirm")) {
                    if (currentTeller.getCurrentCustomer().getFirstName() == null
                            || currentTeller.getCurrentCustomer().getLastName() == null
                            || currentTeller.getCurrentCustomer().getPhoto_proof() == null
                            || currentTeller.getCurrentCustomer().getAddress_proof() == null
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
                } else if (input.equalsIgnoreCase("/back")) {
                    currentTeller.currentDirectory = "home";
                    exit = true;
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
        String input = null;
        String dobString = "";
        LocalDate dob;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            System.out.println("Enter customer's birth date, day-month-year: ");
            input = scanner.nextLine();
            dob = LocalDate.parse(input, formatter);
            dobString = String.valueOf(dob);

        }catch (DateTimeParseException e){
            System.out.println(e);

            if (input.isBlank()){
                System.out.println("Customer date of birth cannot be empty");
            } else if (input.length() > 10){
                System.out.println("Invalid date format, please use a correct format");
            } else if (e.getMessage().contains("Invalid value for MonthOfYear")) {
                System.out.println("The month needs to be valid.");
            }
            return displayEditDOB(currentTeller, scanner);

        }

        if (dobString.isBlank()){
            System.out.println("Customer date of birth cannot be empty");
            return displayEditDOB(currentTeller, scanner);
        } else{
            currentTeller.getCurrentCustomer().setDob(dobString);
            return dobString;
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
    private static int displayEditPhoneNumber(Teller currentTeller, Scanner scanner) {

        System.out.println("Enter the phone number of the customer: ");
        int phone = scanner.nextInt();
        if (phone == 0){
            System.out.println("Phone number cannot be empty");
            return displayEditPhoneNumber(currentTeller, scanner);
        } else{
            currentTeller.getCurrentCustomer().setPhone_number(phone);
            return phone;
        }
    }
    private static String displayEditEmail(Teller currentTeller, Scanner scanner) {

        System.out.println("Enter the email address of the customer: ");
        String email = scanner.nextLine();
        if (email.isBlank()){
            System.out.println("Email address cannot be empty");
            return displayEditEmail(currentTeller, scanner);
        } else{
            currentTeller.getCurrentCustomer().setEmail(email);
            return email;
        }
    }
}
