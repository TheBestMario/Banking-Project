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
                currentTeller = HelpPage.display(currentTeller, scanner);
                return currentTeller;

            case "createTeller":
                currentTeller = CreateAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home":
                // routes to home page, shows home page menu with options
                currentTeller = CustomersPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers":
                currentTeller = SelectCustomerPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/ISAAccount":
                currentTeller = ExistingISAAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/PersonalAccount":
                currentTeller = ExistingPersonalAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/BusinessAccount":
                currentTeller = ExistingBusinessAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/createCustomer":
                break;

            case "home/customers/createISAAccount":
                currentTeller = NewISAPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/createBusinessAccount":
                break;

            case "home/customers/createPersonalAccount":
                // personal account creation page goes here..
                break;

            case "logout":
                // logouts out of the system...
                break;
        }
        return currentTeller;
    }
}
