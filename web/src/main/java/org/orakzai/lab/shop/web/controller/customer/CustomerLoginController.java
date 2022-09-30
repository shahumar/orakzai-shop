package org.orakzai.lab.shop.web.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartService;
import org.orakzai.lab.shop.domain.utils.ajax.AjaxResponse;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.controller.AbstractController;
import org.orakzai.lab.shop.web.controller.customer.facade.CustomerFacade;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.dto.customer.SecuredCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/shop/customer")
public class CustomerLoginController extends AbstractController {
	
	@Autowired
	private CustomerFacade customerFacade;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping("/logon.html")
	public @ResponseBody String logon(@ModelAttribute SecuredCustomer securedCustomer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		AjaxResponse jsonObject = new AjaxResponse();
		
		try {
			log.debug("Authenticating user " + securedCustomer.getUserName());
			MerchantStore store = (MerchantStore) req.getAttribute(Constants.MERCHANT_STORE);
			Language language = (Language) req.getAttribute(Constants.LANGUAGE);
			Customer customerModel = customerFacade.getCustomerByUserName(securedCustomer.getUserName(), store);
			if (customerModel == null) {
				jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				return jsonObject.toJSONString();
			}
			System.out.println(securedCustomer.getEmailAddress() + " " + securedCustomer.getClearPassword());
			customerFacade.authenticate(customerModel, securedCustomer.getUserName(), securedCustomer.getPassword());
			super.setSessionAttribute(Constants.CUSTOMER, customerModel, req);
			jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			log.info("Fetching and merging Shopping cart data");
			final String sessionShoppingCartCode = (String) req.getSession().getAttribute(Constants.SHOPPING_CART);
			if (!StringUtils.isBlank(sessionShoppingCartCode)) {
				ShoppingCartData shoppingCartData = customerFacade.mergeCart(customerModel, sessionShoppingCartCode, store, language);
				if (shoppingCartData != null) {
					jsonObject.addEntry(Constants.SHOPPING_CART, shoppingCartData.getCode());
					req.getSession().setAttribute(Constants.SHOPPING_CART, shoppingCartData.getCode());
				}
			} else {
				ShoppingCart cartModel = shoppingCartService.getByCustomer(customerModel);
				if (cartModel != null) {
					jsonObject.addEntry(Constants.SHOPPING_CART, cartModel.getShoppingCartCode());
					req.getSession().setAttribute(Constants.SHOPPING_CART, cartModel.getShoppingCartCode());
				}
			}
		} catch (AuthenticationException ex) {
			log.error("Error occured", ex);
			jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		} catch (Exception e) {
			log.error("Error occured ", e);
			jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		return jsonObject.toJSONString();
	}
	
	

}
