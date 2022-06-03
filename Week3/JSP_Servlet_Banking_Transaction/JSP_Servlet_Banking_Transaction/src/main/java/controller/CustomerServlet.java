package controller;

import model.*;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "CustomerServlet",urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {

    private final ICustomerService customerService = new CustomerService();
    private final IDepositsService depositsService = new DepositsService();
    private final IWithdrawService withdrawsService = new WithdrawService();
    private final ITransferService transferService = new TransferService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

//        try {
            switch (action) {
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "deposits":
                    showDepositsForm(req, resp);
                    break;
                case "withdraw":
                    showWithrawForm(req, resp);
                    break;
                case "transfer":
                    showTransferForm(req, resp);
                    break;
                case "block":
                    blockCustomer(req, resp);
                    break;
                case "create":
                    showCreateForm(req, resp);
                    break;
                case "transferInfo":
                    showTransferInfo(req, resp);
                    break;
                default:
                    listCustomer(req, resp);
                    break;
            }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
    }

    private void showTransferInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TransferInfo> listTransferInfo = transferService.selectAllTransfers();
        req.setAttribute("listTransferInfo", listTransferInfo);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/transferInfo.jsp");
        dispatcher.forward(req,resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

//        try {
        switch (action) {
            case "edit":
                try {
                    editCustomer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "deposits":
                try {
                    depositsCustomer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "withdraw":
                try {
                    withdrawCustomer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "transfer":
                try {
                    transferCustomer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "block":
                blockCustomer(req, resp);
                break;
            case "create":
                try {
                    createCustomer(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                listCustomer(req, resp);
                break;
        }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
    }

    private void transferCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int senderId = Integer.parseInt(req.getParameter("senderId"));
        String repName = req.getParameter("recipientName");
        long transferAmount = Long.parseLong(req.getParameter("amount"));
        Customer customer = customerService.selectCustomerByName(repName);
        Transfer transfer = new Transfer(senderId,customer.getId(),transferAmount);


        String message = transferService.insert(transfer);

        customer = customerService.selectCustomer(senderId);
        req.setAttribute("customer", customer);
        List<Customer> customerList = customerService.selectAllCustomers();
        req.setAttribute("listCustomer",customerList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/transfer.jsp");

//        if(created) {
//            req.setAttribute("success", "New customer was depossited");
//            req.setAttribute("error", null);
//        }else {
//            req.setAttribute("success", null);
//            req.setAttribute("error", "New customer not was depossited");
//        }

        req.setAttribute("message",message);
        dispatcher.forward(req, resp);
    }

    private void withdrawCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int customerId = Integer.parseInt(req.getParameter("id"));
        long transactionAmount = Long.parseLong(req.getParameter("amount"));
        Withdraw withdraw = new Withdraw(customerId,transactionAmount);


//        boolean created = depositsService.insert(deposit);
        String message = withdrawsService.insert(withdraw);

        Customer customer = customerService.selectCustomer(customerId);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/withdraw.jsp");

//        if(created) {
//            req.setAttribute("success", "New customer was depossited");
//            req.setAttribute("error", null);
//        }else {
//            req.setAttribute("success", null);
//            req.setAttribute("error", "New customer not was depossited");
//        }

        req.setAttribute("message",message);
        dispatcher.forward(req, resp);
    }

    private void depositsCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int customerId = Integer.parseInt(req.getParameter("id"));
        long transactionAmount = Long.parseLong(req.getParameter("amount"));
        Deposit deposit = new Deposit(customerId,transactionAmount);


//        boolean created = depositsService.insert(deposit);
        String message = depositsService.insertMessage(deposit);

        Customer customer = customerService.selectCustomer(customerId);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/deposits.jsp");

//        if(created) {
//            req.setAttribute("success", "New customer was depossited");
//            req.setAttribute("error", null);
//        }else {
//            req.setAttribute("success", null);
//            req.setAttribute("error", "New customer not was depossited");
//        }

        req.setAttribute("message",message);
        dispatcher.forward(req, resp);
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Customer customer = new Customer(fullName, email, phone,address,0);
        boolean created = customerService.insertCustomer(customer);

        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");

        if(created) {
            req.setAttribute("success", "New customer was created");
            req.setAttribute("error", null);
        }else {
            req.setAttribute("success", null);
            req.setAttribute("error", "New customer not was created");
        }
        dispatcher.forward(req, resp);
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        Customer customer = new Customer(id, fullName, email, phone,address);
        boolean updated = customerService.updateCustomer(customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/edit.jsp");

        if(updated) {
            req.setAttribute("success", "New customer was updated");
            req.setAttribute("error", null);
        }else {
            req.setAttribute("success", null);
            req.setAttribute("error", "New customer not was updated");
        }

        dispatcher.forward(req, resp);
    }

    private void blockCustomer(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void showTransferForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> listCustomer = customerService.selectAllCustomers();
        req.setAttribute("listCustomer", listCustomer);

        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.selectCustomer(id);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/transfer.jsp");
        dispatcher.forward(req,resp);
    }

    private void showWithrawForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.selectCustomer(id);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/withdraw.jsp");
        dispatcher.forward(req,resp);
    }

    private void showDepositsForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.selectCustomer(id);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/deposits.jsp");
        dispatcher.forward(req,resp);
    }

    private void listCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> listCustomer = customerService.selectAllCustomers();
        req.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/list.jsp");
        dispatcher.forward(req,resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.selectCustomer(id);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/edit.jsp");
        dispatcher.forward(req,resp);
    }

}
