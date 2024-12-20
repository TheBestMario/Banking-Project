package Pages;

import main.Database;
import main.Business;
import main.Teller;

import java.util.Scanner;

public class NewBusinessAccountPage {

    public static Teller display(Teller teller, Scanner scanner) {
        System.out.println("Create a New Business Account");
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

       try {
            // Insert account into DB and get auto-incremented account_number
       //     int accountNumber = Database.createBusinessAccount(initialDeposit);

            // Create new Business account object
       //     Business newBusinessAccount = new Business(accountNumber, initialDeposit);
       //     teller.addBusinessAccount(newBusinessAccount);

       //     System.out.println("New Business Account created successfully with Account Number: " + accountNumber);
            teller.setCurrentDirectory("home/customers");
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }



        return teller;
    }
}
