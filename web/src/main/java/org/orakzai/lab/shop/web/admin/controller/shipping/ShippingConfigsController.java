package org.orakzai.lab.shop.web.admin.controller.shipping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingConfiguration;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingType;
import org.orakzai.lab.shop.domain.business.shipping.service.ShippingService;
import org.orakzai.lab.shop.domain.utils.ajax.AjaxResponse;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShippingConfigsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShippingConfigsController.class);
	
	@Autowired
	private ShippingService shippingService;
	
	@Autowired
	private CountryService countryService;
	
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value="/admin/shipping/shippingConfigs.html", method=RequestMethod.GET)
	public String displayShippingConfigs(Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		setMenu(model, req);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		ShippingConfiguration shippingConfiguration = shippingService.getShippingConfiguration(store);
		if (shippingConfiguration == null) {
			shippingConfiguration = new ShippingConfiguration();
			shippingConfiguration.setShippingType(ShippingType.INTERNATIONAL);
		}
		model.addAttribute("configuration", shippingConfiguration);
		
		return ControllerConstants.Shipping.SHIPPING_CONFIGS;
	}
	
	@SuppressWarnings({"unchecked"})
	@PreAuthorize("hasRole('SHIPPING')")
	@RequestMapping(value = "/admin/shipping/countries/paging.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pageCountries(HttpServletRequest req, HttpServletResponse resp) {
		String countryName = req.getParameter("name");
		AjaxResponse response = new AjaxResponse();
		
		try {
			Language language = (Language) req.getAttribute("LANGUAGE");
			MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
			Map<String, Country> countries = countryService.getCountriesMap(language);
			
			List<String> includedCountries = shippingService.getSupportedCountries(store);
			for (String key : countries.keySet()) {
				Country country = (Country) countries.get(key);
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("code", country.getIsoCode());
				entry.put("name", country.getName());
				
				if (includedCountries.contains(key)) {
					entry.put("supported", true);
				} else {
					entry.put("supported", false);
				}
				if (StringUtils.isBlank(countryName)) {
					response.addDataEntry(entry);
				} else if (country.getName().contains(countryName)) {
					response.addDataEntry(entry);
				}
				
				response.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			} 
		}catch (Exception e) {
			LOGGER.error("Error while paging shipping countries", e);
			response.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}

		String returnString = response.toJSONString();
		return returnString;
	}

	private void setMenu(Model model, HttpServletRequest req) {
		Map<String, String> activeMenus = new HashMap<>();
		activeMenus.put("shipping", "shipping");
		activeMenus.put("shipping-configs", "shipping-configs");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>) req.getAttribute("MENUMAP");
		Menu currentMenu = (Menu) menus.get("shipping");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		
	}

}
