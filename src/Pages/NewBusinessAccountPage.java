package Pages;

import main.Database;
import main.Business;
import main.Teller;
import main.*;


import java.util.Scanner;

public class NewBusinessAccountPage {

    public static Teller display(Teller currentTeller, Scanner scanner) {
        System.out.println("Create a New Business Account");
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        scanner.nextLine();
        System.out.println("Enter the business details");
        String businessDetails = scanner.nextLine();

        System.out.println("Has Cheque Books Yes or No?");
        boolean hasChequeBooks = scanner.nextLine().equalsIgnoreCase("yes");

        int customerId = currentTeller.getCurrentCustomer().getId();


        try {
            Business newAccount = new Business(0, initialDeposit, businessDetails, customerId, hasChequeBooks, initialDeposit);
            int accountId = currentTeller.getDatabase().createBusinessAccount(newAccount);
            if (accountId > 0) {
                System.out.println("New Business Account Created, Account ID: " + accountId);

            } else {
                System.out.println("New Business Account Creation Failed");
            }
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        currentTeller.currentDirectory = "home/customers/accounts";
        return currentTeller;
    }

}



