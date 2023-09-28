package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.*;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.services.GrantAccessService;
import vn.edu.iuh.fit.services.LogService;
import vn.edu.iuh.fit.services.RoleService;
import vn.edu.iuh.fit.services.imp.AccountServiceImp;
import vn.edu.iuh.fit.services.imp.GrantAccessServiceImp;
import vn.edu.iuh.fit.services.imp.LogServiceImp;
import vn.edu.iuh.fit.services.imp.RoleServiceImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/controllerServlet", "/control"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();

        if (action.equalsIgnoreCase("login")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.include(req, resp);
        } else {
            resp.setContentType("text/html");
            out.println("<html><body>");
            out.println("<h1>" + "/web?action=login" + "</h1>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("action: " + action);

        PrintWriter out = resp.getWriter();
        switch (action) {
            case "login":
                login(req, resp);
                break;
            case "LOG OUT":
                logout(req, resp);
                break;
            case "UPDATE":
                updateUser(req,resp);
                break;
            case "OK":
                updateUserOK(req,resp);
                break;
            case "ADD":
                addAccount(req,resp);
                break;
            case "selectUPDATE":
                selectUPDATE(req,resp);
                break;
            case "adminUPDATE":
                adminUPDATE(req,resp);
                break;
            case "deleteUSER":
                deleteUser(req,resp);
                break;
            case "CANCEL":
                cancelUpdate(req,resp);
                break;
            case "CLEAR":
                clearInfo(req,resp);
                break;
            case "RESET":
                resetInfo(req,resp);
                break;
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String log = req.getParameter("hiddenValue");
        String adminId = req.getParameter("accountId");
        String accountIdSeclect = req.getParameter("accountIdSeclect");
        long logId = Integer.parseInt(log);


        RequestDispatcher requestDispatcher = null;
        try{
            AccountService accountService = new AccountServiceImp();
            GrantAccessService grantAccessService = new GrantAccessServiceImp();


            if(!accountIdSeclect.equals(adminId)){
                accountService.delete(accountIdSeclect);
                grantAccessService.delete(accountIdSeclect);
            }else{
                req.setAttribute("messError","cannot delete your own account");
            }

            Map<Account,String> accounts = accountService.getAll();

            req.setAttribute("accounts", accounts);
            req.setAttribute("logId", logId);
            req.setAttribute("adminId", adminId);

            requestDispatcher = req.getRequestDispatcher("admin.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void resetInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String log = req.getParameter("hiddenValue");
        String adminId = req.getParameter("accountId");
        String accountIdSeclect = req.getParameter("username");
        long logId = Integer.parseInt(log);


        RequestDispatcher requestDispatcher = null;
        try {
            AccountService accountService = new AccountServiceImp();

            Map<Account,String> accounts = accountService.getAll();
            Account accountSelect = accountService.getOne(accountIdSeclect);

            req.setAttribute("accounts", accounts);
            req.setAttribute("accountInfo",accountSelect);
            req.setAttribute("allowUpdate",true);
            req.setAttribute("logId", logId);
            req.setAttribute("adminId", adminId);

            requestDispatcher = req.getRequestDispatcher("admin.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void cancelUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String log = req.getParameter("hiddenValue");
        String adminId = req.getParameter("accountId");
        long logId = Integer.parseInt(log);


        RequestDispatcher requestDispatcher = null;
        try {
            AccountService accountService = new AccountServiceImp();

            Map<Account,String> accounts = accountService.getAll();

            req.setAttribute("accounts", accounts);
            req.setAttribute("logId", logId);
            req.setAttribute("adminId", adminId);

            requestDispatcher = req.getRequestDispatcher("admin.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void adminUPDATE(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String log = req.getParameter("hiddenValue");
        String adminId = req.getParameter("accountId");
        long logId = Integer.parseInt(log);

        String accID = req.getParameter("username");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String status = req.getParameter("status");
        String role = req.getParameter("role");

        RequestDispatcher requestDispatcher = null;
        try{
            AccountService accountService = new AccountServiceImp();
            GrantAccessService grantAccessService = new GrantAccessServiceImp();

            boolean result;
            try {
                result = accountService.update(new Account(accID,fullName,password,email,phone, Status.fromValue(Integer.parseInt(status))));
            }catch (SQLException e){
                result = false;
            }
            if(result){
                if(status.equals("1"))
                    grantAccessService.changeRole(new GrantAccess(role,accID, IsGrant.enable,""));
                else
                    grantAccessService.changeRole(new GrantAccess(role,accID, IsGrant.disable,""));
            }else{
                req.setAttribute("resultAdd",result);
            }

            Map<Account,String> accounts = accountService.getAll();

            req.setAttribute("accounts", accounts);
            req.setAttribute("logId", logId);
            req.setAttribute("adminId", adminId);

            requestDispatcher = req.getRequestDispatcher("admin.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void selectUPDATE(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String log = req.getParameter("hiddenValue");
        String adminId = req.getParameter("accountId");
        String accountIdSeclect = req.getParameter("accountIdSeclect");
        long logId = Integer.parseInt(log);


        RequestDispatcher requestDispatcher = null;
        try {
            AccountService accountService = new AccountServiceImp();

            Map<Account,String> accounts = accountService.getAll();
            Account accountSelect = accountService.getOne(accountIdSeclect);

            req.setAttribute("accounts", accounts);
            req.setAttribute("accountInfo",accountSelect);
            req.setAttribute("allowUpdate",true);
            req.setAttribute("logId", logId);
            req.setAttribute("adminId", adminId);

            requestDispatcher = req.getRequestDispatcher("admin.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String log = req.getParameter("hiddenValue");
        String adminId = req.getParameter("accountId");
        long logId = Integer.parseInt(log);


        RequestDispatcher requestDispatcher = null;
        try {
            AccountService accountService = new AccountServiceImp();

            Map<Account,String> accounts = accountService.getAll();

            req.setAttribute("accounts", accounts);
            req.setAttribute("logId", logId);
            req.setAttribute("adminId", adminId);

            requestDispatcher = req.getRequestDispatcher("admin.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String log = req.getParameter("hiddenValue");
        String adminId = req.getParameter("accountId");
        long logId = Integer.parseInt(log);

        String accID = req.getParameter("username");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");

        RequestDispatcher requestDispatcher = null;
        try{
            AccountService accountService = new AccountServiceImp();
            GrantAccessService grantAccessService = new GrantAccessServiceImp();

            boolean result;
            try {
                result = accountService.insert(new Account(accID,fullName,password,email,phone, Status.ACTIVE));
            }catch (SQLException e){
                result = false;
            }
            if(result){
                grantAccessService.insert(new GrantAccess(role,accID, IsGrant.enable,""));
            }else{
                req.setAttribute("resultAdd",result);
                req.setAttribute("messError","username already exists");

            }

            Map<Account,String> accounts = accountService.getAll();

            req.setAttribute("accounts", accounts);
            req.setAttribute("logId", logId);
            req.setAttribute("adminId", adminId);

            requestDispatcher = req.getRequestDispatcher("admin.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUserOK(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AccountService accountService = new AccountServiceImp();
            LogService logService = new LogServiceImp();

            String username = req.getParameter("accountId");
            Account accountNew = accountService.getOne(username);

            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            accountNew.setPassword(password);
            accountNew.setEmail(email);
            accountNew.setPhone(phone);
            req.setAttribute("account", accountNew);
            accountService.update(accountNew);


            String role = accountService.isAdmin(accountNew.getAccount_id()) == 1? "administrator" : "user";
            req.setAttribute("role", role);

            String hidden = req.getParameter("hiddenValue");
            long idLog = Integer.parseInt(hidden);
            req.setAttribute("logId", idLog);

            req.setAttribute("allowPasswordUpdate", false);
            req.getRequestDispatcher("user.jsp").forward(req, resp);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            AccountService accountService = new AccountServiceImp();
            LogService logService = new LogServiceImp();

            String username = req.getParameter("accountId");
            Account account = accountService.getOne(username);
            req.setAttribute("account", account);

            String role = "user";
            req.setAttribute("role", role);

            String hidden = req.getParameter("hiddenValue");
            long idLog = Integer.parseInt(hidden);
            req.setAttribute("logId", idLog);

            req.setAttribute("allowPasswordUpdate", true);
            req.getRequestDispatcher("user.jsp").forward(req,resp);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void login (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                RequestDispatcher requestDispatcher = null;
                AccountService accountService = new AccountServiceImp();
                LogService logService = new LogServiceImp();
                GrantAccessService grantAccessService = new GrantAccessServiceImp();

                String username = req.getParameter("username");
                String password = req.getParameter("password");
                int status = accountService.login(username, password);
                System.out.println("status: " + status);

                int logid;
                switch (status) {
                    case 1:
                        Map<Account,String> accounts = accountService.getAll();
                        List<GrantAccess> grantAccesses = grantAccessService.getAll();

                        logid = logService.insert(username, "login");

                        req.setAttribute("accounts", accounts);
                        req.setAttribute("grantAccesses", grantAccesses);
                        req.setAttribute("logId", logid);
                        req.setAttribute("adminId", username);
                        requestDispatcher = req.getRequestDispatcher("admin.jsp");
                        requestDispatcher.include(req, resp);
                        break;
                    case 0:
                        req.setAttribute("loginError", "Username or password is incorrect");
                        requestDispatcher = req.getRequestDispatcher("login.jsp");
                        requestDispatcher.include(req, resp);
                        break;
                    case -1:
                        Account account = accountService.getOne(username);
                        System.out.println("user: " + account.getAccount_id());
                        System.out.println("statusss: " + account.getStatus().getValue());
                        String role = "user";

                        req.setAttribute("account", account);
                        req.setAttribute("role", role);
                        logid = logService.insert(username, "login");
                        req.setAttribute("logId", logid);
                        requestDispatcher = req.getRequestDispatcher("user.jsp");
                        requestDispatcher.include(req, resp);
                        break;

                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    protected void logout (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String hidden = req.getParameter("hiddenValue");
        System.out.println(hidden);
        long idLog = Integer.parseInt(hidden);
        try {
            LogService logService = new LogServiceImp();
            logService.update(idLog, "logout");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.include(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

