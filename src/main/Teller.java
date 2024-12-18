package main;
public class Teller {
    String firstName;
    String lastName;
    public String currentDirectory;
    public String userName;
    int teller_id;
    public Boolean loggedIN;

    public Teller(String setDirectory){
        this.currentDirectory = setDirectory;
        this.loggedIN = false;
    }

    public Boolean Login(){
        return true;

    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.firstName;
    }
    public String getUsername(){
        return this.userName;
    }

    public String getPassword(){
        return this.firstName;
    }
    public Boolean Logout(){
        return true;
    }

    public int getID() {
        return this.teller_id;
    }

    public void createAccount(String firstName, String lastName, String password){

    }
}
