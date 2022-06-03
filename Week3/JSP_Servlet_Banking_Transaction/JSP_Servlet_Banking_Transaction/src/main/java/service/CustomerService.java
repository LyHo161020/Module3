package service;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements  ICustomerService{

    private String jdbcURL = "jdbc:mysql://localhost:3306/banking";
    private String jdbcUsername = "root";
    private String jdbcPassword = "25251325";


    private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO customers" + "  (full_name, email, phone,address,balance) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT id,full_name,email,phone,address,balance FROM customers WHERE id =?";
    private static final String SELECT_CUSTOMER_BY_NAME = "SELECT id,full_name,email,phone,address,balance FROM customers WHERE full_name = ?";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_CUSTOMERS_SQL = "UPDATE customers SET full_name = ?,email= ?, phone =?, address =? WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean insertCustomer(Customer customer) throws SQLException {
        boolean inserted = false;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setLong(5, customer.getBalance());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            inserted = true;

        } catch (SQLException e) {
            printSQLException(e);
        }
        return inserted;
    }

    @Override
    public Customer selectCustomer(int id) {
        Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                long balance = Long.parseLong(rs.getString("balance"));
                customer = new Customer(id, fullName, email, phone,address,balance);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Customer> customers = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (
                Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                long balance = Long.parseLong(rs.getString("balance"));
                customers.add(new Customer(id, fullName, email, phone,address,balance));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean updated = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);) {
            statement.setString(1, customer.getFullName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getId());

            statement.executeUpdate();
            updated = true;
        }catch (SQLException e) {
            printSQLException(e);
        }
        return updated;
    }

    @Override
    public Customer selectCustomerByName(String name) {
        Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_NAME);) {
            preparedStatement.setString(1, name);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                long balance = Long.parseLong(rs.getString("balance"));
                customer = new Customer(id, name, email, phone,address,balance);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }
}
