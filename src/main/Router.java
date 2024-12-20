package main;
import Pages.*;
import java.util.Scanner;

public class Router {

    public Router(){}

    Teller route(Teller currentTeller, Scanner scanner){
        // check if current teller has logged out
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

            case "home/customers/accounts":
                currentTeller = AccountSelector.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/accounts/ISAAccount":
                currentTeller = ExistingISAAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/accounts/PersonalAccount":
                currentTeller = ExistingPersonalAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/accounts/BusinessAccount":
                currentTeller = ExistingBusinessAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/createCustomer":
                currentTeller = NewCustomerPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/accounts/NewISAAccount":
                currentTeller = NewISAPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/accounts/NewBusinessAccount":
                currentTeller = NewBusinessAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "home/customers/accounts/NewPersonalAccount":
                currentTeller = NewPersonalAccountPage.display(currentTeller, scanner);
                return currentTeller;

            case "logout":
                // TODO: clear the teller object of teller specific info
                currentTeller.currentDirectory = "";
                currentTeller.loggedIN = false;
                return currentTeller;
        }
        return currentTeller;
    }
}
