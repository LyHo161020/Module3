package service;

import model.Customer;
import model.Deposit;

import java.sql.SQLException;
import java.util.List;

public interface IDepositsService {
    boolean insert(Deposit deposit) throws SQLException;

    Customer selectById(int id);

    List<Customer> selectAll();

    boolean delete(int id) throws SQLException;

    boolean update(Deposit deposit) throws SQLException;

    String insertMessage(Deposit deposit);
}
