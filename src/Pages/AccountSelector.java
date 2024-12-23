package Pages;
import main.Customer;
import main.Teller;
import java.util.Scanner;

public class AccountSelector {
    static Customer currentCustomer;
    public static Teller display(Teller currentTeller, Scanner scanner) {
        currentCustomer = currentTeller.getCurrentCustomer();
        System.out.print("""
                Would you like to
                Select the existing ISA Account for this customer (1)
                Select the existing Personal Account for this customer (2)
                Select the existing Business Account for this customer (3)
                Create an ISA Account for this customer (4)
                Create a Personal Account for this customer (5)
                Create a Business Account for this customer (6)
                Show Help Page (7)
                Go Back (8)
                Enter your choice, (1,2,3,4,5,6,7,8):
                """);

        boolean exit = false;
        while (!exit) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Selecting the existing ISA Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/ISAAccount";
                        return currentTeller;
                    case 2:
                        System.out.println("Selecting the existing Personal Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/PersonalAccount";
                        return currentTeller;
                    case 3:
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/BusinessAccount";
                        return currentTeller;
                    case 4:
                        System.out.println("Creating the ISA Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewISAAccount";
                        return currentTeller;
                    case 5:
                        System.out.println("Creating a Personal Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewPersonalAccount";
                        return currentTeller;
                    case 6:
                        System.out.println("Creating a new Business Account for this customer");

                        if (!businessProofExists(currentTeller)) {
                            System.out.println("Business proof is required to create a business account");

                            if (addBusinessProofMiniPage(currentTeller, scanner)){
                                currentTeller.currentDirectory = "home/customers/accounts/NewBusinessAccount";
                            }
                            return currentTeller;
                        }else {
                            currentTeller.currentDirectory = "home/customers/accounts/NewBusinessAccount";
                            return currentTeller;
                        }
                    case 7:
                        currentTeller.currentDirectory = "home/customers/accounts/help";
                        currentTeller = HelpPage.display(currentTeller, scanner);
                        return currentTeller;
                    case 8:
                        System.out.println("Going Back");
                        currentTeller.currentDirectory = "home/customers";
                        return currentTeller;
                    default:
                        System.out.println("Invalid choice, Please try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
            }
        }
        return currentTeller;


    }private static boolean businessProofExists(Teller currentTeller) {
        String businessProof = currentTeller.getCurrentCustomer().getBusiness_proof();
        if (businessProof == null || businessProof.isEmpty()) {
            return false;
        }else
            return true;
    }
    private static Boolean addBusinessProofMiniPage(Teller currentTeller, Scanner scanner){
        System.out.println("Enter the proof of business for this customer");
        String input = scanner.nextLine();
        if (input.isBlank()){

            System.out.println("Business proof cannot be empty");
            addBusinessProofMiniPage(currentTeller, scanner);

        } else if (input.equalsIgnoreCase("/back") || input.equals("0")) {

            System.out.println("cancelling the operation");
            return false;

        } else if (confirmation(currentTeller, scanner)) {

            //sets attribute in the current customer object
            currentTeller.getCurrentCustomer().setBusiness_proof(input);
            //adds the attribute proof to the database as business proof
            currentTeller.getDatabase().setBusinessProof(currentTeller.getCurrentCustomer().getId(),
                    currentTeller.getCurrentCustomer().getBusiness_proof());
            return true;
        }
        else {

            addBusinessProofMiniPage(currentTeller, scanner);
        }

        return false;
    }
    private static boolean confirmation(Teller currentTeller, Scanner scanner){
        System.out.println("Confirm that this the correct information?");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes")){

            return true;

        }else if(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No")) {
            return false;
        }
        else{

            System.out.println("Invalid input, please enter yes or no.");
            confirmation(currentTeller, scanner);

        }
        return false;

    }
}
