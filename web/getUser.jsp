<%-- 
    Document   : top1Server
    Created on : Jul 13, 2024, 2:00:03 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Top1 server Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <c:if test="${requestScope.TOP1_SERVER!=null}">
            User ID: ${requestScope.TOP1_SERVER.userID}</br>
            FUll name: ${requestScope.TOP1_SERVER.fullName}</br>
            Role ID: ${requestScope.TOP1_SERVER.roleID}</br>
            Password: ${requestScope.TOP1_SERVER.password}</br>
        </c:if>

    </body>
</html>
