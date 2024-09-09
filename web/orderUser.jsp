<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order User Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #FFD1DC, #FFC0CB);
            margin: 0;
            padding: 20px;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #FF1493;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        h2 {
            color: #FF69B4;
            font-style: italic;
        }
        table {
            width: 90%;
            max-width: 800px;
            border-collapse: separate;
            border-spacing: 0;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #FFB6C1;
        }
        th {
            background-color: #FF69B4;
            color: white;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        tr:nth-child(even) {
            background-color: rgba(255, 182, 193, 0.1);
        }
        tr:hover {
            background-color: rgba(255, 105, 180, 0.1);
            transition: background-color 0.3s ease;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        table {
            animation: fadeIn 0.5s ease-out;
        }
        @media (max-width: 600px) {
            table {
                font-size: 0.8em;
            }
            th, td {
                padding: 10px;
            }
        }
        .logout {
            position: fixed;
            top: 20px;
            right: 20px;
            background-color: #ff1493;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s;
            z-index: 1000;
        }
        .logout:hover {
            background-color: #ff69b4;
        }
    </style>
</head>
<body>
    <a href="MainController?action=Logout" class="logout">Logout</a>
    <h1>Your History Order</h1>
    <h2><c:out value="${requestScope.MESSAGE}"/></h2>
    <c:if test="${requestScope.HISTORY_ORDER_LIST!=null}">
        <c:if test="${not empty requestScope.HISTORY_ORDER_LIST}">
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Order ID</th>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Time</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="historyOrder" varStatus="counter" items="${requestScope.HISTORY_ORDER_LIST}">
                       <tr>
                            <td>${counter.count}</td>
                            <td>${historyOrder.orderID}</td>
                            <td>${historyOrder.productID}</td>
                            <td>${historyOrder.name}</td>
                            <td>${historyOrder.quantity}</td>
                            <td>${historyOrder.date}</td>
                       </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
</body>
</html>