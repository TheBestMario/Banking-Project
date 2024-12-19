package Pages;
import main.Teller;
import java.util.Scanner;


public class HelpPage {
    public static Teller display(Teller currentTeller, Scanner scanner) {
        System.out.println("====== Teller Help Page ======");
        System.out.println("1. Register New Customer");
        System.out.println("2. Handle Transactions");
        System.out.println("3. FAQs");
        System.out.println("4. Contact Support");
        System.out.println("5. Exit");

        while (true) {
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    RegisterNewCustomer();
                    break;
                case 2:
                    HandleTransactions();
                    break;
               
                case 3:
                    displayFAQs();
                    break;
                case 4:
                    ContactSupport();
                    break;
                case 5:
                    System.out.println("Exiting Help Page...");
                    currentTeller.currentDirectory = "home";
                    return currentTeller;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void RegisterNewCustomer() {
        System.out.println("====== Register New Customer ======");
        System.out.println("To register a new customer, follow these steps:");
        System.out.println("1. Collect customer's personal information including first name, middle name, last name, date of birth, email address, and passport number.");
        System.out.println("2. Use the system's 'Register' feature to input the collected information.");
        System.out.println("3. Confirm the information and complete the registration process.");
        System.out.println("4. Provide the customer with their account details.");
        System.out.println();
    }

    private static void HandleTransactions() {
        System.out.println("====== Handle Transactions ======");
        System.out.println("To handle transactions, follow these steps:");
        System.out.println("1. Log into the teller system with your credentials.");
        System.out.println("2. Navigate to the 'Transactions' section.");
        System.out.println("3. Select the type of transaction (deposit, withdrawal, etc.).");
        System.out.println("4. Enter the required transaction details and confirm.");
        System.out.println("5. Ensure that the transaction is processed and provide the customer with a receipt.");
        System.out.println();
    }



    private static void displayFAQs() {
        System.out.println("====== FAQs ======");
        System.out.println("Q1: How do I register a new customer?");
        System.out.println("A1: Use the 'Register New Customer' feature and input the required information.");
        System.out.println();
        System.out.println("Q2: What should I do if a transaction fails?");
        System.out.println("A2: Ensure that all details are correct and retry. If the issue persists, contact support.");
        System.out.println();
        // Add more if we need to do so
    }

    private static void ContactSupport() {
        System.out.println("====== Contact Support ======");
        System.out.println("For further assistance, contact the support team:");
        System.out.println("Email: teller.support@financialservices.com");
        System.out.println("Phone: +1-800-987-6543");
        System.out.println("Support is available 24/7.");
        System.out.println();
    }
}

