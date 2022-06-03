package service;

import model.Customer;
import model.Deposit;
import model.Withdraw;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class WithdrawService implements IWithdrawService{

    private final CustomerService customerService = new CustomerService();

//    private String jdbcURL = "jdbc:mysql://localhost:3306/banking";
//    private String jdbcUsername = "root";
//    private String jdbcPassword = "25251325";


    private static final String INSERT_WITHDRAWS_SQL = "{call sp_withdraw_money(?, ?,?)}";

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT id,full_name,email,phone,address,balance FROM customers WHERE id =?";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_CUSTOMERS_SQL = "UPDATE customers SET full_name = ?,email= ?, phone =?, address =? WHERE id = ?;";

    @Override
    public String insert(Withdraw withdraw) throws SQLException {
        try{
            Connection connection = customerService.getConnection();
            CallableStatement callableStatement = connection.prepareCall(INSERT_WITHDRAWS_SQL);
            callableStatement.setInt(1, withdraw.getCustomerId());
            callableStatement.setLong(2, withdraw.getTransactionAmount());
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.execute();
//            callableStatement.executeUpdate();
            String message = callableStatement.getString(3);
            return message;

        } catch (SQLException e) {
            customerService.printSQLException(e);
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
    public boolean update(Withdraw withdraw) throws SQLException {
        return false;
    }
}
