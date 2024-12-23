package Pages;

import main.Database;
import main.Business;
import main.Teller;

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
                System.out.println("New Business Account Created");
                //newAccount.setAccountNumber(newAccount);
                currentTeller.addBusinessAccount(newAccount);

            } else {
                System.out.println("New Business Account Creation Failed");
            }
        } catch (Exception e) {
            System.out.println("New Business Account Creation Failed" + e.getMessage());
        }
        currentTeller.currentDirectory = "home/customers/accounts";
        return currentTeller;
    }

    }

        // Insert account into DB and get auto-incremented account_number
        //int accountNumber = Database.createBusinessAccount(initialDeposit, businessDetails, hasChequeBooks);

/*
            // Create new Business account object
            Business newBusinessAccount = new Business(accountNumber, initialDeposit, businessDetails, hasChequeBooks);
            currentTeller.addBusinessAccount(newBusinessAccount);

            System.out.println("New Business Account created successfully with Account Number: " + accountNumber);
            currentTeller.setCurrentDirectory("home/customers");
        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());


        }
        currentTeller.cuurent



        return teller;
    }*/


