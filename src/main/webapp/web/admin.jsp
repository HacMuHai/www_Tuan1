<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page admin</title>
    <style>
        body {
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            background-color: #fff;
            border: 1px solid #ddd;
            margin-bottom: 20px;
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .edit-input {
            width: 100%;
            border: none;
        }

        .button-container {
            text-align: right;
            margin-right: 20px;
        }

        .button-container button {
            padding: 10px 20px;
            margin-left: 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            background-color: #007BFF;
            color: #fff;
        }

        .button-container button.logout-button .add-button {
            float: right;
        }
    </style>
</head>

<body>
<h1>Page Admin</h1>
<table>
    <thead>
    <tr>
        <th>STT</th>
        <th>Account ID</th>
        <th>Full Name</th>
        <th>Password</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Role</th>
        <th>Select</th>
    </tr>
    </thead>
    <tbody>
    ${rows}
    </tbody>
</table>

<div class="button-container">
    <form action="control" method="post">
        <button name="action" value="ADD">ADD</button>
        <button name="action" value="DELETE">DELETE</button>
        <button name="action" value="UPDATE">UPDATE</button>
        <button name="action" value="LOG OUT">LOG OUT</button>
        <input type="hidden" id="hiddenValue" name="hiddenValue" value="${logId}">
    </form>
</div>
</body>
</html>