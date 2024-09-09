<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.product.Clothes"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Diep's store</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://gcs.tripi.vn/public-tripi/tripi-feed/img/474289NgK/background-thoi-trang-dep_025556498.jpg');
            background-size: cover;
            background-attachment: fixed;
            color: #333;
            margin: 0;
            padding: 20px;
        }
        .container {
            background-color: rgba(255, 192, 203, 0.85);
            border-radius: 15px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            margin: 0 auto;
        }
        h1 {
            color: #ff1493;
            text-align: center;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #ff69b4;
            color: white;
        }
        tr:hover {
            background-color: #fff0f5;
        }
        input[type="number"] {
            width: 60px;
            padding: 5px;
        }
        .total {
            font-size: 1.2em;
            font-weight: bold;
            margin-top: 20px;
            text-align: right;
        }
        .actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }
        .button {
            background-color: #ff1493;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
        }
        .button:hover {
            background-color: #ff69b4;
            transform: translateY(-2px);
        }
        .checkout-form {
            display: inline;
        }
        .email-form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
            width: 100%;
        }
        .email-input {
            padding: 12px;
            font-size: 16px;
            border: 2px solid #ff1493;
            border-radius: 5px;
            width: 80%;
            max-width: 400px;
            margin-bottom: 10px;
        }
        .email-submit {
            background-color: #ff1493;
            color: white;
            padding: 12px 24px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 200px;
        }
        .email-submit:hover {
            background-color: #ff69b4;
        }
        .logout {
            position: absolute;
            top: 10px;
            right: 20px;
            background-color: #ff1493;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .logout:hover {
            background-color: #ff69b4;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="logout">
            <a href="MainController?action=Logout">Logout</a>
        </div>
        <c:if test="${sessionScope.CART!=null}">
            <c:if test="${not empty sessionScope.CART}">
                <c:set var="total" value="0.0"/>
                <h1>Your shopping Cart</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Remove</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="clothes" varStatus="counter" items="${sessionScope.CART.getCart().values()}">
                            <c:set var="total" value="${total + clothes.quantity*clothes.price}"/>
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${clothes.id}</td>
                                    <td>${clothes.name}</td>
                                    <td> 
                                        <input type="number" name="quantity" value="${clothes.quantity}" min="1"/>
                                    </td>
                                    <td>${clothes.price}$</td>
                                    <td>${clothes.quantity*clothes.price}$</td>
                                    <td>
                                        <a href="MainController?action=Remove&id=${clothes.id}" class="button">Remove</a>
                                    </td>
                                    <td>
                                        <input type="submit" name="action" value="Edit" class="button"/>
                                        <input type="hidden" name="id" value="${clothes.id}"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="actions">
                    <a href="shopping.jsp" class="button">Add more!</a>
                    <div class="total">Total: <c:out value="${total}"/>$</div>
                    <form action="MainController" method="POST" class="checkout-form">
                        <input class="button" type="submit" name="action" value="Checkout"/>
                    </form>
                </div>
                <c:out value="${requestScope.ERROR}"/>
                <c:if test="${requestScope.MESSAGE_CHECKOUT!=null}">
                    <c:forEach var="mess" varStatus="counter" items="${requestScope.MESSAGE_CHECKOUT}">
                        <h3>${counter.count}. <c:out value="${mess}"/></h3><br>
                    </c:forEach>
                </c:if>    
            </c:if>
        </c:if>      
        <c:if test="${sessionScope.CART ==null}">
            <c:if test="${requestScope.MESSAGE!=null}">
                <h1><c:out  value="${requestScope.MESSAGE}"/></h1>
            </c:if>
            <c:if test="${requestScope.MESSAGE==null}">
                <c:if test="${requestScope.MESSAGE_MAIL==null}">
                    <h1>Your cart is empty</h1>
                </c:if>
            </c:if>
                <c:if test="${requestScope.MESSAGE==null}">
                    <c:if test="${requestScope.MESSAGE_MAIL!=null}">
                        <h1>${requestScope.MESSAGE_MAIL}</h1>
                        <h1>Thank You!</h1>
                    </c:if>
                </c:if>
        </c:if>
        <c:if test="${sessionScope.CART_MAIL !=null}">
            <form action="MainController" method="POST" class="email-form">
                <input type="email" name="mail" placeholder="Enter your email here" value="${param.MAIL}" class="email-input"/>
                <input type="submit" name="action" value="Send" class="email-submit"/>
            </form>
        </c:if>
    </div>
</body>
</html>