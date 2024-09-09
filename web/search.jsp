<%-- 
    Document   : search
    Created on : Jul 9, 2024, 2:14:35 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search User Page</title>
    </head>
    <body>
        <c:set var="search" value="${requestScope.search}" />
        <c:if test="${search==null}">
            <c:set var="search" value="" />
        </c:if>
        <h1>Welcome,<c:out value="${sessionScope.LOGIN_USER.fullName}"/></h1>
        <form action="MainController" method="POST">
            SEARCH: <input type="text" name="search" value="${search}"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <c:if test="${requestScope.LIST_USER!=null}">
            <c:if test="${ not empty requestScope.LIST_USER}">
               
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Password</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${user.userID}</td>
                                    <td><input type="text" name="fullName" value="${user.fullName}"/></td>
                                    <td><input type="text" name="roleID" value="${user.roleID}"/></td>
                                    <td>***</td>
                                    <td>
                                        <input type="submit" name="action" value="Delete"/>
                                        <input type="hidden" name="userID" value="${user.userID}"/>
                                        <input type="hidden" name="search" value="${search}"
                                    </td>
                                    <td>
                                        <input type="submit" name="action" value="Update"/>
                                        <input type="hidden" name="userID" value="${user.userID}"/>
                                         <input type="hidden" name="search" value="${search}"
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        
                    </tbody>
                </table>

            </c:if>
        </c:if>
    </body>
</html>
