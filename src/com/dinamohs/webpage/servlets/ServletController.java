package com.dinamohs.webpage.servlets;

import com.dinamohs.webpage.system.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author crealhex
 */
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

    /**
     * Method to validate if the username and password provided are correct
     */
    private void verifyLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

//        UserRules userTracker = UserTrack.getInstance();
//        boolean verified = userTracker.verifyUserData(user);
        // TODO UserService userService = new UserServiceImpl.getInstance();

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
