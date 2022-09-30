package org.orakzai.lab.shop.web.admin.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.common.model.CriteriaOrderBy;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.OrderCriteria;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.orakzai.lab.shop.domain.business.user.service.UserService;
import org.orakzai.lab.shop.web.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = {"/admin/home.html", "/admin/", "/admin"})
	public String dashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		Language language = (Language) request.getAttribute(Constants.LANGUAGE);
		
		var activeMenus = new HashMap<String, String>();
		activeMenus.put("home", "home");
		model.addAttribute("activeMenus", activeMenus);
		MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
		var countries = countryService.getCountriesMap(language);
		Country storeCountry = store.getCountry();
		Country country = countries.get(storeCountry.getIsoCode());
		String sCurrentUser = request.getRemoteUser();
		User currentUser = userService.getByUserName(sCurrentUser);
		model.addAttribute("store", store);
		model.addAttribute("country", country);
		model.addAttribute("user", currentUser);
		
		OrderCriteria orderCriteria = new OrderCriteria();
		orderCriteria.setMaxCount(10);
		orderCriteria.setOrderBy(CriteriaOrderBy.DESC);
		
		return ControllerConstants.Templates.ADMIN_DASHBOARD_TPL;
	}

}
