package com.dinamohs.webpage.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletController", urlPatterns = "/controller")
public class ServletController extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "showPersons":
                userState(req, resp);
                break;
            case "validateUser":
                verifyLogin(req, resp);
                break;
            default:
                badRequest(req, resp);
                break;
        }

    }

    private void userState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String)session.getAttribute("user");
        if (user != null) {
//            showPersons(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/page/core/login.jsp").forward(req, resp);
        }
    }

    private void verifyLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

//        User userDTO = new User();
//        UserService userService = new UserServiceImpl.getInstance();

    }

    private void badRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "Error en la petición";
        req.setAttribute("message", message);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}