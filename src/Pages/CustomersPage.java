package Pages;
import java.util.Scanner;
import main.Teller;
public class CustomersPage {

    public CustomersPage() {}

    public static Teller display(Teller currentTeller, Scanner scanner){
        System.out.print("""
               CLI BANKING SYSTEM
               Show Customers (1)
               Create a new customer (2)
               Help Page (3)
               Logout (4)
               Enter your choice, (1,2,3,4):
                """);
        //amateur CLI options placeholder
        boolean exit = false;
        while (!exit) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Showing all customers...");
                        currentTeller.currentDirectory = "home/customers";
                        return currentTeller;
                    case 2:
                        System.out.println("Creating a new customer...");
                        currentTeller.currentDirectory = "home/createCustomer";
                        return currentTeller;
                    case 3:
                        System.out.println("Going to the help page");
                        currentTeller.currentDirectory = "help";
                        return currentTeller;
                    case 4:
                        currentTeller.currentDirectory = "logout";
                        return currentTeller;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
            }
        }
        return currentTeller;
    }
}
