package Pages;

import java.util.Scanner;
import main.Teller;

public class LoginPage {

    public static Teller display(Teller teller, Scanner scanner) {
        String username = "";
        String password = "";
        boolean isAuthenticated = false;

        System.out.print("""
                Please enter your username:
                """);

        while(username.length() == 0){
            username = scanner.nextLine();
        }

        System.out.print("""
                Please enter your password:
                """);
        while(password.length() == 0){
            password = scanner.nextLine();
            Teller storedTeller = teller.getDatabase().getTeller(username, password);

            if (storedTeller != null) {
                isAuthenticated = true;
                teller = storedTeller;
                teller.loggedIN = true;
            }
        }

        if (isAuthenticated) {
            System.out.println("Login successful! Welcome, " + teller.getFirstName() + " " + teller.getLastName() + ".");
            teller.currentDirectory = "home";
        }
        else {
            System.out.println("""
                    Invalid username or password. Please try again.
                    
                    """);
            teller.currentDirectory = "";
        }

        return teller;
    }
}
