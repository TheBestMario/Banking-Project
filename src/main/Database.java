package main;
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

    public void closeConnection(){
        try {
            this.con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //TELLERS
    public void createTeller(Teller teller) {
        String query = "INSERT INTO Tellers (firstName,LastName, password) VALUES (?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, teller.getFirstName());
            st.setString(2, teller.getLastName());
            st.setString(3, teller.getPassword());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Teller getTeller(String username) {
        String query = "SELECT * FROM Tellers WHERE username = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1,username);
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateTeller(Teller teller) {
        String query = "UPDATE Tellers SET password = ?, firstName = ?, lastName = ? WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, teller.getPassword());
            st.setString(2, teller.getFirstName());
            st.setString(3, teller.getLastName());
            st.setInt(4, teller.getID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteTeller(String username) {
        String query = "DELETE FROM Tellers WHERE username = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, username);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

// this class wil hold all the CRUD operations for interacting with the DB

