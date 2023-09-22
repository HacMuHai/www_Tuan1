package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.services.LogService;
import vn.edu.iuh.fit.services.imp.AccountServiceImp;
import vn.edu.iuh.fit.services.imp.LogServiceImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
        System.out.println("action: "+action);

        PrintWriter out = resp.getWriter();
            switch (action){
                case "login":
                    login(req,resp);
                    break;
                case "LOG OUT":
                    logout(req,resp);
                    break;
                case "aminUPDATE":
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                    break;
                case "UPDATE":
                    req.getSession().setAttribute("allowPasswordUpdate",true);
                    login(req,resp);
                    break;
            }

    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher requestDispatcher = null;
            AccountService accountService = new AccountServiceImp();
            LogService logService = new LogServiceImp();

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            int status = accountService.login(username, password);
            System.out.println("status: "+status);

            String row ="";
            int i;
            int logid;
            switch (status) {
                case 1:
                    List<Account> accounts = accountService.getAll();
                    System.out.println("Admin: "+accounts.size());
                    row = "";
                    i=0;
                    for(Account value:accounts){
                        row += "<tr>" +
                                "<td>" + ++i + "</td>" +
                                "<td>" + value.getAccount_id() + "</td>" +
                                "<td>" + value.getFull_name() + "</td>" +
                                "<td><input class=\"edit-input\" type=\"password\" value=\"" + value.getPassword() + "\"></td>" +
                                "<td>"+ value.getEmail()  +"</td>" +
                                "<td>" + value.getPhone() + "</td>" +
                                "<td>" +
                                "<input type=\"checkbox\" name=\"selectRow\">" +
                                "</td>" +
                                "</tr>";
                    }
                    logid = logService.insert(username,"login");
                    req.setAttribute("rows",row);
                    req.setAttribute("logId",logid);
                    requestDispatcher = req.getRequestDispatcher("admin.jsp");
                    requestDispatcher.include(req,resp);
                    break;
                case 0:
                    req.setAttribute("loginError", "Tên đăng nhập hoặc mật khẩu không đúng");
                    requestDispatcher = req.getRequestDispatcher("login.jsp");
                    requestDispatcher.include(req,resp);
                    break;
                case -1:
                    Account account = accountService.getOne(username);
                    System.out.println("user: " + account.getAccount_id());
                    String role = accountService.isAdmin(account.getAccount_id())? "administrator" : "user";

                    req.setAttribute("account",account);
                    req.setAttribute("role",role);
                    logid = logService.insert(username,"login");
                    req.setAttribute("logId",logid);
                    requestDispatcher = req.getRequestDispatcher("user.jsp");
                    requestDispatcher.include(req,resp);
                    break;

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String hidden=req.getParameter("hiddenValue");
        System.out.println(hidden);
        long idLog = Integer.parseInt(hidden);
        try {
            LogService logService = new LogServiceImp();
            logService.update(idLog,"logout");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.include(req,resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
