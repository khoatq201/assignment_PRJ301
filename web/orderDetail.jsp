<%-- 
    Document   : orderDetail
    Created on : Jul 4, 2024, 3:27:08 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail Page</title>
    </head>
    <body>
        <h1>Order Detail List</h1>
        <c:if test="${requestScope.ORDER_DETAIL!=null}">
            <c:if test="${not empty requestScope.ORDER_DETAIL}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Order ID</th>
                            <th>Product ID</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="orderDetail" varStatus="counter" items="${requestScope.ORDER_DETAIL.getOrderDetailList()}">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${orderDetail.orderID}</td>
                                <td>${orderDetail.productID}</td>
                                <td>${orderDetail.price}$</td>
                                <td>${orderDetail.quantity}</td>
                                <td>${orderDetail.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </c:if>
    </body>
</html>
