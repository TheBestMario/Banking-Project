package main;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Database {
    private String dbUsername;
    private String dbPassword;
    private String connectionUrl;
    private static Connection con;

    // create a BankingAppDB within your azure data studios
    // DB == BankingAppDB

    public Database(Config config){
        this.dbUsername = config.userName;
        this.dbPassword = config.password;
        this.connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=BankingAppDB;user=" + this.dbUsername + ";password=" + this.dbPassword + ";encrypt=false;";
    }

    public Boolean establishConnection() {
        try {
            System.out.println(this.connectionUrl);
            this.con = DriverManager.getConnection(this.connectionUrl);
            return true;
        } catch (SQLException e) {
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
                String phone_number = rs.getString("mobile_number");
                LocalDate dob = rs.getDate("DOB").toLocalDate();
                String email = rs.getString("email");
                int customer_id = rs.getInt("id");

                Customer customer = new Customer(customer_id,
                        firstName, lastName,
                        photo_proof, address_proof,
                        phone_number, email,
                        business_proof, dob);

                 return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBalance(int accountId, double newBalance) throws SQLException {
        String query = "UPDATE Accounts SET balance = ?, dateUpdated = GETDATE() WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setDouble(1, newBalance);
            st.setInt(2, accountId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCustomer(Customer customer){
        String query = "INSERT INTO Customers (firstName,lastName,photo_proof,address_proof,business_proof,DOB, mobile_number, email)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, customer.getFirstName());
            st.setString(2, customer.getLastName());
            st.setString(3, customer.getPhoto_proof());
            st.setString(4, customer.getAddress_proof());
            st.setString(5, customer.getBusiness_proof());
            st.setDate(6, java.sql.Date.valueOf(customer.getDob()));
            st.setString(7, customer.getPhone_number());
            st.setString(8, customer.getEmail());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getCustomerId(String firstName, String lastName, String number, String email) {
        String query = "SELECT id FROM Customers WHERE firstName = ? AND lastName = ? AND mobile_number = ? AND email = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, firstName);
            st.setString(2, lastName);
            st.setString(3, number);
            st.setString(4, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List <Personal> getPersonalAccount(int customerId) {
        List<Personal> personalAccounts = new ArrayList<>();

        String query = """ 
                SELECT a.id AS account_id, a.balance,a.dateCreated,pa.bank_address
                FROM Accounts a
                JOIN Account_Type at ON a.type_id = at.id
                JOIN Personal_Accounts pa ON at.personal_account_id = pa.id
                WHERE a.id = ? AND a.isDeleted = 0
                """;
        try (PreparedStatement st = con.prepareStatement(query)){
            st.setInt(1,customerId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                personalAccounts.add(new Personal(
                        rs.getInt("id"),
                        rs.getDouble("balance"),
                        rs.getInt("customer_id"),
                        rs.getString("bank_address")
                ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return personalAccounts;


    }
    public Boolean setBusinessProof(int customerId, String businessProof) {
        String query = "UPDATE Customers SET business_proof = ? WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, businessProof);
            st.setInt(2, customerId);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List <Business> getBusinessAccount(int customerId) {
        List <Business> businessAccounts = new ArrayList<>();

        String query = """ 
                SELECT a.id AS account_id, a.balance,a.dateCreated,ba.business_details, ba.has_Cheque_Books
                FROM Accounts a
                JOIN Account_Type at ON a.type_id = at.id
                JOIN Personal_Accounts ba ON at.business_account_id = ba.id
                WHERE a.id = ? AND a.isDeleted = 0
                """;


        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1,customerId);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                businessAccounts.add(new Business(
                        rs.getInt("account_id"),
                rs.getDouble("balance"),
                rs.getString("bank_details"),
                rs.getInt("customer_id"),
                        rs.getBoolean("has_Cheque_Books"),
                        rs.getDouble("initial_deposit")
                ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return businessAccounts;


    }
    //write
    public Personal getPersonalAccountById(int accountId) throws SQLException {
        String query = """
            SELECT a.id AS account_id, a.balance, pa.bank_address
            FROM Accounts a
            JOIN Account_Type at ON a.type_id = at.id
            JOIN Personal_Accounts pa ON at.personal_account_id = pa.id
            WHERE a.id = ? AND a.isDeleted = 0
            """;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("account_id");
                double balance = rs.getDouble("balance");
                int customerId = rs.getInt("customer_id");
                String bankAddress = rs.getString("bank_address");

                return new Personal(id, balance,customerId, bankAddress);
            }
        }
        return null; // Account not found
    }

//write
    public Business getBusinessAccountById(int accountId) throws SQLException {
        String query = """
            SELECT a.id AS account_id, a.balance, ba.business_details, ba.has_Cheque_Books
            FROM Accounts a
            JOIN Account_Type at ON a.type_id = at.id
            JOIN Business_Accounts ba ON at.business_account_id = ba.id
            WHERE a.id = ? AND a.isDeleted = 0
            """;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("account_id");
                double balance = rs.getDouble("balance");
                String businessDetails = rs.getString("business_details");
                int customerId = rs.getInt("customer_id");
                boolean hasChequeBooks = rs.getBoolean("has_Cheque_Books");
                double initialDeposit = rs.getDouble("initial_deposit");

                return new Business(id, balance, businessDetails,customerId,hasChequeBooks, initialDeposit);
            }
        }
        return null; // Account not found
    }




    public static int createPersonalAccount(Personal account) throws SQLException {
        String query = "INSERT INTO Accounts (customer_id,type_id, initial_deposit, balance, dateCreated, dateUpdated, isDeleted)" +
                "OUTPUT INSERTED.id VALUES (?,?,?,?, GETDATE(),GETDATE(),0)";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, account.getCustomerId());//replace with customer id
            st.setDouble(2, 1); //type of id
            st.setDouble(3, account.getInitialBalance());
            st.setDouble(4, account.getBalance());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // return the account id

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;

    }

    public static int createBusinessAccount(Business account) throws SQLException {
        String query = "INSERT INTO Accounts (type_id,customer_id,initial_deposit, balance, dateCreated, dateUpdated, isDeleted)" +
                "VALUES (?,?,?, GETDATE(),GETDATE(),0); "
                +"insert into Business_Accounts (business_details, has_Cheque_Books) VALUES (?,?);"
                +"SELECT id from accounts where id = (select max(id) from accounts)";
        try (PreparedStatement st = con.prepareStatement(query)) {
            //st.setInt(1, account.getCustomerId());//replace with customer id
            //st.setDouble(1, 2); //type of id
            st.setDouble(1, account.getInitialDeposit());
            st.setDouble(2, account.getBalance());
            st.setString(3, account.getBusinessDetails());
            st.setBoolean(4, account.hasChequeBooks());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // return the account id

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /* ------------------------- ISA Queries ----------------------------------------------------*/

    public boolean hasExistingISAAccount(int customerId) {
        System.out.println("Customer id" + customerId);
        String query = """
                SELECT COUNT(*)
                FROM Accounts a
                WHERE a.customer_id = ?
                AND a.ISA_account_id IS NOT NULL
                """;

        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, customerId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while checking for an existing ISA account.");

        }

        return false;
    }



    public boolean saveISAAccount(int isaTypeId, int customerId, double initialDeposit) {
        // Query to insert into ISA_Accounts
        String insertISAQuery = "INSERT INTO ISA_Accounts (type_id, currentBalance, dateCreated, threshold) VALUES (?, ?, ?, ?)";
        String insertAccounts = "INSERT INTO Account (customer_id, ISA_account_id, initial_deposit, balance, dateCreated, dateUpdated) VALUES(?,?,?,?,?,?,)";

        try (PreparedStatement insertISAStmt = con.prepareStatement(insertISAQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Set for ISA_Accounts
            insertISAStmt.setInt(1, isaTypeId);
            insertISAStmt.setDouble(2, initialDeposit);
            insertISAStmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            insertISAStmt.setInt(4, 0);

            // Execute the insert statement
            int rowsInserted = insertISAStmt.executeUpdate();

            // Check if the insert was successful
            if (rowsInserted > 0) {
                // Retrieve the generated ISA_Accounts ID
                ResultSet rs = insertISAStmt.getGeneratedKeys();
                if (rs.next()) {
                    int newISAAccountId = rs.getInt(1);


                    try (PreparedStatement Accounts = con.prepareStatement(insertAccounts)) {
                        Accounts.setInt(1, customerId);
                        Accounts.setInt(2, newISAAccountId);
                        Accounts.setDouble(3, initialDeposit);
                        Accounts.setDouble(4,initialDeposit); // balance set as initial deposit
                        Accounts.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
                        Accounts.setDate(6, java.sql.Date.valueOf(LocalDate.now()));


                        // Execute the update statement
                        int rowsUpdated = Accounts.executeUpdate();

                        // Confirm if the update was successful
                        if (rowsUpdated > 0) {
                            System.out.println("Successfully created ISA account and linked it to the customer.");
                            return true;
                        } else {
                            System.out.println("Failed to update the Accounts table with the new ISA account.");
                        }
                    }
                }
            } else {
                System.out.println("Failed to insert into ISA_Accounts.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while creating the ISA account.", e);
        }

        return false; // Return false if any part of the process fails
    }

    public boolean checkLimit(int isaTypeId, double initialDeposit) {
        String limitQuery = "SELECT limit FROM ISA_Types WHERE id = ?";

        try (PreparedStatement st = con.prepareStatement(limitQuery)) {
            st.setInt(1, isaTypeId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                double limit = rs.getDouble("limit");

                if (initialDeposit > limit) {
                    System.out.println("Initial deposit exceeds the limit for this ISA account.");
                    return true;
                } else {
                    System.out.println("This deposit is within the limit of the ISA account.");
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public double getISABalance(int customerId) {
        String query = """
                SELECT currentBalance FROM ISA_ACCOUNTS isa
                LEFT JOIN Accounts a  ON a.ISA_account_id = isa.id
                WHERE a.customer_id = ?
                """;

        try(PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1,customerId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("currentBalance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("No balance returned");
        return 0;
    }

    public boolean updateISABalance(int customerId, double newBalance){
        String query = """
                SELECT isa.id FROM ISA_ACCOUNTS isa
                LEFT JOIN Accounts a ON a.ISA_account_id = isa.id
                WHERE a.customer_id = ?
                """;

        int isaAccountId =0; // Initialize variable to store results (isa account id) from above query



        try(PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, customerId);

            ResultSet rs = st.executeQuery();
            if(rs.next()){
                isaAccountId = rs.getInt("id"); // Store the isaAccountId in this variable. Will use this to update the current balance.
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Update currentBalance in ISAAccount Table
        String updateCurrentBalance = "UPDATE ISA_Accounts SET currentBalance = ?  WHERE id = ? ";

        try(PreparedStatement st = con.prepareStatement(updateCurrentBalance)) {
            st.setDouble(1,newBalance);
            st.setInt(2,isaAccountId);

            int updateRecord = st.executeUpdate();
            return updateRecord > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}

// this class wil hold all the CRUD operations for interacting with the DB