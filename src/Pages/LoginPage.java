package Pages;
import java.util.Scanner;
import main.Teller;

public class LoginPage {
    public static Teller display(Teller teller, Scanner scanner){
        System.out.print("""
                Please enter a username:
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
                }
            return teller;
            }
            catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
            }
        }
        return teller;
    }
}
