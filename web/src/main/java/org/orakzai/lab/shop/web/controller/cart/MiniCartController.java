package org.orakzai.lab.shop.web.controller.cart;

import javax.servlet.http.HttpServletRequest;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.controller.AbstractController;
import org.orakzai.lab.shop.web.controller.cart.facade.ShoppingCartFacade;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/shop/cart")
public class MiniCartController extends AbstractController{
	
	@Autowired
	private ShoppingCartFacade shoppingCartFacade;
	
	@RequestMapping("/displayMiniCartByCode.html")
	public @ResponseBody ShoppingCartData displayMiniCart(final String shoppingCartCode, HttpServletRequest req, Model modle) {
		try {
			var store = (MerchantStore) req.getAttribute(Constants.MERCHANT_STORE);
			Customer customer = getSessionAttribute(Constants.CUSTOMER, req);
			ShoppingCartData cart = shoppingCartFacade.getShoppingCartData(customer, store, shoppingCartCode);
			if (cart != null) {
				req.getSession().setAttribute(Constants.SHOPPING_CART, cart.getCode());
			}
			if (cart == null) {
				req.getSession().removeAttribute(Constants.SHOPPING_CART);
			}
			return cart;
		} catch (Exception e) {
			log.error("Error while getting the shopping cart", e);
		}
		
		return null;
	}

}
