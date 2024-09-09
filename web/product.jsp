<%-- 
    Document   : product
    Created on : Jul 4, 2024, 11:43:00 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <h1>Hello <c:out value="${sessionScope.LOGIN_USER.fullName}"/></h1>
        <c:if test="${sessionScope.ADMIN_PRODUCT!=null}">
            <c:if test="${not empty sessionScope.ADMIN_PRODUCT}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Status</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" items="${sessionScope.ADMIN_PRODUCT}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${product.productID}</td>
                                    <td><input type="text" name="name" value="${product.name}" required=""/></td>
                                    <td><input type="text" name="price" value="${product.price}" required=""/></td>
                                    <td><input type="number" name="quantity" value="${product.quantity}" required=""/> </td>
                                    <td><input type="number" name="status" value="${product.status}" required="" min="0" max="1" step="1"/></td>
                                    <td>
                                        <input type="submit" name="action" value="UpdateProduct"/>
                                        <input type="hidden" name="productID" value="${product.productID}"/>
                                    </td>
                                    <td>
                                        <input type="submit" name="action" value="DeleteProduct"/>
                                        <input type="hidden" name="productID" value="${product.productID}"/>
                                    </td>
                                </tr>
                               </form>
                        </c:forEach>
                    </tbody>
                </table>
                <c:out value="${requestScope.MESSAGE}"></c:out>
            </c:if>
        </c:if>
        <a href="createProducts.html">Add a new Clothe</a>
    </body>
</html>
