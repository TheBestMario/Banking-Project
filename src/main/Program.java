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
}
