import Pages.*;

import java.util.Scanner;

public class Router {

    public Router(){}

    String route(String route, Scanner scanner){
        switch (route){
            case "":
                StartMenuPage.display(scanner);
                break;
            case "login":
                LoginPage.display(scanner);
                break;
            case "help":
                HelpPage.display(scanner);
                break;
            case "createTeller":
                CreateAccountPage.display(scanner);
                break;
            case "/home":
                // routes to home page, shows home page menu with options
                CustomersPage.display(scanner);
                break;

            case "home/customers":
                // gets the customers for the teller
                break;

            case "logout":
                // logouts out of the system...
                break;

        }
        return "";
    }
}
