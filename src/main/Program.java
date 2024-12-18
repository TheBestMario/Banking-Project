package main;
import java.util.Scanner;

public class Program {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Config config = new Config();
        Database db = new Database(config);
        Router router = new Router();
        db.establishConnection();
        String currentDirectory = "";
        Teller currentTeller = new Teller(currentDirectory);

        while(!currentTeller.loggedIN){
            router.route(currentTeller, scanner);
            while(currentTeller.loggedIN){
                currentTeller = router.route(currentTeller, scanner);
            }
        }
        scanner.close();
        System.exit(0);
    }


    private static void reviewAccounts(Customer customer) {
        System.out.println("Reviewing client accounts...");
        for (Account account : customer.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
        }
    }

    private static void createAccount(Customer customer) {
        System.out.println("Creating a new account...");
        System.out.println("1. Personal Account");
        System.out.println("2. ISA Account");
        System.out.println("3. Business Account");
        System.out.print("Enter your choice (1, 2, 3): ");

        int choice = scanner.nextInt();
        int accountNumber = 0;
        double initialBalance = 0.0;

        switch (choice) {
            case 1:
                customer.addAccount(new Personal(accountNumber, initialBalance));
                break;
            case 2:
                customer.addAccount(new ISA(accountNumber, initialBalance));
                break;
            case 3:
                customer.addAccount(new Business(accountNumber, initialBalance));
                break;
            default:
                System.out.println("Invalid choice. Account not created.");
        }
    }
}
