package Pages;

import main.Database;
import main.Personal;
import main.Teller;
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

        try {
            Personal newAccount = new Personal(currentTeller.getCurrentCustomer().getId(), initialDeposit,customerId ,bankAddress);
            currentTeller.getDatabase().createPersonalAccount(newAccount);
            System.out.println("New personal account created");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        currentTeller.currentDirectory = "home/customers/accounts";
        return currentTeller;
/*
            // Create new Personal account object
            Personal newPersonalAccount = new Personal(accountNumber,initialDeposit,bankAddress);
            teller.addPersonalAccount(newPersonalAccount);

            System.out.println("New Personal Account created successfully with Account Number: " + accountNumber);
            teller.setCurrentDirectory("home/customers");
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }

        return teller;
    }
    */
    }
}
