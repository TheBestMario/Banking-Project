package Pages;

import main.Config;
import main.Customer;
import main.Database;
import main.Teller;

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
                        deposit(scanner, isaTypeId);
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

    public  static void displayGainsOverYears() {
        System.out.println("Gains Over Years");
    }

    public static void displayBalance(Teller teller) {
        Customer currentCustomer = teller.getCurrentCustomer();
        int customerId  = currentCustomer.getId();

        db.getISABalance(customerId);

    }

    public static void withdraw(Scanner scanner) {

        System.out.println("Cannot withdraw from ISA Account");
    }

    public static void deposit(Scanner scanner, int isaTypeId) {
        boolean validDeposit = false;
        double depositAmount = 0;

        while (!validDeposit) {
            try {
                System.out.println("Enter the amount you want to deposit: ");
                depositAmount = scanner.nextDouble();

                if(db.checkLimit(isaTypeId, depositAmount)){
                    System.out.println( "Enter deposit amount that does not exceed the limits");
                } else{
                    validDeposit = true;
                    System.out.println("Deposit Successful");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void displayPendingPayments() {
        System.out.println("Pending Payments");
    }
}
