package com.example.javaassignmenttwo;

import java.sql.*;
import java.util.ArrayList;

public class SqliteHelper {
    // Setup
    static final String URL = "jdbc:sqlite:src/main/resources/Chinook_Sqlite.sqlite";
    Connection conn = null;



    //---reading all customers from database
    public ArrayList<Customer> selectAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    //---Read a specific customer based on ID
    public Customer selectSpecificCustomerID(String customerId){
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email " +
                            "FROM Customer WHERE CustomerId = ?");
            preparedStatement.setString(1, customerId); // Corresponds to 1st '?' (must match type)
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                );
            }

        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customer;
        }
    }

    //---Read a specific customer based on name
    public ArrayList<Customer>  selectSpecificCustomersName(String customerFirstName){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Customer customer = null;
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email " +
                            "FROM Customer WHERE FirstName = ?");
            preparedStatement.setString(1, customerFirstName); // Corresponds to 1st '?' (must match type)
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                        resultSet.getString("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Country"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Email")
                        ));
            }

        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    //---Choose a subset of customers
    public ArrayList<Customer> selectSubsetOfCustomers(int offset,int limit){
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT CustomerId,FirstName,LastName,Country,PostalCode,Phone,Email FROM Customer WHERE CustomerId >= ? LIMIT ? ");

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }

    //---reading all user invoice from database
    public ArrayList<CustomerInvoice> selectAllInvoiceByID(String id){
        ArrayList<CustomerInvoice> customerInvoices = new ArrayList<CustomerInvoice>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT InvoiceId,CustomerId FROM Invoice WHERE CustomerId = ?");

            preparedStatement.setString(1, id);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customerInvoices.add(
                        new CustomerInvoice(
                                resultSet.getString("InvoiceId"),
                                resultSet.getString("CustomerId")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customerInvoices;
        }
    }

    //---reading invoicelines from database for userinvoice
    public ArrayList<InvoiceLine> selectUserInvoiceLines(String id) {
        ArrayList<InvoiceLine> UserInvoiceLines = new ArrayList<InvoiceLine>();

            try {
                // Open Connection
                conn = DriverManager.getConnection(URL);
                System.out.println("Connection to SQLite has been established.");

                // Prepare Statement
                PreparedStatement preparedStatement =
                        conn.prepareStatement("SELECT InvoiceId,TrackId FROM InvoiceLine WHERE InvoiceId = ?");
                preparedStatement.setString(1, id);
                // Execute Statement
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process Results
                while (resultSet.next()) {
                    UserInvoiceLines.add(
                            new InvoiceLine(
                                    resultSet.getString("InvoiceId"),
                                    resultSet.getString("TrackId")
                            ));
                }
            } catch (Exception ex) {
                System.out.println("Something went wrong...");
                System.out.println(ex.toString());
            } finally {
                try {
                    // Close Connection
                    conn.close();
                } catch (Exception ex) {
                    System.out.println("Something went wrong while closing connection.");
                    System.out.println(ex.toString());
                }
            }

        return UserInvoiceLines;
    }
    //---reading all tracks from database
    public ArrayList<Track> selectTracks(String trackID){
        ArrayList<Track> Tracks = new ArrayList<Track>();

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,TrackId FROM Track WHERE TrackId=?");

            preparedStatement.setString(1, trackID);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                Tracks.add(
                        new Track(
                                resultSet.getString("GenreId"),
                                resultSet.getString("TrackId")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return Tracks;
        }
    }

    //---reading all genre from database
    public Genre selectFavGenre(String genreID){
        Genre genre = null;

        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT GenreId,Name FROM Genre WHERE GenreId = ?");
            preparedStatement.setString(1, genreID);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                genre = new Genre(
                                resultSet.getString("GenreId"),
                                resultSet.getString("Name")
                        );
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return genre;
        }
    }

    // task 5, add a new customer
    public Customer addNewCustomer(Customer newCustomer) throws SQLException
    {
        try {
            // newCustomer = new Customer("0", "Test", "Testerson", "Denmark", "2600", "555-555-55", "test@test.com");
            PreparedStatement insertCustomerStatement = conn.prepareStatement("INSERT INTO Customer(FirstName, LastName, Country, PostalCode, Phone, Email) VALUES (?,?,?,?,?,?)");

            // Inserting values from the inquired customer instance pushed through via the parameter of this function.
            insertCustomerStatement.setString(1, newCustomer.getCustomerFirstname());
            insertCustomerStatement.setString(2, newCustomer.getCustomerLastname());
            insertCustomerStatement.setString(3, newCustomer.getCustomerCountry());
            insertCustomerStatement.setString(4, newCustomer.getCustomerPostalCode());
            insertCustomerStatement.setString(5, newCustomer.getCustomerPhoneNumber());
            insertCustomerStatement.setString(6, newCustomer.getCustomerEmail());

            insertCustomerStatement.executeUpdate();

            // prepare another statement for testing purposes. Only used to see, if the new customer was really added.
            PreparedStatement checkResultStatement = conn.prepareStatement("SELECT CustomerId,FirstName,LastName FROM Customer WHERE FirstName LIKE ?");
            checkResultStatement.setString(1, "Test");
            ResultSet resultset = checkResultStatement.executeQuery();

            int id = resultset.getInt("CustomerId");
            String firstname = resultset.getString("FirstName");
            String lastname = resultset.getString("LastName");
            System.out.printf("Customer {%d,%s,%s} has now been added to the Customer table \n", id, firstname, lastname);
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return newCustomer;
        }
    }

    // task 6, update an existing customer
    public Customer updateExistingCustomer(Customer existingCustomer) throws SQLException
    {
        try {
            // doesn't expose potential user of the application to overwrite information about himself, unfortunately hardcoded code.

            //Customer newCustomer = new Customer(-1, "Testy", "Testeron", "Denmark", "2600", "555-555-55", "test@test.com");
            PreparedStatement updateCustomerStatement = conn.prepareStatement("UPDATE Customer SET FirstName = 'Testy', LastName = 'Testeron', Country = 'Denmark', PostalCode = '2600', Phone = '+4545043112', Email = 'test.com' WHERE FirstName = 'Test'");
            //insertCustomerStatement.setInt(1,newCustomer.getId());

            updateCustomerStatement.executeUpdate();

            PreparedStatement checkResultStatement = conn.prepareStatement("SELECT CustomerId,FirstName,LastName FROM Customer WHERE FirstName LIKE ?");
            checkResultStatement.setString(1, "Testy");
            ResultSet resultset = checkResultStatement.executeQuery();

            int id = resultset.getInt("CustomerId");
            String firstname = resultset.getString("FirstName");
            String lastname = resultset.getString("LastName");
            System.out.printf("Customer {%d,%s,%s} has now been updated in the Customer table \n", id, firstname, lastname);
        }
        finally {
        try {
            // Close Connection
            conn.close();
        }
        catch (Exception ex){
            System.out.println("Something went wrong while closing connection.");
            System.out.println(ex.toString());
        }
        return existingCustomer;
    }
    }

    // task 8, order customers after, who has spent the most money on products.
    public ArrayList<CustomerSpender> orderCustomerByBiggestSpender() throws SQLException
    {
        ArrayList<CustomerSpender> queryResult = new ArrayList<CustomerSpender>();

        try
        {
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement prepStatement = conn.prepareStatement("SELECT FirstName, LastName, Customer.CustomerId, SUM(Total) FROM Customer INNER JOIN Invoice ON Customer.CustomerId = Invoice.CustomerId GROUP BY Customer.FirstName, Customer.LastName, Customer.CustomerId ORDER BY SUM(Total) DESC;");
            ResultSet resultset = prepStatement.executeQuery();

            while (resultset.next())
            {
                String firstName = resultset.getString("FirstName");
                String lastName = resultset.getString("LastName");
                double totalCost = resultset.getDouble("SUM(Total)");

                // Add the results to be used in the API endpoint.
                queryResult.add(new CustomerSpender(firstName, lastName, totalCost));

                System.out.printf("Customer name and how much they have spend in total {%s,%s,%f} \n", firstName, lastName, totalCost);
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            } catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return queryResult;
        }
    }

    // task 7, query the number of registered customer living in different country's.
    public ArrayList<CustomerCountry> orderCustomerByCountry() throws SQLException
    {
        ArrayList<CustomerCountry> queryResult = new ArrayList<CustomerCountry>();

        try {
            Connection conn = ConnectionManager.getInstance().getConnection();
            PreparedStatement prepStatement = conn.prepareStatement("SELECT Country,COUNT(CustomerId) FROM Customer GROUP BY Country ORDER BY COUNT(CustomerId) DESC");
            ResultSet resultset = prepStatement.executeQuery();

            while (resultset.next()) {
                String country = resultset.getString("Country");
                int numberOfCustomers = resultset.getInt("COUNT(CustomerId)");

                queryResult.add(new CustomerCountry(country, numberOfCustomers));

                System.out.printf("Country and number of Customers {%s,%d} \n", country, numberOfCustomers);
            }
        }
        catch (Exception ex) {
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex) {
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }

            return queryResult;
        }
    }
}
