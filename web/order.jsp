<%-- 
    Document   : order
    Created on : Jul 4, 2024, 3:08:53 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
        <h1>Order List</h1>
        <c:if test="${requestScope.ADMIN_ORDER!=null}">
            <c:if test="${not empty requestScope.ADMIN_ORDER}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Order ID</th>
                            <th>User ID</th>
                            <th>Date</th>
                            <th>Total</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" varStatus="counter" items="${requestScope.ADMIN_ORDER}">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${order.orderID}</td>
                                <td>${order.userID}</td>
                                <td>${order.date}</td>
                                <td>${order.total}$</td>
                                <td>${order.status}</td>
                            </tr>
                        </c:forEach>
                       
                    </tbody>
                </table>

            </c:if>
        </c:if>
    </body>
</html>
