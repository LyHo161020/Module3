package service;

import model.Customer;
import model.Deposit;
import model.Withdraw;

import java.sql.SQLException;
import java.util.List;

public interface IWithdrawService {
    String insert(Withdraw withdraw) throws SQLException;

    Customer selectById(int id);

    List<Customer> selectAll();

    boolean delete(int id) throws SQLException;

    boolean update(Withdraw withdraw) throws SQLException;

//    String insertMessage(Deposit deposit);
}
