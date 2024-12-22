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

            //after debugging, after you try to log in with correct account the currentTeller object
            //is not being updated with the object returned by Login > Router > Program in the line underneath
            //so I made it update the object outside the second while loop
            currentTeller = router.route(currentTeller, scanner);
            while (currentTeller.loggedIN){
                currentTeller = router.route(currentTeller, scanner);
            }
        }
        scanner.close();
        System.exit(0);
    }
}
