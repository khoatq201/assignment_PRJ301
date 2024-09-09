<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="sample.product.Product"%>
<%@page import="sample.shopping.Cart"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Diep's Store</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://cdn.shopify.com/s/files/1/0070/7032/files/how_20to_20start_20a_20clothing_20brand.png?v=1693935729');
            background-size: cover;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: rgba(255, 192, 203, 0.9);
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            max-width: 800px;
            width: 100%;
        }

        .welcome {
            font-size: 24px;
            font-weight: bold;
            color: #333;
            text-align: center;
            margin-bottom: 1.5rem;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 1rem;
        }

        th, td {
            padding: 0.5rem;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #ff69b4;
            color: white;
        }

        select {
            padding: 0.5rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            font-size: 14px;
        }

        input[type="submit"] {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            background-color: #4caf50;
            color: white;
        }

        input[type="submit"]:hover {
            transform: translateY(-2px);
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        input[type="submit"]:active {
            transform: translateY(-1px);
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.2);
        }

        a {
            display: inline-block;
            margin-top: 1rem;
            padding: 0.5rem 1rem;
            background-color: #ff69b4;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: all 0.3s ease;
        }

        a:hover {
            background-color: #ff1493;
            transform: translateY(-2px);
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        @media (max-width: 600px) {
            .container {
                padding: 1rem;
            }
            
            .welcome {
                font-size: 20px;
            }
            
            table, select, input[type="submit"] {
                font-size: 12px;
            }
        }
        .logout {
            position: fixed;
            top: 10px;
            right: 10px;
            z-index: 1000;
        }

        .logout a {
            background-color: #ff4757;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .logout a:hover {
            background-color: #ff6b81;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="logout">
            <a href="MainController?action=Logout">Logout</a>
        </div>
        <div class="welcome">Welcome to Diep's Store</div>
        <c:if test="${sessionScope.LIST_PRODUCT !=null}">
            <c:if test="${not empty sessionScope.LIST_PRODUCT}">
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Name</th> 
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Add</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" items="${sessionScope.LIST_PRODUCT}">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${product.productID}</td>
                                    <td>${product.name}</td>
                                    <td>${product.price}</td>
                                    <td>
                                        <select name="cmbQuantity" id="cmbQuantity">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="10">10</option>
                                            <option value="50">50</option>
                                            <option value="100">100</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="hidden" name="ID" value="${product.productID}"/>
                                        <input type="hidden" name="name" value="${product.name}"/>
                                        <input type="hidden" name="price" value="${product.price}"/>
                                        <input type="hidden" name="cmbQuantity"/>
                                        <input type="submit" name="action" value="Add"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        ${requestScope.MESSAGE}
        <div>
            <a href="viewCart.jsp">View cart</a>
        </div>
    </div>
</body>
</html>