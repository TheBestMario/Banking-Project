package Pages;

import main.Database;
import main.Personal;
import main.Teller;

import java.util.Scanner;

public class NewPersonalAccountPage {

    public static Teller display(Teller teller, Scanner scanner) {
        System.out.println("Create a New Personal Account");
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        /* try {
            // Insert account into DB and get auto-incremented account_number
            int accountNumber = Database.createPersonalAccount(initialDeposit);

            // Create new Personal account object
            Personal newPersonalAccount = new Personal(accountNumber, initialDeposit);
            teller.addPersonalAccount(newPersonalAccount);

            System.out.println("New Personal Account created successfully with Account Number: " + accountNumber);
            teller.setCurrentDirectory("home/customers");
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
*/
        return teller;
    }
}
