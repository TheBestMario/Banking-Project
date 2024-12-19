package Pages;
import main.Personal;
import main.Teller;

import java.util.InputMismatchException;
import java.util.Scanner;



public class ExistingPersonalAccountPage {
    private static Personal account;

    public ExistingPersonalAccountPage(Personal account) {
        ExistingPersonalAccountPage.account = account;
    }

    public static Teller display(Teller currentTeller,Scanner scanner) {
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
                    case 2: manageStandingOrders(scanner);
                    case 3: viewStatmentHistory();
                    case 4: deposit(scanner);
                    case 5: withdraw(scanner);
                    case 6: viewCardDetails();
                    case 7: viewPendingPayments();
                    case 8: isRunning = false;
                        System.out.println("Leaving Personal Account Page");
                        currentTeller.currentDirectory = "home/customers/account";
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
    public static void manageStandingOrders(Scanner scanner) {

        System.out.println("Manage Standing Orders");
        int i = 1;
        for (String order : account.getStandingOrders()) {
            System.out.println(i++ + ". " + order);
        }
        System.out.println("""
                1. Add Standing Order
                2. Remove Standing Orders
                3. Main Menu
                """);
        try{
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:{
                    System.out.println("Enter Standing Order ");
                    String order = scanner.nextLine();
                    account.addStandingOrder(order);
                    System.out.println("Standing Order Added");
                }
                case 2:{
                    System.out.println("Enter Standing Order to remove");
                    String order = scanner.nextLine();
                    account.removeStandingOrder(order);
                    System.out.println("Standing Order Removed");
                }
                case 3:{
                    System.out.println("Returning to Main Menu");

                }
                default:{
                    System.out.println("Invalid choice. Try again.");
                }


            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid choice");
            scanner.nextLine();
        }

    }
    public static void viewStatmentHistory() {
        System.out.println("View Statment History");
        if (account.getStatmentHistory().isEmpty()) {
            System.out.println("No statment history");
        }
        else {
            for (String statment : account.getStatmentHistory()) {
                System.out.println(statment);
            }

        }
    }
    public static void deposit(Scanner scanner) {
        System.out.println("Enter amount to deposit");
        try{
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Amount deposited is £ " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());}
    }
    public static void withdraw(Scanner scanner) {
        System.out.println(" Enter amount to Withdraw Money");
        try{
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            System.out.println("The amount being withdrawn is £ " + amount);
        }catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void viewCardDetails() {
        System.out.println("View Card Details" + account.getCardNumber());

    }
    public static void viewPendingPayments() {
        System.out.println("View Pending Payments");
        if (account.getPendingPayments().isEmpty()) {
            System.out.println("No pending payments");
        }
        else {
            for (String payment : account.getPendingPayments()) {
                System.out.println(payment);
            }
        }
    }


}
