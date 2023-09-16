<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
<%--    <link rel="stylesheet" href="../css/login.css">--%>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            text-align: center;
            background-color: #fff;
            padding: 40px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 40px;
        }

        .form-container {
            margin-top: 40px;
        }

        .form-container label {
            display: block;
            text-align: left;
            font-weight: bold;
        }

        .form-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            border-radius: 3px;
        }

        .button-container {
            margin-top: 40px;
        }

        .button-container button {
            padding: 10px 40px;
            font-size: 18px;
            margin-right: 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .button-container button:last-child {
            margin-right: 0;
        }

        .button-container button.login-button {
            background-color: #007BFF;
            color: #fff;
        }

        .button-container button.register-button {
            background-color: #28A745;
            color: #fff;
        }

        .button-container button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form method="post" action="controllerServlet">
        <div class="form-container">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="button-container">
            <button class="login-button" name="action" value="login">Login</button>
            <button class="register-button" name="action" value="register">Register</button>
        </div>
    </form>
</div>
</body>
</html>
