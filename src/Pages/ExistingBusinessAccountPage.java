package Pages;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExistingBusinessAccountPage {
    public void display(Scanner scanner) {
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
                    7. Make International Payments
                    8. View Pending Payments
                    9. Exit
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
                    case 7: makeInternationalPayments(scanner);
                    case 8: viewPendingPayments();
                    case 9: isRunning = false;
                        System.out.println("Leaving Personal Account Page");
                        break;
                    default:
                        isRunning = false;
                }

            }catch (InputMismatchException e) {
                System.out.println("Please enter a valid choice");
                scanner.nextLine();
            }
        }

    }
    public void displayBalance() {
        System.out.println("Displaying Balance");
    }
    public void manageStandingOrders() {
        System.out.println("Manage Standing Orders");
    }
    public void viewStatmentHistory() {
        System.out.println("View Statment History");
    }
    public void deposit(Scanner scanner) {
        System.out.println("Enter amount to deposit");
        try{
            double amount = scanner.nextDouble();
            System.out.println("Amount deposited is £ " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        }
    }
    public void withdraw(Scanner scanner) {
        System.out.println(" Enter amount to Withdraw Money");
        try{
            double amount = scanner.nextDouble();
            System.out.println("The amount being withdrawn is £ " + amount);
        }catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        }
    }
    public void viewCardDetails() {
        System.out.println("View Card Details");
    }
    public void makeInternationalPayments(Scanner scanner) {
        System.out.println("Enter amount to make International Payments");
        try{
            double amount = scanner.nextDouble();
            System.out.println("The amount being making International Payments is " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        }
    }
    public void viewPendingPayments() {
        System.out.println("View Pending Payments");
    }


}
