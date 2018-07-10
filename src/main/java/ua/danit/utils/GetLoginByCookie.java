package ua.danit.utils;

import javax.servlet.http.Cookie;

public class GetLoginByCookie {

    public String getLogin(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userLogin")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
