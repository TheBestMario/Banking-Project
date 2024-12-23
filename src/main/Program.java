package main;
import java.util.Scanner;

public class Program {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Config config = new Config();
        Database db = new Database(config);
        Router router = new Router();
        db.establishConnection();
        Teller currentTeller = new Teller("", db);

        while(!currentTeller.loggedIN && currentTeller.ApplicationON){
            currentTeller = router.route(currentTeller, scanner);
            while (currentTeller.loggedIN){
                currentTeller = router.route(currentTeller, scanner);
            }
        }
        scanner.close();
        System.exit(0);
    }
}
