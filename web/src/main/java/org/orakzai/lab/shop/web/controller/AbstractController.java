package org.orakzai.lab.shop.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.web.utils.SessionUtil;

public class AbstractController {

	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(final String key, HttpServletRequest req) {
		return (T) SessionUtil.getSessionAttribute(key, req);
	}

	public void setSessionAttribute(String key, Object value, HttpServletRequest req) {
		SessionUtil.setSessionAttribute(key, value, req);
		
	}
}
