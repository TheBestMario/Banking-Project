package Pages;

import main.Teller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NewISAPage {

    // Access to data structure that holds existing customers with ISA accounts

    boolean hasExistingISA = false;

    public static Teller display(Teller teller, Scanner scanner) {
        System.out.println("ISA Account Setup");

        validateUser(scanner); // Makes sure that the customer does not have an existing ISA account

        chooseAccountType(scanner); // Choose the account type for the customer. Lifetime ISA has age validation.

        makeInitialDeposit(scanner); // The customer makes a deposit. Minimum of £100.

        return teller;
    }


    public static void validateUser(Scanner scanner) {
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        // If statement here to check if customer isa exists within the database


//        if (/* hashMap.contains(name)*/) {
//            System.out.println("Customer already has a ISA account. Customer can only have one ISA account");
//        } else {
//            System.out.println("No ISA account associated with this customer.");
//            // hashMap.add(customerName);
//            hasExistingISA = true;
//        }
    }

    public static void chooseAccountType(Scanner scanner) {
        boolean validChoice = false;
        int accountType = 0;

        while (!validChoice) {
            try {
                System.out.println("""
                        Choose the type of ISA account:
                        1. Cash ISA
                        2. Stocks and Shares ISA
                        3. Innovative finance ISA
                        4. Lifetime ISA
                        """);

                System.out.println("Enter the type of ISA account: ");
                accountType = scanner.nextInt();

                if (accountType < 1 || accountType > 4) {
                    System.out.println("Invalid input. Please try again.");
                } else {
                    validChoice = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input.");

            }


            switch (accountType) {
                case 1:
                    System.out.println("Customer has selected Cash ISA");
                    break;
                case 2:
                    System.out.println("Customer has selected Stocks ISA");
                    break;

                case 3:
                    System.out.println("Customer has selected Innovative finance ISA");
                    break;

                case 4:
                    System.out.println("Customer has selected Lifetime ISA");
                    System.out.println("You must validate the customer age to see if they are eligible for Lifetime ISA");
                    validateAge(scanner);
            }
        }

    }

    public static void makeInitialDeposit(Scanner scanner) {
        boolean validDeposit = false;
        double initialDepositAmount = 0;

        while (!validDeposit) {
            try {
                System.out.println("Enter deposit amount: ");
                initialDepositAmount = scanner.nextDouble();

                if (initialDepositAmount < 0) {
                    System.out.println("Deposit must be minimum of £100");
                } else {
                    validDeposit = true;
                    System.out.println("Initial deposit amount is: " + initialDepositAmount);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input.");
            }
        }

    }

    public static void validateAge(Scanner scanner) {
        System.out.println("HELP: Customer must be between the ages of 18 - 40 to open a Lifetime ISA");
        int age = scanner.nextInt();
        // If the customer is an existing customer, we can pull their DOB and calculate their age.

        if (age < 18 || age > 40) {
            System.out.println("Customer is not eligible for Lifetime ISA");
            System.out.println("Select another ISA account");
        } else {
            System.out.println("Customer is eligible for Lifetime ISA");
            makeInitialDeposit(scanner);
        }

    }
}
