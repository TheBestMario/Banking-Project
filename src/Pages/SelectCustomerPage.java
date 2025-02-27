package Pages;

import main.Customer;
import main.Teller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectCustomerPage {

    public static Teller display(Teller currentTeller, Scanner scanner) {
        // Retrieves a list of customers (numbered)

        System.out.println("CLI BANKING SYSTEM");
        //displays customers by row while they exist.
        currentTeller.getDatabase().displayAllCustomers();
        System.out.println("""
                Select a customer to view their accounts
                To go back, enter zero (0)
                Enter the customer's ID: 
                """);

        boolean exit = false;
        while (!exit) {
            try {
                int choice = scanner.nextInt();
                Customer selectedCustomer = currentTeller.getDatabase().getCustomer(choice);
                if (choice == 0) {
                    currentTeller.currentDirectory = "home";
                    exit = true;
                }
                else if (selectedCustomer != null) {
                    currentTeller.setCurrentCustomer(selectedCustomer);
                    System.out.println();
                    System.out.println("Selected Customer: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
                    currentTeller.currentDirectory = "home/customers/accounts";
                    exit = true;
                }
                else {
                    System.out.println("Invalid customer ID. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
                scanner.next();
            }
        }

        return currentTeller;
    }
}
