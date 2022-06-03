package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
    boolean insertCustomer(Customer customer) throws SQLException;

    Customer selectCustomer(int id);

    List<Customer> selectAllCustomers();

    boolean deleteUser(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    Customer selectCustomerByName(String name);
}
