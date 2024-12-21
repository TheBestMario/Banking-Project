package Pages;
import main.*;
import main.Teller;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ExistingBusinessAccountPage {


    public static Teller display(Teller currentTeller, Scanner scanner, Database db) {
        System.out.println("Entering Existing Businnes Account");
        int accountId = scanner.nextInt();
        scanner.nextLine();
        try{
            Business account = db.getBusinessAccountById(accountId);
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
                    7. Make International Payments
                    8. View Pending Payments
                    9. Exit
                    """);
                    try{
                        int choice = scanner.nextInt();
                        switch (choice) {
                            case 1: displayBalance(account);
                            case 2: manageStandingOrders(account);
                            case 3: viewStatmentHistory(account);
                            case 4: deposit(scanner,account,db);
                            case 5: withdraw(scanner,account,db);
                            case 6: viewCardDetails(account);
                            case 7: makeInternationalPayments(scanner,account,db);
                            case 8: viewPendingPayments(account);
                            case 9: isRunning = false;
                                System.out.println("Leaving Personal Account Page");
                                currentTeller.currentDirectory = "home/customers/account";
                                return currentTeller;
                            default:
                                isRunning = false;
                        }

                    }catch (InputMismatchException e) {
                        System.out.println("Please enter a valid choice");
                        scanner.nextLine();
                    }
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return currentTeller;

    }

    public static void displayBalance(Business account) {
        System.out.println("Displaying Balance" + account.getBalance());
    }
    public static void manageStandingOrders(Business account) {
        System.out.println("Manage Standing Orders");
    }
    public static void viewStatmentHistory(Business account) {
        System.out.println("View Statment History");
    }
    public static void deposit(Scanner scanner,Business account,Database db) {
        System.out.println("Enter amount to deposit");
        try {
            double amount = scanner.nextDouble();
            account.deposit(amount);
            db.updateBalance(account.getAccountNumber(),account.getBalance());
            System.out.println("Amount deposited is £ " + amount + "Updatetd Balance" + account.getBalance());
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void withdraw(Scanner scanner,Business account,Database db) {
        System.out.print("Enter amount to withdraw: ");
        try {
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            if (amount > account.getBalance()) {
                throw new IllegalArgumentException("Insufficient balance for withdrawal.");
            }
            account.withdraw(amount);
            db.updateBalance(account.getAccountNumber(), account.getBalance());
            System.out.println("Withdrew £" + amount + ". New Balance: £" + account.getBalance());
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error updating balance in database: " + e.getMessage());
        }
    }

    public static void viewCardDetails(Business account) {
        System.out.println("View Card Details");
    }
    private static void makeInternationalPayments(Scanner scanner,Business account,Database db) {
        System.out.print("Enter amount for international payment: ");
        try {
            double amount = scanner.nextDouble();
            if (amount <= 0) {
                throw new IllegalArgumentException("Payment amount must be positive.");
            }
            if (amount > account.getBalance()) {
                throw new IllegalArgumentException("Insufficient balance for international payment.");
            }
            account.withdraw(amount); // Deduct payment from balance
            db.updateBalance(account.getAccountNumber(), account.getBalance());
            System.out.println("International payment of £" + amount + " processed.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error updating balance in database: " + e.getMessage());
        }
    }
    public static void viewPendingPayments(Business account) {
        System.out.println("View Pending Payments");
    }



}
