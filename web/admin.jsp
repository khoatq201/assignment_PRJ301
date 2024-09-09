<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.lang.String"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFE6F2;
            color: #333;
            line-height: 1.6;
            padding: 20px;
        }
        h1 {
            color: #FF69B4;
            text-align: center;
        }
        a {
            color: #FF1493;
            text-decoration: none;
            padding: 5px 10px;
            background-color: #FFC0CB;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #FF69B4;
            color: white;
        }
        div {
            margin: 20px 0;
            text-align: center;
        }
        div a {
            margin: 0 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #FF69B4;
        }
        th {
            background-color: #FF69B4;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #FFF0F5;
        }
        input[type="text"], input[type="submit"] {
            padding: 8px;
            margin: 5px 0;
            border: 1px solid #FF69B4;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #FF69B4;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #FF1493;
        }
    </style>
</head>
<body>
    <h1>Welcome ${sessionScope.LOGIN_USER.fullName}</h1>
    <c:url var="logoutLink" value="MainController">
        <c:param name="action" value="Logout"></c:param>
    </c:url>
    <div>
        <a href="${logoutLink}">Logout JSTL</a>
    </div>
    <div>
        <a href="MainController?action=LoadProduct">View Products</a>
        <a href="MainController?action=LoadOrder">View Order</a>
        <a href="MainController?action=LoadOrderDetail">View Order Detail</a>
        <a href="search.jsp">Search</a>
    </div>
</body>
</html>