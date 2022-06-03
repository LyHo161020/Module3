<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 02/06/2022
  Time: 9:58 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

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
                    <h1>Transfer money Information</h1>
                </div>
                <div class="col-sm-7">
                    <a href="/customers" class="btn btn-outline-light">
                        <i class="fas fa-list"></i>
                        <span>Customer list</span>
                    </a>
                </div>
            </div>
        </div>

        <table>
            <thead>
            <tr>
                <th>#</th>
                <th>Sender ID</th>
                <th>Sender Name</th>
                <th>Recipient ID</th>
                <th>Recipient Name</th>
                <th>Transfer Amount ($)</th>
                <th>Fees (%)</th>
                <th>Fees Amount ($)</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="transferInfo" items='${listTransferInfo}'>
                    <tr>
                        <td>${transferInfo.getId()}</td>
                        <td>${transferInfo.getSendId}</td>
                        <td>${transferInfo.getSendName}</td>
                        <td>${transferInfo.getRepId}</td>
                        <td>${transferInfo.getRepName}</td>
                        <td>${transferInfo.getTransactionAmount}</td>
                        <td>${transferInfo.getFees}</td>
                        <td>${transferInfo.getFeesAmount}</td>
                    </tr>
                </c:forEach>
            </tbody>

            <%  long totalFeesAmount = 0;%>
            <c:forEach var="transfer" items='${listTransferInfo}'>
                ${totalFeesAmount} = ${totalFeesAmount} + ${transfer.getFeesAmount};
            </c:forEach>
            <tfoot>
            <tr>
                <td colspan="5"></td>
                <td><strong>Total Fees Amount:</strong></td>
                <td></td>
                <td><strong>${totalFeesAmount}</strong></td>
            </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>
