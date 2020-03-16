package com.dinamohs.webpage.servlets;

import com.dinamohs.webpage.services.couriers.PersonTracker;
import com.dinamohs.webpage.services.couriers.UserTracker;
import com.dinamohs.webpage.services.rules.PersonRules;
import com.dinamohs.webpage.services.rules.UserRules;
import com.dinamohs.webpage.system.dto.Person;
import com.dinamohs.webpage.system.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            case "addPerson":
                addPerson(req, resp);
                break;
            case "editPerson":
                editPerson(req, resp);
                break;
            case "deletePerson":
                deletePerson(req, resp);
                break;
            case "savePerson":
                savePerson(req, resp);
                break;
            case "exit":
                logout(req, resp);
                break;
            default:
                badRequest(req, resp);
                break;
        }

    }

    /**
     * Pocket
     * Dynalist
     * Refind
     * // TODO(Luis Enco): List of productivity apps
     * Forest
     * Google keep
     * LastPass
     */

    private void userState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String)session.getAttribute("user");
        if (user != null) {
            showPersons(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/page/core/login.jsp").forward(req, resp);
        }
    }

    private void showPersons(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String user = (String)session.getAttribute("user");

        if (user != null) {
            PersonRules personTracker = PersonTracker.getInstance();
            List<Person> personList = personTracker.getAllPersons();

            if ((personList != null) && (personList.size() > 0)) {
                req.setAttribute("personList", personList);
            }

            req.getRequestDispatcher("/WEB-INF/page/core/personList.jsp").forward(req, resp);
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

        UserRules userTracker = UserTracker.getInstance();
        boolean exists = userTracker.verifyUserData(user);

        if (exists) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user.getUsername());
            showPersons(req, resp);
        } else {
            req.setAttribute("message", "El usuario o contraseña son incorrectos");
            req.getRequestDispatcher("/WEB-INF/page/core/login.jsp").forward(req, resp);
        }
    }

    private void addPerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/page/core/personDetail.jsp").forward(req, resp);
    }

    private void editPerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idPersonParam = req.getParameter("person");
        Integer idPerson;

        if (idPersonParam != null && !idPersonParam.trim().equals("")) {
            idPerson = new Integer(idPersonParam);

            PersonRules personRules = PersonTracker.getInstance();
            Person person = personRules.getPersonById(idPerson);

            req.setAttribute("person", person);
            req.getRequestDispatcher("/WEB-INF/page/core/personDetail.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("message", "Debe seleccionar un elemento para editar");
        showPersons(req, resp);
    }

    private void deletePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] idParams = req.getParameterValues("person");
        List<Integer> codes = new ArrayList<>();

        if (idParams != null && idParams.length > 0) {
            for (String id : idParams) {
                codes.add(new Integer(id));
            }
        }

        PersonRules personRules = PersonTracker.getInstance();
        boolean deleteStatus = personRules.deletePersons(codes);

        String message;
        if (deleteStatus) {
            message = "Eliminación correctamente ejecutada";
        } else {
            message = "Ocurrió un error al eliminar los registros";
        }

        req.setAttribute("message", message);
        showPersons(req, resp);
    }

    private void savePerson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id;
        try {
            id = Integer.parseInt(req.getParameter("idPerson"));
        } catch (NumberFormatException e) {
            id = null;
        }

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        Person person = new Person();
        person.setIdPerson(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);

        PersonRules personRules = PersonTracker.getInstance();
        boolean saveStatus = personRules.savePerson(person);

        String message;
        if (saveStatus) {
            message = "Se guardó el elemento correctamente";
        } else {
            message = "No se guardo correctamente el elemento";
        }

        req.setAttribute("message", message);
        showPersons(req, resp);
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("index.jsp").forward(req, resp);
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
