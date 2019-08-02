package com.db.util;

import javax.servlet.http.Cookie;

public class Token {
    public static String getToken(Cookie[] cookies, String ticket) {
        String token = null;
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (ticket.equals(name)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        return token;
    }
}
