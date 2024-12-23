package main;
import Pages.*;
import java.util.Scanner;

public class Router {


    public Router() {

    }

    Teller route(Teller currentTeller, Scanner scanner){
        // check if current teller has logged out
        if(currentTeller.currentDirectory.contains("help")){
            currentTeller = HelpPage.display(currentTeller, scanner);
            return currentTeller;
        }
        else{
            System.out.println("directory:");
            System.out.println(currentTeller.currentDirectory);


            switch (currentTeller.currentDirectory){
                case "":
                    currentTeller = StartMenuPage.display(currentTeller, scanner);
                    return currentTeller;

                case "login":
                    currentTeller = LoginPage.display(currentTeller, scanner);
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
                    currentTeller = NewBusinessAccountPage.display(currentTeller,scanner);
                    return currentTeller;

                case "home/customers/accounts/NewPersonalAccount":
                    // personal account creation page goes here..
                    currentTeller = NewPersonalAccountPage.display(currentTeller,scanner);
                    return currentTeller;
                case "logout":
                    // logouts out of the system...
                    currentTeller.currentDirectory = "";
                    currentTeller.loggedIN = false;
                    currentTeller.clearUserSession();
                    return currentTeller;
            }
        }
        return currentTeller;
    }
}



/*


 case 1:
                        System.out.println("Selecting the existing ISA Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/ISAAccount";
                        return currentTeller;
                    case 2:
                        System.out.println("Selecting the existing Personal Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/PersonalAccount";
                        return currentTeller;
                    case 3:
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/BusinessAccount";
                        return currentTeller;
                    case 4:
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewISAAccount";
                        return currentTeller;
                    case 5:
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewPersonalAccount";
                        return currentTeller;
                    case 6:
                        System.out.println("Selecting the existing Business Account for this customer");
                        currentTeller.currentDirectory = "home/customers/accounts/NewBusinessAccount";
                        return currentTeller;
                    case 7:
                        System.out.println("Going Back");
                        currentTeller.currentDirectory = "home/customers";
                        return currentTeller;





 */