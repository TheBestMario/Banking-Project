package Pages;

import main.Config;
import main.Customer;
import main.Database;
import main.Teller;

import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NewISAPage {

    private static final Database db = new Database(new Config());

    static int isaTypeId;


    public static Teller display(Teller teller, Scanner scanner) {
        System.out.println("ISA Account Setup");

        // Validate that the customer doesn't already have an ISA
        if (validateUser(teller)) {
            return teller;
        }


        // Choose the account type for the customer. Lifetime ISA will have age validation.
        chooseAccountType(scanner, teller);

        // Ask for initial deposit only after the account type has been selected.
        makeInitialDeposit(scanner, teller, isaTypeId);

        teller.currentDirectory = "home/customers/accounts";
        return teller;
    }


    public static boolean validateUser(Teller teller) {
        // Establish a connection to the database
        if (db.establishConnection()) {
            // If the connection is successful, proceed with checking the ISA account
            Customer customer = teller.getCurrentCustomer();
            int customerId = customer.getId();

            // Check if the customer has an ISA account. Return true or false
            boolean hasISA = db.hasExistingISAAccount(customerId);

            if (hasISA) {
                System.out.println("This customer already has an ISA account. Returning to Customer account options....");

                teller.currentDirectory = "home/customers/accounts";
                return true;
            } else {
                System.out.println("This customer does not have an ISA account. Proceeding with setup...");
                return false;
            }
        } else {
            // If the connection failed, prints an error message
            System.out.println("Unable to establish a connection to the database.");
            return true;
        }
    }


    public static void chooseAccountType(Scanner scanner, Teller teller) {
        boolean validChoice = false;
        int accountType = 0;

        while (!validChoice) {
            try {
                System.out.println("""
                        Choose the type of ISA account:
                        1. Cash ISA
                        2. Stocks and Shares ISA
                        3. Innovative Finance ISA
                        4. Lifetime ISA
                        """);

                System.out.print("Enter the type of ISA account: ");
                accountType = scanner.nextInt();

                if (accountType < 1 || accountType > 4) {
                    System.out.println("Invalid input. Please try again.");
                } else {
                    validChoice = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input.");
                scanner.nextLine();  // Clear buffer
            }


            switch (accountType) {
                case 1:
                    isaTypeId = 1;
                    System.out.println("Customer has selected Cash ISA");
                    break;
                case 2:
                    isaTypeId = 2;
                    System.out.println("Customer has selected Stocks ISA");
                    break;
                case 3:
                    isaTypeId = 3;
                    System.out.println("Customer has selected Innovative Finance ISA");
                    break;
                case 4:
                    isaTypeId = 4;
                    System.out.println("Customer has selected Lifetime ISA");
                    validateAge(scanner, teller); // Validate eligibility for Lifetime Account
                    break;
            }
        }


    }

    public static void makeInitialDeposit(Scanner scanner, Teller teller, int isaTypeId) {
        boolean validDeposit = false;
        double initialDepositAmount = 0;

        while (!validDeposit) {
            try {
                System.out.println("Enter deposit amount: ");
                initialDepositAmount = scanner.nextDouble();

                if (db.checkLimit(isaTypeId, initialDepositAmount)) {
                    System.out.println("Enter deposit amount that does not exceed the limits");
                } else {
                    validDeposit = true;
                    System.out.println("You are able to deposit this amount into your ISA account.");
                    System.out.println("Initial deposit amount is: " + initialDepositAmount);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input.");
                scanner.nextLine();
            }
        }

        Customer customer = teller.getCurrentCustomer();
        int customerId = customer.getId();

        // Save the ISA account and link it to the customer
        boolean success = db.saveISAAccount(isaTypeId, customerId, initialDepositAmount);
        if (success) {
            System.out.println("ISA account successfully created.");
        } else {
            System.out.println("Failed to create ISA account.");
        }
    }

    public static void validateAge(Scanner scanner, Teller teller) {
        System.out.println("HELP: Customer must be between the ages of 18 - 40 to open a Lifetime ISA");

        Customer customer = teller.getCurrentCustomer();
        LocalDate dob = customer.getDob();

        if (dob != null) {

            LocalDate currentDate = LocalDate.now();
            int age = Period.between(dob, currentDate).getYears();


            System.out.println("Customer's age: " + age);

            if (age < 18 || age > 40) {
                System.out.println("Customer is not eligible for Lifetime ISA");
                System.out.println("Select another ISA account");
                chooseAccountType(scanner, teller);
            } else {
                System.out.println("Customer is eligible for Lifetime ISA");

            }

        }
    }
}