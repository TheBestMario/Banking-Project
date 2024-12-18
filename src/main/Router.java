package main;
import Pages.*;
import java.util.Scanner;

public class Router {

    public Router(){}

    Teller route(Teller currentTeller, Scanner scanner){
        switch (currentTeller.currentDirectory){
            case "":
                currentTeller = StartMenuPage.display(currentTeller, scanner);
                return currentTeller;
            case "login":
                currentTeller = LoginPage.display(currentTeller, scanner);
                return currentTeller;
            case "help":
                HelpPage.display(scanner);
                break;
            case "createTeller":
                currentTeller = CreateAccountPage.display(currentTeller, scanner);
                return currentTeller;
            case "/home":
                currentTeller = CustomersPage.display(currentTeller, scanner);
                // routes to home page, shows home page menu with options
                break;

            case "home/customers":
                // gets the customers for the teller
                break;



            case "logout":
                // logouts out of the system...
                break;
        }
        return currentTeller;
    }
}
