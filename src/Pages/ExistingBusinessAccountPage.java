package Pages;

import main.*;
import main.Teller;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ExistingBusinessAccountPage {


    public static Teller display(Teller currentTeller, Scanner scanner) {
        System.out.println("Entering Existing Business Account");

        while (currentTeller.getBusinessAccounts() == null) {
            System.out.println("No Business Accounts Found");
            System.out.println("""
                    To go Back, Enter 0
                    """);
            String choice = scanner.nextLine();
            if (choice == "0") {
                currentTeller.currentDirectory = "home/customers/accounts";
                return currentTeller;
            }
        }
        currentTeller.getBusinessAccounts().forEach(account -> {
            System.out.println("Account ID " + account.getAccountNumber() + " Balance £ " + account.getBalance());

        });
        System.out.println("""
                To go Back, Enter 0
                Enter Account ID
                
                """);
        boolean exit = false;
        while (!exit) {
            try {
                int choice = scanner.nextInt();
                if (choice == 0) {
                    currentTeller.currentDirectory = "home/customers/accounts";
                    exit = true;
                } else {
                    Business selectedAccount = currentTeller.getDatabase().getBusinessAccountById(choice);
                    if (selectedAccount != null) {
                        System.out.println("Selected Account ID " + selectedAccount.getAccountNumber() + " Balance £ " + selectedAccount.getBalance());
                        currentTeller.setCurrentAccount(selectedAccount);

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
                            try {
                                int actionChoice = scanner.nextInt();
                                switch (actionChoice) {
                                    case 1:
                                        displayBalance(selectedAccount);
                                    case 2:
                                        manageStandingOrders(selectedAccount);
                                    case 3:
                                        viewStatmentHistory(selectedAccount);
                                    case 4:
                                        deposit(scanner, selectedAccount, currentTeller.getDatabase());
                                    case 5:
                                        withdraw(scanner, selectedAccount, currentTeller.getDatabase());
                                    case 6:
                                        viewCardDetails(selectedAccount);
                                    case 7:
                                        makeInternationalPayments(scanner, selectedAccount, currentTeller.getDatabase());
                                    case 8:
                                        viewPendingPayments(selectedAccount);
                                    case 9: {
                                        System.out.println("Leaving Personal Account Page");
                                        currentTeller.currentDirectory = "home/customers/account";
                                        isRunning = false;
                                    }

                                    default:
                                        System.out.println("Invalid Action");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid choice");
                                scanner.nextLine();
                            }
                        }
                        exit = true;
                    } else {
                        System.out.println("Select a valid choice");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
                scanner.nextLine();

            }
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

    public static void deposit(Scanner scanner, Business account, Database db) {
        System.out.println("Enter amount to deposit");
        try {
            double amount = scanner.nextDouble();
            account.deposit(amount);
            db.updateBalance(account.getAccountNumber(), account.getBalance());
            System.out.println("Amount deposited is £ " + amount + "Updatetd Balance" + account.getBalance());
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid amount");
            scanner.nextLine();
        } catch (IllegalArgumentException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void withdraw(Scanner scanner, Business account, Database db) {
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

    private static void makeInternationalPayments(Scanner scanner, Business account, Database db) {
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
