<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 31/05/2022
  Time: 8:53 CH
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="/assets/style.css">
</head>
<body>
    <div class="container">
        <div class="title">
            <div class="row">
                <div class="col-sm-5">
                    <h1>List of customers</h1>
                </div>
                <div class="col-sm-7">
                    <a href="/customers?action=create&id=${customer.id}" class="btn btn-outline-light">
                        <i class="fas fa-plus-square"></i>
                        <span>Add New Customer</span>
                    </a>
                    <a href="/customers?action=transferInfo&id=${customer.id}" class="btn btn-outline-light">
                        <i class="fas fa-history"></i>
                        <span>Transfer money Information</span>
                    </a>
                </div>
            </div>
        </div>
        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>FullName</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Balance</th>
                <th colspan="5">Action</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items='${listCustomer}'>
                    <tr>
                        <td>${customer.getId()}</td>
                        <td>${customer.getFullName()}</td>
                        <td>${customer.getEmail()}</td>
                        <td>${customer.getPhone()}</td>
                        <td>${customer.getAddress()}</td>
                        <td>${customer.getBalance()}</td>
                        <td>
                            <a href="/customers?action=edit&id=${customer.id}" class="btn btn-outline-secondary">
                                <i class="fas fa-pen-square"></i>
                            </a>
                            <a href="/customers?action=deposits&id=${customer.id}" class="btn btn-outline-success">
                                <i class="fa fa-plus"></i>
                            </a>
                            <a href="/customers?action=withdraw&id=${customer.id}" class="btn btn-outline-warning">
                                <i class="fa fa-minus"></i>
                            </a>
                            <a href="/customers?action=transfer&id=${customer.id}" class="btn btn-outline-primary">
                                <i class="fas fa-exchange-alt"></i>
                            </a>
                            <a href="/customers?action=block&id=${customer.id}" class="btn btn-outline-danger">
                                <i class="fa fa-ban"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
