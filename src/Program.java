import java.util.Scanner;

public class Program {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("""
               CLI BANKING SYSTEM
               Review client accounts (1)
               Create a new account (2)
               Exit (3)
               Enter your choice, (1,2,3):
                """);
        //amateur CLI options placeholder
        boolean exit = false;
        while (!exit) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Reviewing client accounts...");
                        break;
                    case 2:
                        System.out.println("Creating a new account...");
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        exit = true;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
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
