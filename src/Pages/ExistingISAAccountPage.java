package Pages;

import main.ISA;
import main.Teller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExistingISAAccountPage {

    public static void display(Scanner scanner, Teller teller) {

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
                        displayBalance();
                        break;

                    case 3:
                      withdraw(scanner);
                      break;

                    case 4:
                        deposit(scanner);
                        break;

                    case 5:
                        displayPendingPayments();
                        break;

                    case 6:
                        isRunning = false;
                        System.out.println("Exiting ISA Page...");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public  static void displayGainsOverYears() {
        System.out.println("Gains Over Years");
    }

    public static void displayBalance() {
        System.out.println("Balance");


    }

    public static void withdraw(Scanner scanner) {
        System.out.println("Withdraw");
    }

    public static void deposit(Scanner scanner) {
        System.out.println("Deposit");
    }

    public static void displayPendingPayments() {
        System.out.println("Pending Payments");
    }
}
