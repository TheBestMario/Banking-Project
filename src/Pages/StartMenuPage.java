package Pages;
import main.Teller;
import java.util.Scanner;

public class StartMenuPage {
    public static Teller display(Teller teller, Scanner scanner) {
        System.out.print("""
                Welcome to ACME Bank, Would you like to:
                Login: (1)
                Create an Account: (2)
                Enter your choice, (1,2):
                 """);
        boolean exit = false;

        while (!exit) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        teller.currentDirectory = "login";
                        return teller;
                    case 2:
                        teller.currentDirectory = "createTeller";
                        return teller;
                    default:
                        System.out.println("Invalid input, use a number from the list given");
                        scanner.nextLine();
                }
                return  teller;
            } catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
                scanner.nextLine();
            }
        }
        return teller;
    }

}
