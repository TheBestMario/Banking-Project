import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
               CLI BANKING SYSTEM
               Review client accounts (1)
               Create a new account (2)
               Exit (3)
               Enter your choice, (1,2,3):
                """);
        //amateur CLI options placeholder
        boolean validChoice = false;
        while (!validChoice) {
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Reviewing client accounts...");
                        validChoice = true;
                        break;
                    case 2:
                        System.out.println("Creating a new account...");
                        validChoice = true;
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        validChoice = true;
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, use a number from the list given");
            }
        }
    }
}
