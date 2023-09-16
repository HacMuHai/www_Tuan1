<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page user</title>
    <style>
        body {
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
            color: #333;
        }



        .data-container label {
            font-weight: bold;
            margin-right: 10px;
            text-align: left;
        }

        .data-container input[type="text"],
        .data-container input[type="password"] {
            padding: 5px;
            width: calc(100%-10px);
        }

        .data-container span {
            background-color: #f9f9f9;
            padding: 5px;
        }

        .button-container {
            text-align: left;
            margin-top: 10px;
            margin-left: 70px;
        }

        .button-container input {
            padding: 10px 20px;
            margin-left: 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            background-color: #007BFF;
            color: #fff;
        }


        .data-container {
            text-align: left;
            margin-left: 20px;
            margin-right: 5px;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
<h1>Page User</h1>
<div style="border: 2px #333 solid; width: 300px;">
    <div class="data-container">
        <label for="userName">Account ID:</label>
        <span id="userName">${account.account_id}</span>
    </div>

    <div class="data-container">
        <label for="fullName">Full Name:</label>
        <span id="fullName">${account.full_name}</span>
    </div>

    <div class="data-container">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${account.password}">
    </div>

    <div class="data-container">
        <label for="email">Email:</label>
        <span id="email">${account.email}</span>
    </div>

    <div class="data-container">
        <label for="phone">Phone:</label>
        <span id="phone">${account.phone}</span>
    </div>

    <div class="data-container">
        <label for="role">Role:</label>
        <span id="role">${role}</span>
    </div>
</div>


<div class="button-container">
    <form action="control" method="post">
        <input type="submit" name="action" value="UPDATE">
        <input class="logout-button" type="submit" name="action" value="LOG OUT">
        <input type="hidden" name="hiddenValue" value="${logId}">

</div>
</body>
</html>
