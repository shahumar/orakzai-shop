package org.orakzai.lab.shop.web.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/customer")
public class CustomerAccountController extends AbstractController {
	
	@GetMapping("/customerLogon.html")
	public String displayLogon(Model model, HttpServletRequest request, HttpServletResponse resp) throws Exception {
		MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, request);
		return ControllerConstants.Templates.Customer.customerLogon;
	}

}
