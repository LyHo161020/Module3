package controller;

import model.*;
import service.*;
import utils.ValidDateUtils;

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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

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
    }

    private void showTransferInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TransferInfo> listTransferInfo = transferService.selectAllTransfers();
        req.setAttribute("listTransferInfo", listTransferInfo);
        int totalFeesAmount = transferService.sumFeesAmount();
        req.setAttribute("totalFeesAmount", totalFeesAmount);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/transferInfo.jsp");
        dispatcher.forward(req,resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void transferCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Customer customer;
        List<String> errors = new ArrayList<>();
        List<Customer> customerList = customerService.selectAllCustomers();

        int senderId = Integer.parseInt(req.getParameter("senderId"));
        String repName = req.getParameter("recipientName");
        String amount = req.getParameter("amount");


        boolean isExist = false;
        for(Customer cus : customerList){
            if (repName.equals(cus.getFullName())) {
                isExist = true;
                break;
            }
        }

        if(!isExist){
            errors.add("T??n ng?????i nh???n kh??ng t???n t???i!");
        }else {
            if (amount.equals("")) {
                errors.add("S??? ti???n chuy???n kho???n kh??ng ???????c ????? tr???ng!");
            } else if (amount.length() > 12) {
                errors.add("S??? ti???n chuy???n kho???n t???i ??a l?? 999.999.999.999??!");
            } else {
                long transferAmount = Long.parseLong(amount);
                customer = customerService.selectCustomerByName(repName);
                Transfer transfer = new Transfer(senderId, customer.getId(), transferAmount);

                String message = transferService.insert(transfer);
                req.setAttribute("message", message);
            }
        }


        if(errors.size() > 0){
            req.setAttribute("errors",errors);
        }
        customer = customerService.selectCustomer(senderId);
        req.setAttribute("customer", customer);
        req.setAttribute("listCustomer",customerList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/transfer.jsp");
        dispatcher.forward(req, resp);
    }

    private void withdrawCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<String> errors = new ArrayList<>();

        int customerId = Integer.parseInt(req.getParameter("id"));
        String amount = req.getParameter("amount");


        if(amount.equals("")){
            errors.add("S??? ti???n chuy???n kho???n kh??ng ???????c ????? tr???ng!");
        } else if (amount.length() > 12) {
            errors.add("S??? ti???n chuy???n kho???n t???i ??a l?? 999.999.999.999??!");
        }else {
            long transactionAmount = Long.parseLong(amount);
            Withdraw withdraw = new Withdraw(customerId,transactionAmount);

            String message = withdrawsService.insert(withdraw);
            req.setAttribute("message",message);
        }

        if(errors.size() > 0){
            req.setAttribute("errors",errors);
        }

        Customer customer = customerService.selectCustomer(customerId);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/withdraw.jsp");
        dispatcher.forward(req, resp);
    }

    private void depositsCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();
        int customerId = Integer.parseInt(req.getParameter("id"));
        String amount = req.getParameter("amount");

        if(!ValidDateUtils.isNumberValid(Integer.toString(customerId))){
            errors.add("ID kh??ng h???p l???!");
        }
        if(amount.equals("")){
            errors.add("S??? ti???n nh???p kh??ng ???????c ????? tr???ng!");
        } else if (amount.length() > 12) {
            errors.add("S??? ti???n g???i t???i ??a l?? 999.999.999.999??!");
        } else{
            long transactionAmount = Long.parseLong(amount);
            Deposit deposit = new Deposit(customerId,transactionAmount);

            String message = depositsService.insertMessage(deposit);
            req.setAttribute("message",message);
        }

        if(errors.size() > 0) {
            req.setAttribute("errors",errors);
        }
        Customer customer = customerService.selectCustomer(customerId);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/deposits.jsp");
        dispatcher.forward(req, resp);
    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();
        List<Customer> customers = customerService.selectAllCustomers();

//        int id = Integer.parseInt(req.getParameter("id"));

        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        if(fullName.length() == 0) {
            errors.add("T??n kh??ng ???????c ????? tr???ng!");
        }
        if(email.length() == 0) {
            errors.add("Email kh??ng ???????c ????? tr???ng!");
        }
        if(phone.length() == 0) {
            errors.add("S??? ??i???n tho???i kh??ng ???????c ????? tr???ng!");
        }
        if(address.length() == 0){
            errors.add("?????a ch??? kh??ng ???????c ????? tr???ng!");
        }

        for(Customer cus : customers){
            if(fullName.equals(cus.getFullName())){
                errors.add("T??n n??y ???? t???n t???i!");
            }

            if(email.equals(cus.getEmail())){
                errors.add("?????a ch??? email n??y ???? t???n t???i!");
            }

            if(phone.equals(cus.getPhone())){
                errors.add("S??? ??i???n tho???i n??y ???? t???n t???i!");
            }

            if(address.equals(cus.getAddress())){
                errors.add("?????a ch??? n??y ???? t???n t???i!");
            }
        }

        if(errors.size() > 0){
            req.setAttribute("errors",errors);
        }else {
            Customer customer = new Customer(fullName, email, phone,address,0);
            boolean created = customerService.insertCustomer(customer);

            if(created) {
                req.setAttribute("message", "New customer was created");
            }else {
                req.setAttribute("message", "New customer not was created");
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void editCustomer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();
        List<Customer> customers = customerService.selectAllCustomers();

        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.selectCustomer(id);
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        if(fullName.length() == 0) {
            errors.add("T??n kh??ng ???????c ????? tr???ng!");
        }
        if(email.length() == 0) {
            errors.add("Email kh??ng ???????c ????? tr???ng!");
        }
        if(phone.length() == 0) {
            errors.add("S??? ??i???n tho???i kh??ng ???????c ????? tr???ng!");
        }
        if(address.length() == 0){
            errors.add("?????a ch??? kh??ng ???????c ????? tr???ng!");
        }

        for(Customer cus : customers){
            if(fullName.equals(cus.getFullName()) && !fullName.equals(customer.getFullName())){
                errors.add("T??n n??y ???? t???n t???i!");
            }

            if(email.equals(cus.getEmail()) && !email.equals(customer.getEmail())){
                errors.add("?????a ch??? email n??y ???? t???n t???i!");
            }

            if(phone.equals(cus.getPhone()) && !phone.equals(customer.getPhone())){
                errors.add("S??? ??i???n tho???i n??y ???? t???n t???i!");
            }

            if(address.equals(cus.getAddress()) && !address.equals(customer.getAddress())){
                errors.add("?????a ch??? n??y ???? t???n t???i!");
            }
        }


        if(errors.size() > 0){
            req.setAttribute("errors",errors);
        }else {
            customer = new Customer(id, fullName, email, phone,address);
            boolean updated = customerService.updateCustomer(customer);
            if(updated) {
                req.setAttribute("message", "Th??ng tin kh??ch h??ng ???? ???????c c???p nh???t th??nh c??ng!");
            }else {
                req.setAttribute("message", "C???p nh???t th??ng tin th???t b???i!");
            }
        }


        customer = customerService.selectCustomer(id);
        req.setAttribute("customer",customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/edit.jsp");
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
