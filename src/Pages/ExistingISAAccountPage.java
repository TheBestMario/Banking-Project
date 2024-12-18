package Pages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExistingISAAccountPage {

    public void display(Scanner scanner) {

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
                        deposit();
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

    public void displayGainsOverYears() {
        System.out.println("Gains Over Years");
    }

    public void displayBalance() {
        System.out.println("Balance");
    }

    public void withdraw(Scanner scanner) {
        System.out.println("Withdraw");
    }

    public void deposit() {
        System.out.println("Deposit");
    }

    public void displayPendingPayments() {
        System.out.println("Pending Payments");
    }
}
