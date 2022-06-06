<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 02/06/2022
  Time: 10:45 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="col-sm-8">
                    <h1>Withdraw money from the customer's account</h1>
                </div>
                <div class="col-sm-4">
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
                    <div class="col-sm-6">
                        <label for="id" class="form-label">Customer ID</label>
                        <input type="text" name="id" id="id" class="form-control" value="${customer.getId()}" disabled>
                    </div>
                    <div class="col-sm-6">
                        <label for="fullName" class="form-label">Full name</label>
                        <input type="text" name="fullName" id="fullName" class="form-control" value="${customer.getFullName()}" disabled>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="balance" class="form-label">Current balance ($)</label>
                        <input type="text" name="balance" id="balance" class="form-control" value="${customer.getBalance()}" disabled>
                    </div>
                    <div class="col-sm-6">
                        <label for="amount" class="form-label">Transaction Amount ($)</label>
                        <input type="number" name="amount" id="amount" class="form-control">
                    </div>
                </div>

                <div class="row create">
                    <div class="col-sm-12">
                        <button class="btn btn-outline-warning" type="submit">
                            <i class="fas fa-minus"> Withdraw</i>
                        </button>
                    </div>
                </div>
            </fieldset>
        </form>

<%--        <p class="notification">--%>
<%--            <c:if test='${requestScope["message"] != null}'>--%>
<%--                <span class="success">${requestScope["message"]}</span>--%>
<%--            </c:if>--%>
<%--        </p>--%>

        <c:choose>
            <c:when test = "${requestScope['message'] == null}" >
            </c:when>
            <c:when test='${requestScope["message"] == "Rút tiền thành công!"}'>
                <%@ include file="/alert/success.jsp"%>
            </c:when>
            <c:otherwise>
                <%@ include file="/alert/warning.jsp"%>
            </c:otherwise>
        </c:choose>


        <c:forEach var="error" items="${errors}">
            <%@ include file="/alert/danger.jsp"%>
        </c:forEach>
    </div>
</body>
</html>
