package Pages;

import main.Database;
import main.Personal;
import main.Teller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.SQLException;


public class ExistingPersonalAccountPage {


    public static Teller display(Teller currentTeller, Scanner scanner, Database db) {
        System.out.println("Enter Existing Account Number");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        try {
            Personal account = db.getPersonalAccountById(accountId);

            if (account != null) {
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
                    try {
                        int choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                displayBalance(account);
                            case 2:
                                manageStandingOrders(scanner,account);
                            case 3:
                                viewStatmentHistory(account);
                            case 4:
                                deposit(scanner,account,db);
                            case 5:
                                withdraw(scanner,account,db);
                            case 6:
                                viewCardDetails(account);
                            case 7:
                                viewPendingPayments(account);
                            case 8:
                                isRunning = false;
                                System.out.println("Leaving Personal Account Page");
                                currentTeller.currentDirectory = "home/customers/account";
                                return currentTeller;
                            default:
                                isRunning = true;

                        }


                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid choice");
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println("Account not found");
            }


        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }


        return currentTeller;

    }

    public static void displayBalance(Personal account) {
        System.out.println("Displaying Balance" + account.getBalance());
    }

    public static void manageStandingOrders(Scanner scanner, Personal account) {

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
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter Standing Order ");
                    String order = scanner.nextLine();
                    account.addStandingOrder(order);
                    System.out.println("Standing Order Added");
                }
                case 2: {
                    System.out.println("Enter Standing Order to remove");
                    String order = scanner.nextLine();
                    account.removeStandingOrder(order);
                    System.out.println("Standing Order Removed");
                }
                case 3: {
                    System.out.println("Returning to Main Menu");

                }
                default: {
                    System.out.println("Invalid choice. Try again.");
                }


            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid choice");
            scanner.nextLine();
        }

    }

    public static void viewStatmentHistory(Personal account) {
        System.out.println("View Statment History");
        if (account.getStatmentHistory().isEmpty()) {
            System.out.println("No statment history");
        } else {
            for (String statment : account.getStatmentHistory()) {
                System.out.println(statment);
            }

        }
    }



    public static void deposit(Scanner scanner, Personal account, Database db) {
        System.out.println("Enter amount to deposit");
        try {
            double amount = scanner.nextDouble();
            account.deposit(amount);
            db.updateBalance(account.getAccountNumber(),account.getBalance());
            System.out.println("Amount deposited is £ " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void withdraw(Scanner scanner,Personal account,Database db) {
        System.out.println(" Enter amount to Withdraw Money");
        try {
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            db.updateBalance(account.getAccountNumber(),account.getBalance());
            System.out.println("The amount being withdrawn is £ " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewCardDetails(Personal account) {
        System.out.println("View Card Details" + account.getCardNumber());

    }

    public static void viewPendingPayments(Personal account) {
        System.out.println("View Pending Payments");
        if (account.getPendingPayments().isEmpty()) {
            System.out.println("No pending payments");
        } else {
            for (String payment : account.getPendingPayments()) {
                System.out.println(payment);
            }
        }
    }


}
