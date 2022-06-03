<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 01/06/2022
  Time: 2:21 CH
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
    <style>
        .success {
            color:green;
            font-size: 20px;
            font-family: Arial;
        }

        .error {
            color: red;
            font-size: 20px;
            font-family: Arial;
        }

        .notification {
            margin-left: 450px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="title">
            <div class="row">
                <div class="col-sm-5">
                    <h1>Create customer</h1>
                </div>
                <div class="col-sm-7">
                    <a href="/customers" class="btn btn-outline-light" style="margin-right: 40px;">
                        <i class="fas fa-list"></i>
                        <span>List of customers</span>
                    </a>
                </div>
            </div>
        </div>

        <form action="#" method="post">
            <fieldset>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="fullName" class="form-label">Full name</label>
                        <input type="text" name="fullName" id="fullName" class="form-control">
                    </div>
                    <div class="col-sm-6">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" name="email" id="email" class="form-control">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="text" name="phone" id="phone" class="form-control">
                    </div>
                    <div class="col-sm-6">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" name="address" id="address" class="form-control">
                    </div>
                </div>

                <div class="row create">
                    <div class="col-sm-12">
                        <button class="btn btn-outline-primary" type="submit">
                            <i class="fas fa-plus"> Create customer</i>
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
    </div>
</body>
</html>
