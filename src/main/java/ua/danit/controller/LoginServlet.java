package ua.danit.controller;

import ua.danit.dao.UserDAO;
import ua.danit.model.User;
import ua.danit.utils.HtmlFreeMarker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HtmlFreeMarker htmlFreeMarker = new HtmlFreeMarker();
        Map<String, Object> map = new HashMap<>();

        htmlFreeMarker.getHtmlPage(map, resp, "login.html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("userLogin");
        String passward = req.getParameter("passward");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(login);

        if (user != null && user.getPassword().equals(passward)) {
            Cookie cookie = new Cookie("userLogin", req.getParameter("userLogin"));
            cookie.setMaxAge(120);

            resp.addCookie(cookie);
            resp.sendRedirect("users");

        } else {
            resp.sendRedirect("/login");
        }

    }
}
