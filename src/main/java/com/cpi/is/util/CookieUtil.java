package com.cpi.is.util;

import javax.servlet.http.Cookie;

public class CookieUtil {

	public static String getCookieValue(Cookie[] cookies, String cookieName) {
		String cookieValue = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		return cookieValue;
	}
	
}