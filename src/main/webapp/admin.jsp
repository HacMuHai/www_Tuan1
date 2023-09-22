<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page admin</title>
    <link rel="stylesheet" href="css/admin.css">
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
        <button name="action" value="aminUPDATE">UPDATE</button>
        <button name="action" value="LOG OUT">LOG OUT</button>
        <input type="hidden" id="hiddenValue" name="hiddenValue" value="${logId}">
    </form>
</div>
</body>
</html>