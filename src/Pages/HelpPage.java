package Pages;
import main.Teller;
import java.util.Scanner;

public class HelpPage {
    public static Teller display(Teller currentTeller, Scanner scanner) {

        switch (currentTeller.currentDirectory) {
            case "help":
                startHelp(scanner);
                currentTeller.currentDirectory = "";
                return currentTeller;

            case "home/help":
                homeNavigationHelp(scanner);
                currentTeller.currentDirectory = "home";
                return currentTeller;

            case "home/customers/help":
                customersPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers";
                return currentTeller;

            case "home/customers/accounts/help":
                accountsPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers/accounts";
                return currentTeller;

            case "home/customers/accounts/ISAAccount/help":
                existingISAAccountPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers/accounts/ISAAccount";
                return currentTeller;

            case "home/customers/accounts/PersonalAccount/help":
                existingPersonalAccountPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers/accounts/PersonalAccount";
                return currentTeller;

            case "home/customers/accounts/BusinessAccount/help":
                existingBusinessAccountPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers/accounts/BusinessAccount";
                return currentTeller;

            case "home/createCustomer/help":
                RegisterNewCustomerHelp(scanner);
                currentTeller.currentDirectory = "home/createCustomer";
                return currentTeller;

            case "home/customers/accounts/NewISAAccount/help":
                newISAAccountPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers/accounts/NewISAAccount";
                return currentTeller;

            case "home/customers/accounts/NewBusinessAccount/help":
                newBusinessAccountPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers/accounts/NewBusinessAccount";
                return currentTeller;

            case "home/customers/accounts/NewPersonalAccount/help":
                newPersonalAccountPageHelp(scanner);
                currentTeller.currentDirectory = "home/customers/accounts/NewPersonalAccount";
                return currentTeller;
            default:
                return currentTeller;
        }
    }


    private static void startHelp(Scanner scanner){
        System.out.println("====== Starting help page ======");
        System.out.print("""
                This page will allow you to create a teller account to login or login as a teller.
                As a teller you will use this application to talk to users of the bank and handle their money 
                on their behalf using this application.
                Please click 1 to return back to previous Page
                """);
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }
    }

    private static void RegisterNewCustomerHelp(Scanner scanner) {
        // Display Page
        System.out.println("""
                ====== Register New Customer ======
                You can choose a field to fill in and type '/back'
                 to discard the registration during the process.
                
                Ensure information is accurate before submitting, this will update the database.
                To FINISH and SUBMIT the registration, type '/submit'.
                *All fields are required to finish and submit.
                
                """);
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }
    }

    private static void createTellerHelp(Scanner scanner){
        // Prints out for the help page here...

        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }
    }

    private static void homeNavigationHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("====== Register New Accounts ======");
        System.out.println("To register a new customer, follow these steps:");
        System.out.println("1. Collect customer's personal information including first name, middle name, last name, date of birth, email address, and passport number.");
        System.out.println("2. Use the system's 'Register' feature to input the collected information.");
        System.out.println("3. Confirm the information and complete the registration process.");
        System.out.println("4. Provide the customer with their account details.");
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }
    }

    private static void customersPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }
    }

    private static void accountsPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }
    }

    private static void existingISAAccountPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }

    }

    private static void existingPersonalAccountPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }

    }

    private static void existingBusinessAccountPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }

    }

    private static void newISAAccountPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }

    }

    private static void newBusinessAccountPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }

    }

    private static void newPersonalAccountPageHelp(Scanner scanner){
        // Prints out for the help page here...
        System.out.println("Please click 1 to return back to previous Page");
        String userInput = scanner.nextLine().trim();
        while(!userInput.equals("1")){
            userInput = scanner.nextLine().trim();
        }
    }

    private static void HandleTransactions(Scanner scanner) {
        System.out.println("====== Handle Transactions ======");
        System.out.println("To handle transactions, follow these steps:");
        System.out.println("1. Log into the teller system with your credentials.");
        System.out.println("2. Navigate to the 'Transactions' section.");
        System.out.println("3. Select the type of transaction (deposit, withdrawal, etc.).");
        System.out.println("4. Enter the required transaction details and confirm.");
        System.out.println("5. Ensure that the transaction is processed and provide the customer with a receipt.");
        System.out.println();
    }

    private static void displayFAQs(Scanner scanner) {
        System.out.println("====== FAQs ======");
        System.out.println("Q1: How do I register a new customer?");
        System.out.println("A1: Use the 'Register New Customer' feature and input the required information.");
        System.out.println();
        System.out.println("Q2: What should I do if a transaction fails?");
        System.out.println("A2: Ensure that all details are correct and retry. If the issue persists, contact support.");
        System.out.println();
    }

    private static void ContactSupport(Scanner scanner) {
        System.out.println("====== Contact Support ======");
        System.out.println("For further assistance, contact the support team:");
        System.out.println("Email: teller.support@financialservices.com");
        System.out.println("Support is available 24/7.");
        System.out.println();
    }

}

