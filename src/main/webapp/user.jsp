<%@ page import="vn.edu.iuh.fit.entities.Account" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page user</title>
    <link rel="stylesheet" href="css/user.css">
</head>

<body>
<%
    Account account = (Account) request.getAttribute("account");
%>
<h1>Page User</h1>
<div style="border: 2px #333 solid; width: 300px;">
    <div class="data-container">
        <label for="userName">Account ID:</label>
        <span id="userName"><%= account.getAccount_id()%></span>
    </div>

    <div class="data-container">
        <label for="fullName">Full Name:</label>
        <span id="fullName">${account.full_name}</span>
    </div>

    <%
        Boolean allowPasswordUpdate = (Boolean) request.getSession().getAttribute("allowPasswordUpdate");
        if (allowPasswordUpdate == null) {
            allowPasswordUpdate = false;
        }
    %>
    <div class="data-container">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${account.password}" <%= allowPasswordUpdate?"readonly":""%>>
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
    <form>
        <input type="submit" id="updateButton" name="action" value="<%= allowPasswordUpdate ? "OK" : "UPDATE" %>" data-allow-update="false" onclick="test">
        <input class="logout-button" type="submit" name="action" value="LOG OUT">
        <input type="hidden" name="hiddenValue" value="${logId}">
    </form>
</div>
</body>
</html>


