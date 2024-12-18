import java.sql.*;
public class Database {
    private String dbUsername;
    private String dbPassword;
    private String connectionUrl;
    private Connection con;

    // create a BankingAppDB within your azure data studios
    // DB == BankingAppDB

    public Database(Config config){
        this.dbUsername = config.userName;
        this.dbPassword = config.password;
        this.connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=BankingAppDB;user=" + this.dbUsername + ";password=" + this.dbPassword + ";encrypt=false;";
    }



    public Boolean establishConnection(){
        try {
            System.out.println(this.connectionUrl);
            this.con = DriverManager.getConnection(this.connectionUrl);
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}

// this class wil hold all the CRUD operations for interacting with the DB

