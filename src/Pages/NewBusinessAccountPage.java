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

        scanner.nextLine();
        System.out.println("Enter the business details");
        String businessDetails = scanner.nextLine();

        System.out.println("Has Cheque Books Yes or No?");
        boolean hasChequeBooks = scanner.nextLine().equalsIgnoreCase("yes");


       try {
            // Insert account into DB and get auto-incremented account_number
            int accountNumber = Database.createBusinessAccount(initialDeposit, businessDetails, hasChequeBooks);


            // Create new Business account object
            Business newBusinessAccount = new Business(accountNumber, initialDeposit, businessDetails, hasChequeBooks);
            teller.addBusinessAccount(newBusinessAccount);

            System.out.println("New Business Account created successfully with Account Number: " + accountNumber);
            teller.setCurrentDirectory("home/customers");
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }



        return teller;
    }
}
