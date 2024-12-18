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
        }
        System.out.println("answers:");
        System.out.println(username);
        System.out.println(password);

    /*
        if (isAuthenticated) {
            System.out.println("Login successful! Welcome, " + teller.getFirstName() + " " + teller.getLastName() + ".");
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return display(teller, scanner);
        }


     */
        teller.currentDirectory = "/home";
        return teller;
    }
}
