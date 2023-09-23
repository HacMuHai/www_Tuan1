<%@ page import="vn.edu.iuh.fit.entities.Account" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>


<head>
    <title>Page user</title>
    <link rel="stylesheet" href="css/user.css">
</head>

<%
    Account account = (Account) request.getAttribute("account");
    Boolean allowPasswordUpdate = (Boolean) request.getAttribute("allowPasswordUpdate");
    if (allowPasswordUpdate == null) {
        allowPasswordUpdate = false;
    }
%>
<body>
<div class="container">
    <div class="form-container">
        <h2>Page user <%= account.getFull_name()%></h2>
        <form action="controllerServlet" method="post">
            <div class="form-content">
                <div class="input-container">
                    <label for="userName">Account ID:</label>
                    <span id="userName" name="username"><%= account.getAccount_id()%></span>
                </div>

                <div class="input-container">
                    <label for="fullName">Full Name:</label>
                    <span id="fullName"><%= account.getFull_name()%></span>
                </div>

                <div class="input-container">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" value="<%= account.getPassword()%>" <%= allowPasswordUpdate?"":"disabled"%>>
                </div>

                <div class="input-container">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" value="<%= account.getEmail()%>"  <%= allowPasswordUpdate?"":"disabled"%>>
                </div>

                <div class="input-container">
                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone" value="<%= account.getPhone()%>"  <%= allowPasswordUpdate?"":"disabled"%>>
                </div>

                <div class="input-container">
                    <label for="role">Role:</label>
                    <span id="role">${role}</span>
                </div>
            </div>
            <div class="button-container">
                <input style="width: 100px;" type="submit" id="updateButton" name="action" value="<%= allowPasswordUpdate ? "OK" : "UPDATE" %>">
                <input style="width: 100px;" class="logout-button" type="submit" name="action" value="LOG OUT">
                <input type="hidden" name="accountId" value="<%= account.getAccount_id() %>">
                <input type="hidden" name="hiddenValue" value="${logId}">
            </div>
        </form>
    </div>
</div>
</body>

</html>
