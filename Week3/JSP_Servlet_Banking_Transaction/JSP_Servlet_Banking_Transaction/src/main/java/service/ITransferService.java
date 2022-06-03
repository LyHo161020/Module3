package service;

import model.Customer;
import model.Transfer;
import model.TransferInfo;
import model.Withdraw;

import java.sql.SQLException;
import java.util.List;

public interface ITransferService {
    String insert(Transfer transfer) throws SQLException;

//    Customer selectCustomerByName(String name);

    List<Customer> selectAll();

    boolean delete(int id) throws SQLException;

    boolean update(Transfer transfer) throws SQLException;

    List<TransferInfo> selectAllTransfers();
}
