package ua.danit.controller;

import ua.danit.dao.LikedDAO;
import ua.danit.dao.UserDAO;
import ua.danit.model.User;
import ua.danit.utils.GetLoginByCookie;
import ua.danit.utils.HtmlFreeMarker;
import ua.danit.utils.UserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/liked")
public class LikedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        GetLoginByCookie getLoginByCookie = new GetLoginByCookie();
        LikedDAO likedDAO = new LikedDAO();
        UserDAO userDAO = new UserDAO();
        List<String> personList = likedDAO.getListOfLikedUsers(getLoginByCookie.getLogin(cookies));
        UserList htmlUtil = new UserList(personList);
        List<User> person = htmlUtil.userList();
        HtmlFreeMarker htmlFreeMarker = new HtmlFreeMarker();

        Map<String, Object> profile = new HashMap<>();

        profile.put("userlogin", cookies[0].getValue());
        profile.put("items", person);

        htmlFreeMarker.getHtmlPage(profile, resp, "people-list.html");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
