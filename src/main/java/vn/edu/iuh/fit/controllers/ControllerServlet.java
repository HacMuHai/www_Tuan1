package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.services.imp.AccountServiceImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/controllerServlet", "/control"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();

        if (action.equalsIgnoreCase("login")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/web/login.jsp");
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
        PrintWriter out = resp.getWriter();
        try {
            AccountService accountService = new AccountServiceImp();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            int status = accountService.login(username, password);
            System.out.println(status);
            switch (status) {
                case 1:
                    resp.setContentType("text/html");
                    out.println("<html><body>");
                    out.println("<h1>" + "admin" + "</h1>");
                    out.println("</body></html>");
//                    resp.sendRedirect("/web/login.jsp");
                    break;
                case 0:
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/web/login.jsp");
                    requestDispatcher.include(req,resp);
                   break;
                case -1:
                    resp.setContentType("text/html");
                    out.println("<html><body>");
                    out.println("<h1>" + "user" + "</h1>");
                    out.println("</body></html>");
//                    resp.sendRedirect("/web/login.jsp");
                    break;

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
