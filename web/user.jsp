<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDKw6e50AJFuTcveYnAyRpzpmhTsLdiAadOQ&s');
            background-size: cover;
            background-attachment: fixed;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        .container {
            background-color: rgba(219, 112, 147, 0.9); /* Darker pink */
            border-radius: 15px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            max-width: 800px;
            margin: 0 auto;
        }
        h1 {
            color: #fff;
            text-align: center;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
        }
        a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            display: inline-block;
            margin-bottom: 15px;
        }
        a:hover {
            text-decoration: underline;
        }
        form {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
        }
        input[type="submit"] {
            background-color: #c71585; /* Dark pink */
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            flex-grow: 1;
            margin: 5px;
            width: calc(33.33% - 10px); /* Adjust for 3 buttons per row */
        }
        input[type="submit"]:hover {
            background-color: #8b008b; /* Darker pink on hover */
        }
        .user-info {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 15px;
            border-radius: 10px;
            margin-top: 20px;
        }
        .user-info p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>WELCOME, <c:out value="${sessionScope.LOGIN_USER.fullName}"/></h1>
        <c:if test="${sessionScope.LOGIN_USER== null || sessionScope.LOGIN_USER.roleID ne 'US'}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>
        
        <div class="user-info">
            <p>User ID : ${sessionScope.LOGIN_USER.userID}</p>
            <p>Full Name: ${sessionScope.LOGIN_USER.fullName}</p>
            <p>RoleID: ${sessionScope.LOGIN_USER.roleID}</p>
        </div>
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout"/>
            <input type="submit" name="action" value="Shopping"/>
            <input type="submit" name="action" value="History Order"/>
            <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.userID}"/>
        </form>
    </div>
</body>
</html>