<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 01/06/2022
  Time: 2:59 CH
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
        <div class="col-sm-8">
          <h1>Deposit money into customer's account</h1>
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
            <input type="text" name="amount" id="amount" class="form-control">
          </div>
        </div>

        <div class="row create">
          <div class="col-sm-12">
            <button class="btn btn-outline-success" type="submit">
              <i class="fas fa-plus"> Deposit</i>
            </button>
          </div>
        </div>
      </fieldset>
    </form>

    <p class="notification">
      <c:if test='${requestScope["success"] != null}'>
        <span class="success">${requestScope["success"]}</span>
      </c:if>

      <c:if test='${requestScope["error"] != null}'>
        <span class="error">${requestScope["error"]}</span>
      </c:if>
    </p>
    <p class="notification">
      <c:if test='${requestScope["message"] != null}'>
        <span class="success">${requestScope["message"]}</span>
      </c:if>
    </p>
  </div>
</body>
</html>
