package Pages;

import main.Teller;
import java.util.Scanner;

public class CreateAccountPage {

    public static Teller display(Teller currentTeller, Scanner scanner) {
        String firstName = "";
        String lastName = "";
        String password = "";
        String userName = "";
        String confirmPassword = "";
        Boolean validPassword = false;
        scanner.nextLine();

        while (firstName.length() == 0) {
            System.out.print("Please enter your first name: ");
            firstName = scanner.nextLine();
        }

        // Get the last name

        while (lastName.length() == 0) {
            System.out.print("Please enter your last name: ");
            lastName = scanner.nextLine();
        }

        while (userName.length() == 0) {
            System.out.print("Please enter your username: ");
            userName = scanner.nextLine();
        }

        // Get and confirm the password
        while (!validPassword) {
            System.out.print("Please enter your password: ");
            password = scanner.nextLine().trim();

            System.out.print("Please confirm your password: ");
            confirmPassword = scanner.nextLine().trim();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again.");
            }
            else if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
            }
            else{
                validPassword = true;
                currentTeller.setFirstName(firstName);
                currentTeller.setLastName(lastName);
                currentTeller.setUserName(userName);
                currentTeller.setPassword(password);
                // after a teller is saved, route back to the root directory
                System.out.println("Account created successfully! Welcome, " + currentTeller.getFirstName() + " " + currentTeller.getLastName() + ".");

                currentTeller.getDatabase().createTeller(currentTeller);
                currentTeller.currentDirectory = "";
                return currentTeller;
            }
        }
        return currentTeller;
    }
}
