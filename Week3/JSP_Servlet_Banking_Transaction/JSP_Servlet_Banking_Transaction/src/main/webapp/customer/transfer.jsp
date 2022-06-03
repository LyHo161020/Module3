<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 02/06/2022
  Time: 2:36 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
                    <h1>Transfer money Information</h1>
                </div>
                <div class="col-sm-7">
                    <a href="/customers" class="btn btn-outline-light" style="margin-right: 40px;">
                        <i class="fas fa-list"></i>
                        <span>Customer list</span>
                    </a>
                </div>
            </div>
        </div>

        <form action="#" method="post">
            <fieldset>
                <div class="row">
                    <div class="col-sm-3">
                        <label for="senderId" class="form-label">Sender ID</label>
                        <input type="text" name="senderId" id="senderId" class="form-control" value="${customer.getId()}" readonly>
                    </div>
                    <div class="col-sm-3">
                        <label for="senderName" class="form-label">Sender Name</label>
                        <input type="text" name="senderName" id="senderName" class="form-control" value="${customer.getFullName()}" disabled>
                    </div>
                    <div class="col-sm-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" name="email" id="email" class="form-control" value="${customer.getEmail()}" disabled>
                    </div>
                    <div class="col-sm-3">
                        <label for="balance" class="form-label">Sender balance</label>
                        <input type="text" name="balance" id="balance" class="form-control" value="${customer.getBalance()}" disabled>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <label for="recipientName" class="col-sm-12 form-label">Recipient Name</label>
                        <div class="col-sm-12">
                            <select id="recipientName" class="form-select valid" name="recipientName" aria-invalid="false">
                                <c:forEach var="customer" items="${listCustomer}">
                                    <option value="${customer.getFullName()}">${customer.getFullName()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <!-- </div> -->
                    </div>
                    <div class="col-sm-3">
                        <label for="amount" class="form-label">Transaction Amount ($)</label>
                        <input type="text" name="amount" id="amount" class="form-control">
                    </div>
                    <div class="col-sm-3">
                        <label for="fees" class="form-label">Fees (%)</label>
                        <input type="text" name="fees" id="fees" class="form-control" value="10" disabled>
                    </div>
                    <div class="col-sm-3">
                        <label for="totalAmount" class="form-label">Total amount of transaction ($)</label>
                        <input type="text" name="totalAmount" id="totalAmount" class="form-control" disabled>
                    </div>
                </div>

                <div class="row create">
                    <div class="col-sm-12">
                        <button class="btn btn-outline-primary" type="submit">
                            <i class="fas fa-exchange-alt"> Transfer</i>
                        </button>
                    </div>
                </div>
            </fieldset>
        </form>

        <p class="notification">
            <c:if test='${requestScope["message"] != null}'>
                <span class="success">${requestScope["message"]}</span>
            </c:if>
        </p>
    </div>

    <script>
        document.addEventListener("change", function(){
            let transactionAmount = document.getElementById("amount").value * 1.1;
            document.getElementById("totalAmount").value = transactionAmount;
        })
    </script>
</body>
</html>
