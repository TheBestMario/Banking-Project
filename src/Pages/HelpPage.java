package Pages;
import main.Teller;
import java.util.Scanner;


public class HelpPage {
    public static Teller display(Teller currentTeller, Scanner scanner) {

        switch (currentTeller.currentDirectory) {
            case "createTeller/help":
                createTellerHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/help":
                homeNavigationHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/help":
                customersPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/accounts/help":
                accountsPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/accounts/ISAAccount/help":
                existingISAAccountPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/accounts/PersonalAccount/help":
                existingPersonalAccountPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/accounts/BusinessAccount/help":
                existingBusinessAccountPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/createCustomer/help":
                RegisterNewCustomerHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/accounts/NewISAAccount/help":
                newISAAccountPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/accounts/NewBusinessAccount/help":
                newBusinessAccountPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;

            case "home/customers/accounts/NewPersonalAccount/help":
                newPersonalAccountPageHelp(scanner);
                currentTeller.navigateFromHelp();
                return currentTeller;
            default:
                return currentTeller;
        }
    }


    // need to do while looops inside of methods...

    private static void RegisterNewCustomerHelp(Scanner scanner) {
        System.out.println("====== Register New Customer ======");
        System.out.println("To register a new customer, follow these steps:");
        System.out.println("1. Collect customer's personal information including first name, middle name, last name, date of birth, email address, and passport number.");
        System.out.println("2. Use the system's 'Register' feature to input the collected information.");
        System.out.println("3. Confirm the information and complete the registration process.");
        System.out.println("4. Provide the customer with their account details.");
        System.out.println();
    }

    private static void createTellerHelp(Scanner scanner){

    }

    private static void homeNavigationHelp(Scanner scanner){

    }

    private static void customersPageHelp(Scanner scanner){

    }

    private static void accountsPageHelp(Scanner scanner){
        System.out.println("Looks to show the help page!");
        // build out the pages here...
        // waits on user command

    }

    private static void existingISAAccountPageHelp(Scanner scanner){

    }

    private static void existingPersonalAccountPageHelp(Scanner scanner){

    }

    private static void existingBusinessAccountPageHelp(Scanner scanner){

    }

    private static void newISAAccountPageHelp(Scanner scanner){

    }

    private static void newBusinessAccountPageHelp(Scanner scanner){

    }

    private static void newPersonalAccountPageHelp(Scanner scanner){

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
/*


 private static void displayHelpPage(BufferedReader reader) throws IOException {
        while (true) {
            System.out.println("====== Teller Help Page ======");
            System.out.println("1. Register New Customer");
            System.out.println("2. Handle Transactions");
            System.out.println("3. FAQs");
            System.out.println("4. Contact Support");
            System.out.println("5. Back to Main Menu");

            System.out.print("Select an option: ");
            int option = Integer.parseInt(reader.readLine());

            switch (option) {
                case 1:
                    registerNewCustomer();
                    break;
                case 2:
                    handleTransactions();
                    break;
                case 3:
                    displayFAQs();
                    break;
                case 4:
                    contactSupport();
                    break;
                case 5:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayExistingCustomersPage(BufferedReader reader) throws IOException {
        System.out.println("====== Existing Customers Page ======");
        
        // Going to call existing customers page here otherwise return to Main menu 
        
        System.out.println("Returning to Main Menu...");
    }

    private static void displayNewCustomersPage(BufferedReader reader) throws IOException {
        System.out.println("====== New Customers Page ======");
        
        // Same procedure with NewCustomer
        
        System.out.println("Returning to Main Menu...");
    }

    private static void registerNewCustomer() {
        System.out.println("====== Register New Customer ======");
        System.out.println("To register a new customer, follow these steps:");
        System.out.println("1. Collect customer's personal information.");
        System.out.println("2. Use the system's 'Register' feature.");
        System.out.println("3. Confirm and complete the registration.");
        System.out.println("4. Provide the customer with their account details.");
        System.out.println();
    }

    private static void handleTransactions() {
        System.out.println("====== Handle Transactions ======");
        System.out.println("To handle transactions, follow these steps:");
        System.out.println("1. Log into the teller system.");
        System.out.println("2. Navigate to the 'Transactions' section.");
        System.out.println("3. Select the type of transaction.");
        System.out.println("4. Enter and confirm the transaction details.");
        System.out.println("5. Provide the customer with a receipt.");
        System.out.println();
    }

    private static void displayFAQs() {
        System.out.println("====== FAQs ======");
        System.out.println("Q1: How do I register a new customer?");
        System.out.println("A1: Use the 'Register New Customer' feature.");
        System.out.println();
        System.out.println("Q2: What should I do if a transaction fails?");
        System.out.println("A2: Ensure all details are correct and retry.");
        System.out.println("If the issue persists, contact support.");
        System.out.println();
    }



*/
