package service;

import model.Customer;
import model.Transfer;
import model.TransferInfo;
import model.Withdraw;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferService implements ITransferService{
    private final CustomerService customerService = new CustomerService();

    private static final String CALL_WITHDRAWS_SQL = "{call sp_transfer(?, ?, ?, ?)}";

    private static final String SELECT_CUSTOMER_BY_NAME = "SELECT id,full_name,email,phone,address,balance FROM customers WHERE id =?";
    private static final String SELECT_ALL_TRANSFERS = "SELECT \n" +
            "\t trans.id,\n" +
            "\t send.id AS send_id,\n" +
            "     send.full_name AS send_name,\n" +
            "     rep.id AS rep_id,\n" +
            "     rep.full_name AS rep_name,\n" +
            "     trans.created_at,\n" +
            "     trans.deleted,\n" +
            "     trans.updated_at,\n" +
            "     trans.fees,\n" +
            "     trans.fees_amount,\n" +
            "     trans.transaction_amount,\n" +
            "     trans.transfer_amount\n" +
            "FROM transfers trans\n" +
            "JOIN customers rep ON trans.recipient_id = rep.id\n" +
            "JOIN customers send ON trans.sender_id = send.id;";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_CUSTOMERS_SQL = "UPDATE customers SET full_name = ?,email= ?, phone =?, address =? WHERE id = ?;";

    @Override
    public String insert(Transfer transfer) throws SQLException {
        try{
            Connection connection = customerService.getConnection();
            CallableStatement callableStatement = connection.prepareCall(CALL_WITHDRAWS_SQL);
            callableStatement.setInt(1, transfer.getSenderId());
            callableStatement.setInt(2, transfer.getRepId());
            callableStatement.setLong(3, transfer.getTransferAmount());
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.execute();
            String message = callableStatement.getString(4);
            return message;

        } catch (SQLException e) {
            customerService.printSQLException(e);
        }
        return null;
    }

//    @Override
//    public Customer selectCustomerByName(String name) {
//        Customer customer = null;
//        // Step 1: Establishing a Connection
//        try (Connection connection = customerService.getConnection();
//             // Step 2:Create a statement using connection object
//             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_NAME);) {
//            preparedStatement.setString(1, name);
//            // Step 3: Execute the query or update query
//            ResultSet rs = preparedStatement.executeQuery();
//
//            // Step 4: Process the ResultSet object.
//            while (rs.next()) {
//                int id = Integer.parseInt(rs.getString("id"));
//                String email = rs.getString("email");
//                String phone = rs.getString("phone");
//                String address = rs.getString("address");
//                long balance = Long.parseLong(rs.getString("balance"));
//                customer = new Customer(id, name, email, phone,address,balance);
//            }
//        } catch (SQLException e) {
//            customerService.printSQLException(e);
//        }
//        return customer;
//    }

    @Override
    public List<Customer> selectAll() {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Transfer transfer) throws SQLException {
        return false;
    }

    @Override
    public List<TransferInfo> selectAllTransfers() {
        List<TransferInfo> transferInfos = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (
                Connection connection = customerService.getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRANSFERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                int sendId = Integer.parseInt(rs.getString("send_id"));
                String sendName = rs.getString("send_name");
                int repId = Integer.parseInt(rs.getString("rep_id"));
                String repName = rs.getString("rep_name");
                long transactionAmount = Long.parseLong(rs.getString("transaction_amount"));
                int fees = Integer.parseInt(rs.getString("fees"));
                int feesAmount = Integer.parseInt(rs.getString("fees_amount"));
                transferInfos.add(new TransferInfo(id,sendId,sendName,repId,repName,transactionAmount,fees,feesAmount));
            }
        } catch (SQLException e) {
            customerService.printSQLException(e);
        }
        return transferInfos;
    }
}
