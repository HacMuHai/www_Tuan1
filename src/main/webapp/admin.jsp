<%@ page import="vn.edu.iuh.fit.entities.Account" %>
<%@ page import="vn.edu.iuh.fit.entities.Role" %>
<%@ page import="vn.edu.iuh.fit.entities.GrantAccess" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>

<head>
    <title>Web Page</title>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<%
    Map<Account,String> listAccount = (Map<Account,String>) request.getAttribute("accounts");
    Account accountInfo = (Account) request.getAttribute("accountInfo");

    Boolean allowUpdate = (Boolean) request.getAttribute("allowUpdate");
    Boolean resultAdd = (Boolean) request.getAttribute("resultAdd");
    String messError = (String) request.getAttribute("messError");

    boolean showInfo = false;
    String roleValue = "admin";
    int statusValue = 1;
    if(accountInfo != null){
        showInfo = true;
        roleValue = listAccount.get(accountInfo);
        statusValue = accountInfo.getStatus().getValue();
    }

    if (allowUpdate == null) {
        allowUpdate = false;
    }
    if (resultAdd == null) {
        resultAdd = true;
    }
    if(messError == null){
        messError = "";
    }


%>

<body>
<div class="container">
    <h2 class="title-admin">Page Admin ${adminId}</h2>
    <div class="table-container">
        <table>
            <thead class="sticky-header">
            <tr>
                <th>ID</th>
                <th>Account ID</th>
                <th>Full Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                int i = 0;
                Set<Account> setAccount = listAccount.keySet();
                for(Account account: setAccount){
            %>
            <tr>
                <td><%= ++i %></td>
                <td><%= account.getAccount_id() %></td>
                <td><%= account.getFull_name() %></td>
                <td><%= account.getPassword() %></td>
                <td><%= account.getEmail() %></td>
                <td><%= account.getPhone() %></td>
                <td><%= listAccount.get(account) %></td>
                <td><%= account.getStatus() %></td>
                <td>
                    <form action="control" method="post">
                        <button name="action" value="deleteUSER"> <i class="fa fa-trash"></i></button>
                        <button name="action" value="selectUPDATE"> <i class="fa fa-pencil-square-o"></i></button>
                        <input type="hidden" name="accountIdSeclect" value="<%= account.getAccount_id() %>">
                        <input type="hidden" name="accountId" value="${adminId}">
                        <input type="hidden" name="hiddenValue" value="${logId}">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>


    <h2 class="title-info">Input Informaton</h2>

    <form id="myForm" action="control" method="post" onsubmit="return validateForm();">
        <div class="input-container">
            <div class="input-column">
                <label for="userName">Account ID:</label>
                <input type="text" id="userName" name="username" value="<%=showInfo?accountInfo.getAccount_id():""%>" placeholder="User name" <%=allowUpdate?"readonly":"" %> required>
            </div>

            <div class="input-column">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="<%=showInfo?accountInfo.getFull_name():""%>" placeholder="Full name" required>
            </div>

            <div class="input-column">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="<%=showInfo?accountInfo.getPassword():""%>" placeholder="Password" required>
            </div>

            <div class="input-column">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" value="<%=showInfo?accountInfo.getEmail():""%>" placeholder="Email" required>
            </div>

            <div class="input-column">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="<%=showInfo?accountInfo.getPhone():""%>" placeholder="Phone" required>
            </div>

            <div class="input-column">
                <div class="role-container">
                    <label for="role">Role:</label>
                    <select id="role" name="role" class="select-item">
                        <option value="admin"<%=roleValue.equals("admin")?"selected":""%>>administrator</option>
                        <option value="user"<%=!roleValue.equals("admin")?"selected":""%>>user</option>
                    </select>
                </div>
                <div class="role-container">
                    <label for="status">Status:</label>
                    <select id="status" name="status" class="select-item" <%=allowUpdate?"":"disabled"%>>
                        <option value="1" <%=statusValue == 1?"selected":""%>>ACTIVE</option>
                        <option value="0" <%=statusValue == 0?"selected":""%>>DEACTIVE</option>
                        <option value="-1" <%=statusValue == -1?"selected":""%>>DELETE</option>
                    </select>
                </div>
            </div>
        </div>
        <!-- button -->
        <div class="button-container">
            <button name="action" onclick="cancelAction()" value="<%=allowUpdate?"RESET":"CLEAR" %>"><%=allowUpdate?"RESET":"CLEAR" %></button>
            <button name="action" value="<%=allowUpdate?"CANCEL":"ADD" %>"><%=allowUpdate?"CANCEL":"ADD" %></button>
            <button name="action" value="adminUPDATE" <%=allowUpdate?"":"disabled" %> >UPDATE</button>
            <button name="action" onclick="submitForm()" value="LOG OUT">LOG OUT</button>
            <input type="hidden" name="accountId" value="${adminId}">
            <input type="hidden" name="hiddenValue" value="${logId}">
        </div>
    </form>
</div>

<script>
    if(<%= !messError.isEmpty() %>)
        alert(<%="\""+messError+"\""%>)
    function validateForm() {
        var actionElement = document.querySelector('button[name="action"]:checked');
        var userName = document.getElementById("userName").value.trim();
        var fullName = document.getElementById("fullName").value.trim();
        var password = document.getElementById("password").value.trim();
        var email = document.getElementById("email").value.trim();
        var phone = document.getElementById("phone").value.trim();

        if (actionElement && actionElement.value === "ADD") {
            if (userName === "" || fullName === "" || password === "" || email === "" || phone === "") {
                alert("Please enter data before submitting");
                return false;
            }
        }
        return true;
    }
    function cancelAction() {
        document.getElementById("myForm").reset();
    }
    function submitForm(){
        document.getElementById("userName").value = "a"
        document.getElementById("fullName").value = "a"
        document.getElementById("password").value = "a"
        document.getElementById("email").value = "a"
        document.getElementById("phone").value = "a"

        document.getElementById("myForm").onsubmit;
    }
</script>

</body>

</html>