package org.orakzai.lab.shop.web.utils;

import javax.servlet.http.HttpServletRequest;


public class SessionUtil {

	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key, HttpServletRequest req) {
		return (T) req.getSession().getAttribute(key);
	}

	public static void setSessionAttribute(String key, Object value, HttpServletRequest req) {
		req.getSession().setAttribute(key, value);
		
	}

}
