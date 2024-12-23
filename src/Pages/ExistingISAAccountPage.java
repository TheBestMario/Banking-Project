package Pages;

import main.*;
import main.ISA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExistingISAAccountPage {
    private static final Database db = new Database(new Config());
    static int isaTypeId;


    public static Teller display(Teller teller, Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            // ISA Account Menu
            System.out.println(
                    """
                            ISA Account Page
                             1. Gains Over the Years
                             2. Check Balance
                             3. Withdraw
                             4. Deposit
                             5. Pending ISA Payments
                             6. Exit
                            """
            );

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayGainsOverYears();
                        break;

                    case 2:
                        displayBalance(teller);
                        break;

                    case 3:
                        withdraw(scanner);
                        break;

                    case 4:
                        deposit(scanner, isaTypeId, teller);
                        break;

                    case 5:
                        displayPendingPayments();
                        break;

                    case 6:
                        isRunning = false;
                        System.out.println("Exiting ISA Page...");
                        teller.currentDirectory = "home/customers/accounts";
                        return teller;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return teller;
    }

    public static void displayGainsOverYears() {
        System.out.println("The customer account is newly opened. They will be able to requests to view their gains over the year.");
    }

    public static void displayBalance(Teller teller) {
        Customer currentCustomer = teller.getCurrentCustomer();
        int customerId = currentCustomer.getId();

        System.out.println(db.getISABalance(customerId));

    }

    public static void withdraw(Scanner scanner) {

        System.out.println("Cannot withdraw from ISA Account");
    }

    public static void deposit(Scanner scanner, int isaTypeId, Teller teller) {
        boolean validDeposit = false;
        double depositAmount = 0;
        double currentBalance = 0;
        double newBalance = 0;
        double annualChargePercentage = 2.75;

        Customer currentCustomer = teller.getCurrentCustomer();
        int customerId = currentCustomer.getId();

        while (!validDeposit) {
            try {
                System.out.println("Enter the amount you want to deposit: ");
                depositAmount = scanner.nextDouble();

                // Get current balance
                currentBalance = db.getISABalance(customerId);
                newBalance = currentBalance + depositAmount; // Calculate the new balance
                double annualCharge = (annualChargePercentage /100.0) * newBalance;



                if (db.checkLimit(isaTypeId, newBalance)) {
                    // Update the balance in the database
                    boolean updated = db.updateISABalance(customerId, newBalance); // Use customerId for update

                    if (updated) {
                        System.out.println("Deposit successful! Your new balance is  £" + newBalance);
                        System.out.println("The Annual Charge to be applied at the end of the year is £" + annualCharge);
                        validDeposit = true;
                    } else {
                        System.out.println("Failed to update the balance. Please try again.");
                    }

                } else {
                    System.out.println("Deposit exceeds the ISA limit. ");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");

            } catch (RuntimeException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void displayPendingPayments() {
        System.out.println("Pending Payments");
    }
}
