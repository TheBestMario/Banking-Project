package Pages;
import java.util.Scanner;

public class CustomersPage {

    public CustomersPage() {}

    public static void display(Scanner scanner){
        System.out.print("""
               CLI BANKING SYSTEM
               Review client accounts (1)
               Create a new account (2)
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
                        System.out.println("Reviewing client accounts...");
                        break;
                    case 2:
                        System.out.println("Creating a new account...");
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        exit = true;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
            }
        }

    }
}
