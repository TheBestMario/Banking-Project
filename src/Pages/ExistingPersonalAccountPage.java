package Pages;
import main.Teller;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExistingPersonalAccountPage {
    public static Teller display(Teller currentTeller, Scanner scanner) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                    Perosnal Account Page
                    1. Check Balance
                    2. Manage Standing Order
                    3. View Statment History
                    4. Deposit Money
                    5. Withdraw Money
                    6. View Card Details
                    7. View Pending Payments
                    8. Exit
                    """);
            try{
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: displayBalance();
                    case 2: manageStandingOrders();
                    case 3: viewStatmentHistory();
                    case 4: deposit(scanner);
                    case 5: withdraw(scanner);
                    case 6: viewCardDetails();
                    case 7: viewPendingPayments();
                    case 8:
                        isRunning = false;
                        System.out.println("Leaving Personal Account Page");
                        currentTeller.goBack();
                        return currentTeller;
                    default:
                        isRunning = true;

                }

            }catch (InputMismatchException e) {
                System.out.println("Please enter a valid choice");
                scanner.nextLine();
            }
        }
        return currentTeller;
    }
    public static void displayBalance() {
        System.out.println("Displaying Balance");
    }
    public static void manageStandingOrders() {
        System.out.println("Manage Standing Orders");
    }
    public static void viewStatmentHistory() {
        System.out.println("View Statment History");
    }
    public static void deposit(Scanner scanner) {
        System.out.println("Enter amount to deposit");
        try{
            double amount = scanner.nextDouble();
            System.out.println("Amount deposited is £ " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        }
    }
    public static void withdraw(Scanner scanner) {
        System.out.println(" Enter amount to Withdraw Money");
        try{
            double amount = scanner.nextDouble();
            System.out.println("The amount being withdrawn is £ " + amount);
        }catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        }
    }
    public static void viewCardDetails() {
        System.out.println("View Card Details");
    }
    public static void viewPendingPayments() {
        System.out.println("View Pending Payments");
    }


}
