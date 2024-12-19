package Pages;
import java.util.Scanner;
import main.Teller;
public class CustomersPage {

    public CustomersPage() {}

    public static Teller display(Teller currentTeller, Scanner scanner){
        System.out.print("""
               CLI BANKING SYSTEM
               Review client accounts (1)
               Create a new customer (2)
               Select 
               Exit (3)
               Enter your choice, (1,2,3):
                """);
        //amateur CLI options placeholder
        boolean exit = false;
        while (!exit) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Select customers...");
                        return currentTeller;
                    case 2:
                        System.out.println("Creating a new customer...");
                        return currentTeller;
                    case 3:
                        System.out.println("Go Back");

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
