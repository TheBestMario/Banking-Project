package main;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        String query = "INSERT INTO Tellers (firstName,lastName,userName, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, teller.getFirstName());
            st.setString(2, teller.getLastName());
            st.setString(3, teller.getUserName());
            st.setString(4, teller.getPassword());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Teller getTeller(String username, String password) {
        String query = "SELECT * FROM Tellers WHERE username = ? and password = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1,username);
            st.setString(2,password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Teller teller = new Teller("", this);
                teller.setFirstName(rs.getString("firstName"));
                teller.setLastName(rs.getString("lastName"));
                teller.setUserName(rs.getString("username"));
                teller.setPassword(rs.getString("password"));
                teller.teller_id = rs.getInt("id");
                return teller;

            }

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
    //CUSTOMERS
//    public void createCustomer(Customer customer) {
//        String query = "INSERT INTO Customers (firstName,lastName,photo_proof,address_proof,business_proof,DOB) VALUES (?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement st = con.prepareStatement(query)) {
//            st.setString(1, customer.getFirstName());
//            st.setString(2, customer.getLastName());
//            st.setString(3, customer.getPhoto());
//            st.setString(4, customer.getAddress());
//            st.setString(5, customer.getBusiness());
//            st.setString(6, customer.getDOB());
//            st.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void displayAllCustomers() {
        String query = "SELECT * FROM Customers";
        try (PreparedStatement st = con.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                System.out.println(id + ": " + firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No customers found");
        }
    }

    public Customer getCustomer(int id) {
        String query = "SELECT * FROM Customers where id = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String photo_proof = rs.getString("photo_proof");
                String address_proof = rs.getString("address_proof");
                String business_proof = rs.getString("business_proof");
                Date dob = rs.getDate("DOB");

                Customer customer = new Customer(firstName, lastName, photo_proof, address_proof, business_proof,dob);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List <Personal> getPersonalAccount(int customerId) {

        String query = """ 
                SELECT a.id AS account_id, a.balance,a.dateCreated,pa.bank_address
                FROM Accounts a
                JOIN Account_Type at ON a.type_id = at.id
                JOIN Personal_Accounts pa ON at.personal_account_id = pa.id
                WHERE a.account_id = ? AND a.isDeleted = 0
                """;
        List <Personal> personalAccounts = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(query)){
            st.setInt(1,customerId);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                int accountId = rs.getInt("account_id");
                double balance = rs.getDouble("balance");
                String bankAddress = rs.getString("bank_address");

                Personal personalAccount = new Personal(accountId,balance,bankAddress);
                personalAccounts.add(personalAccount);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return personalAccounts;


    }

    public List <Business> getBusinessAccount(int customerId) {

        String query = """ 
                SELECT a.id AS account_id, a.balance,a.dateCreated,ba.business_details, ba.has_Cheque_Books
                FROM Accounts a
                JOIN Account_Type at ON a.type_id = at.id
                JOIN Personal_Accounts ba ON at.business_account_id = ba.id
                WHERE a.account_id = ? AND a.isDeleted = 0
                """;
        List <Business> businessAccounts = new ArrayList<>();

        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1,customerId);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                int accountId = rs.getInt("account_id");
                double balance = rs.getDouble("balance");
                String businessDetails = rs.getString("bank_details");
                boolean hasChequeBooks = rs.getBoolean("has_Cheque_Books");

                Business businessAccount = new Business(accountId,balance,businessDetails,hasChequeBooks);
                businessAccounts.add(businessAccount);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return businessAccounts;


    }





}

// this class wil hold all the CRUD operations for interacting with the DB

