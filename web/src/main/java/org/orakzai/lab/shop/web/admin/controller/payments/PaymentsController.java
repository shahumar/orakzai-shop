package org.orakzai.lab.shop.web.admin.controller.payments;

import static org.orakzai.lab.shop.domain.constants.Constants.PRODUCTION_ENVIRONMENT;
import static org.orakzai.lab.shop.domain.constants.Constants.TEST_ENVIRONMENT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.payments.model.TransactionType;
import org.orakzai.lab.shop.domain.business.payments.service.PaymentService;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.orakzai.lab.shop.domain.modules.integration.IntegrationException;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/payments/")
public class PaymentsController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MessageSource messages;

	@GetMapping("paymentMethods.html")
	public String getPaymentMethods(Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setMenu(model, req);
		var store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		
		List<IntegrationModule> modules = paymentService.getPaymentMethods(store);
		Map<String, IntegrationConfiguration> configuredModules = paymentService.getPaymentModulesConfigured(store);
		model.addAttribute("modules", modules);
		model.addAttribute("configuredModules", configuredModules);
		return ControllerConstants.Templates.Payment.paymentMethods;
	}

	
	@PreAuthorize("hasRole('PAYMENT')")
	@GetMapping("paymentMethod.html")
	public String displayPaymentMethod(@RequestParam("code") String code, Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setMenu(model, req);
		var store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		
		IntegrationConfiguration configuration = paymentService.getPaymentConfiguration(code, store);
		
		if (configuration == null) {
			configuration = new IntegrationConfiguration();
			configuration.setEnvironment(PRODUCTION_ENVIRONMENT);
			var keys = new HashMap<String, String>();
			keys.put("transaction", TransactionType.AUTHORIZECAPTURE.name());
			configuration.setIntegrationKeys(keys);
		}
		
		configuration.setModuleCode(code);
		var enviroments = new ArrayList<String>();
		enviroments.add(TEST_ENVIRONMENT);
		enviroments.add(PRODUCTION_ENVIRONMENT);
		model.addAttribute("configuration", configuration);
		model.addAttribute("environments", enviroments);
		return ControllerConstants.Templates.Payment.paymentMethod;
	}
	
	@PreAuthorize("hasRole('PAYMENT')")
	@PostMapping("savePaymentMethod.html")
	public String savePaymentMethod(@ModelAttribute("configuration") IntegrationConfiguration configuration, BindingResult result, Model model, HttpServletRequest req, HttpServletResponse resp, Locale locale) throws Exception {
		
		this.setMenu(model, req);
		var store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		var enviroments = new ArrayList<String>();
		enviroments.add(TEST_ENVIRONMENT);
		enviroments.add(PRODUCTION_ENVIRONMENT);
		
		model.addAttribute("environments", enviroments);
		model.addAttribute("configuration", configuration);
		
		try {
			paymentService.savePaymentModuleConfiguration(configuration, store);
		} catch (Exception e) {
			if (e instanceof IntegrationException) {
				if (((IntegrationException) e).getErrorCode() == IntegrationException.ERROR_VALIDATION_SAVE) {
					var errorCodes = ((IntegrationException)e).getErrorFields();
					for (String strCode : errorCodes) {
						model.addAttribute(strCode, messages.getMessage("message.fielderror", null, locale));
					}
					model.addAttribute("validationError", "validationError");
					return ControllerConstants.Templates.Payment.paymentMethod;
				}
			} else {
				throw new Exception(e);
			}
		}
		
		model.addAttribute("success", "success");
		return "redirect:/admin/payments/paymentMethods.html";
	}
	
	private void setMenu(Model model, HttpServletRequest req) throws Exception {
		var activeMenus = new HashMap<String, String>();
		activeMenus.put("payment", "payment");
		activeMenus.put("payment-methods", "payment-methods");
		
		@SuppressWarnings("unchecked")
		var menus = (Map<String, Menu>) req.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu) menus.get("payment");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		
	}

}
