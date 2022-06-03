package service;

import model.Customer;
import model.Deposit;

import java.sql.*;
import java.util.List;

public class DepositsService implements IDepositsService{

    private String jdbcURL = "jdbc:mysql://localhost:3306/banking";
    private String jdbcUsername = "root";
    private String jdbcPassword = "25251325";

    private static final String INSERT_DEPOSITS_SQL = "INSERT INTO deposits" + "  (customer_id,transaction_amount) VALUES " +
            " (?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT id,full_name,email,phone,address,balance FROM customers WHERE id =?";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_CUSTOMERS_SQL = "UPDATE customers SET full_name = ?,email= ?, phone =?, address =? WHERE id = ?;";

    private static final String INSERT_DEPOSITS_SQL1 = "  {call sp_send_money(?, ?,?)}";

    public Connection getConnection() {
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


//    @Override
//    public boolean insert(Deposit deposit) throws SQLException {
//        boolean inserted = false;
//        // try-with-resource statement will auto close the connection.
//        try {
//            Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPOSITS_SQL);
//            preparedStatement.setInt(1, deposit.getCustomerId());
//            preparedStatement.setLong(2, deposit.getTransactionAmount());
//            preparedStatement.executeUpdate();
//            inserted = true;
//
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return inserted;
//    }

    @Override
    public boolean insert(Deposit deposit) throws SQLException {
        boolean inserted = false;
        // try-with-resource statement will auto close the connection.
        try{
                Connection connection = getConnection();
                CallableStatement callableStatement = connection.prepareCall(INSERT_DEPOSITS_SQL1);
                callableStatement.setInt(1, deposit.getCustomerId());
                callableStatement.setLong(2, deposit.getTransactionAmount());
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.executeUpdate();

                inserted = true;

        } catch (SQLException e) {
            printSQLException(e);
        }
        return inserted;
    }

    @Override
    public String insertMessage(Deposit deposit) {
        // try-with-resource statement will auto close the connection.
        try{
            Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(INSERT_DEPOSITS_SQL1);
            callableStatement.setInt(1, deposit.getCustomerId());
            callableStatement.setLong(2, deposit.getTransactionAmount());
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.execute();
//            callableStatement.executeUpdate();
            String message = callableStatement.getString(3);
            return message;

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    @Override
    public Customer selectById(int id) {
        return null;
    }

    @Override
    public List<Customer> selectAll() {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Deposit deposit) throws SQLException {
        return false;
    }

    private void printSQLException(SQLException ex) {
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
}
