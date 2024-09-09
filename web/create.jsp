<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFF0F5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #FFB6C1;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        h2 {
            color: #FF1493;
            text-align: center;
            margin-bottom: 1.5rem;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 0.5rem;
            color: #FF69B4;
        }
        input[type="text"],
        input[type="password"] {
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #FF69B4;
            border-radius: 4px;
            background-color: #FFF0F5;
        }
        input[type="submit"],
        input[type="reset"] {
            padding: 0.5rem 1rem;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        input[type="submit"] {
            background-color: #FF1493;
            color: white;
            margin-bottom: 0.5rem;
        }
        input[type="submit"]:hover {
            background-color: #FF69B4;
        }
        input[type="reset"] {
            background-color: #FFC0CB;
            color: #FF1493;
        }
        input[type="reset"]:hover {
            background-color: #FFB6C1;
        }
        .error {
            color: #FF0000;
            font-size: 0.8rem;
            margin-top: -0.5rem;
            margin-bottom: 0.5rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Create User</h2>
        <form action="MainController" method="POST">
            <label for="userID">User ID</label>
            <input type="text" id="userID" name="userID" required>
            <div class="error">${requestScope.USER_ERROR.userIDError}</div>
            
            <label for="fullName">Full Name</label>
            <input type="text" id="fullName" name="fullName" required>
            <div class="error">${requestScope.USER_ERROR.fullNameError}</div>
            
            <label for="roleID">Role ID</label>
            <input type="text" id="roleID" name="roleID" value="US" readonly>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            
            <label for="confirm">Confirm Password</label>
            <input type="password" id="confirm" name="confirm" required>
            <div class="error">${requestScope.USER_ERROR.confirmError}</div>
            
            <input type="submit" name="action" value="Create">
            <input type="reset" value="Reset">
        </form>
        <div class="error">${requestScope.USER_ERROR.error}</div>
    </div>
</body>
</html>