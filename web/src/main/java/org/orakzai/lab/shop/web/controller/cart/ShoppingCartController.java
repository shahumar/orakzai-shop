package org.orakzai.lab.shop.web.controller.cart;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartService;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.controller.AbstractController;
import org.orakzai.lab.shop.web.controller.cart.facade.ShoppingCartFacade;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartItem;
import org.orakzai.lab.shop.web.dto.shop.PageInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/shop/cart/")
public class ShoppingCartController extends AbstractController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShoppingCartFacade shoppingCartFacade;
	
	@Autowired
	private MessageSource messages;

	@PostMapping("/addShoppingCartItem.html")
	public @ResponseBody ShoppingCartData addShoppingCartItem(@RequestBody final ShoppingCartItem item, final HttpServletRequest req, final HttpServletResponse resp, final Locale locale) throws Exception {
		
		ShoppingCartData shoppingCart = null;
		MerchantStore store = getSessionAttribute(Constants.MERCHANT_STORE, req);
		Language language = (Language) req.getAttribute(Constants.LANGUAGE);
		Customer customer = getSessionAttribute(Constants.CUSTOMER, req);
		if (customer != null) {
			ShoppingCart customerCart = shoppingCartService.getByCustomer(customer);
			if (customerCart != null) {
				shoppingCart = shoppingCartFacade.getShoppingCartData(customerCart);
			}
		}
		if (shoppingCart == null && !StringUtils.isBlank(item.getCode())) {
			shoppingCart = shoppingCartFacade.getShoppingCartData(item.getCode(), store);
		}
		
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCartData();
			String code = UUID.randomUUID().toString().replaceAll("-", "");
			shoppingCart.setCode(code);
		}
		shoppingCart = shoppingCartFacade.addItemsToShoppingCart(shoppingCart, item, store, language, customer);
		req.getSession().setAttribute("TEST_CART_HERE", shoppingCart.getCode());
		return shoppingCart;
		
	}

	@GetMapping("/shoppingCart.html")
	public String displayShoppingCart(final Model model, final HttpServletRequest req, final HttpServletResponse resp, final Locale locale) throws Exception {
		log.info("Starting to calculate shopping cart...");
		
		PageInformation pageInformation = new PageInformation();
		pageInformation.setPageTitle(messages.getMessage("label.cart.placeholder", null, locale));
		req.setAttribute(Constants.REQUEST_PAGE_INFORMATION, pageInformation);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.MERCHANT_STORE);
		Customer customer = getSessionAttribute(Constants.CUSTOMER, req);
		String cartCode = (String) req.getSession().getAttribute(Constants.SHOPPING_CART);
		if (StringUtils.isBlank(cartCode)) {
			return ControllerConstants.Templates.ShoppingCart.shoppingCart;
		}
		
		ShoppingCartData shoppingCart = shoppingCartFacade.getShoppingCartData(customer, store, cartCode);
		model.addAttribute("cart", shoppingCart);
		return ControllerConstants.Templates.ShoppingCart.shoppingCart;
	}
}
