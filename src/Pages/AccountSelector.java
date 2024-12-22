package Pages;
import main.Teller;
import java.util.Scanner;

public class AccountSelector {

    public static Teller display(Teller currentTeller, Scanner scanner) {
        System.out.print("""
                Would you like to
                Select the existing ISA Account for this customer (1)
                Select the existing Personal Account for this customer (2)
                Select the existing Business Account for this customer (3)
                Create an ISA Account for this customer (4)
                Create a Personal Account for this customer (5)
                Create a Business Account for this customer (6)
                Go Back(7)
                Enter your choice, (1,2,3,4,5,6,7):
                """);

        boolean exit = false;
        while (!exit) {
            try {
                int choice = scanner.nextInt();
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
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewISAAccount";
                        return currentTeller;
                    case 5:
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewPersonalAccount";
                        return currentTeller;
                    case 6:
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewBusinessAccount";
                        return currentTeller;
                    case 7:
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


    }
}
