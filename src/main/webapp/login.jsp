<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <div class="error-message-background">
        <c:if test="${not empty loginError}">
            <div class="error-message">${loginError}</div>
        </c:if>
    </div>
    <form method="post" action="controllerServlet">
        <div class="form-container">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="button-container">
            <button class="login-button" name="action" value="login">Login</button>
        </div>
    </form>
</div>
</body>
</html>

