package Pages;

import main.Database;
import main.Personal;
import main.Teller;
import main.*;
import java.sql.SQLException;
import java.util.Scanner;

public class NewPersonalAccountPage {

    public static Teller display(Teller currentTeller, Scanner scanner) {
        System.out.println("Welcome to CLI Banking System");
        System.out.println("Create a New Personal Account");
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        System.out.println("Enter Bank Address");
        String bankAddress = scanner.next();
        int customerId = currentTeller.getCurrentCustomer().getId();
        System.out.println("Please enter payment limit");
        int paymentLimit = scanner.nextInt();


        try {
            Personal newAccount = new Personal(0, initialDeposit,customerId ,bankAddress,paymentLimit);
            int accountId = currentTeller.getDatabase().createPersonalAccount(newAccount);
            if(accountId > 0){
                System.out.println("Personal Account Created Succesfully, Account ID:  " + accountId);
            }else {
                System.out.println("Personal Account Creation Failed");
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        currentTeller.currentDirectory = "home/customers/accounts";
        return currentTeller;

    }
}
