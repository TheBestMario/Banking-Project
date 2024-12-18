import Pages.CustomersPage;
import java.util.Scanner;

public class Router {

    public Router(){}

    String route(String route, Scanner scanner){
        switch (route){
            case "login":



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
