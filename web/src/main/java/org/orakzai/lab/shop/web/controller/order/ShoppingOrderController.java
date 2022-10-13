package org.orakzai.lab.shop.web.controller.order;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.common.model.Billing;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentMethod;
import org.orakzai.lab.shop.domain.business.payments.service.PaymentService;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingOption;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingQuote;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartService;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.controller.AbstractController;
import org.orakzai.lab.shop.web.controller.cart.facade.ShoppingCartFacade;
import org.orakzai.lab.shop.web.controller.order.facade.OrderFacade;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.dto.customer.AnonymousCustomer;
import org.orakzai.lab.shop.web.dto.order.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(Constants.SHOP_URI + "/order")
public class ShoppingOrderController extends AbstractController {
	
	
	
	@Autowired
	private ShoppingCartFacade shoppingCartFacade;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OrderFacade orderFacade;

	@GetMapping("/checkout.html")
	public String displayCheckout(@CookieValue("cart") String cookie, Model model, HttpServletRequest req, HttpServletResponse resp, Locale locale) throws Exception {
		Language language = (Language) req.getAttribute(Constants.LANGUAGE);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		Customer customer = (Customer) req.getSession().getAttribute(Constants.CUSTOMER);
		
		ShopOrder order = null;
		
		order = super.getSessionAttribute(Constants.ORDER, req);
		
		String shoppingCartCode = (String) req.getSession().getAttribute(Constants.SHOPPING_CART);
		
		ShoppingCart cart = null;
		
		if (StringUtils.isBlank(shoppingCartCode)) {
			if (cookie == null) {
				return "redirect:/shop/cart/shoppingCart.html";
			}
			String merchantCookie[] = cookie.split("_");
			String merchantStoreCode = merchantCookie[0];
			if(!merchantStoreCode.equals(store.getCode())) {
				return "redirect:/shop/cart/shoppingCart.html";
			}
			shoppingCartCode = merchantCookie[1];
		}
		
		cart = shoppingCartFacade.getShoppingCartModel(shoppingCartCode, store);
		
		if (cart == null && customer != null) {
			cart = shoppingCartFacade.getShoppingCartModel(customer, store);
		}
		
		super.setSessionAttribute(Constants.SHOPPING_CART, cart.getShoppingCartCode(), req);
		
		if (shoppingCartCode == null && cart == null) {
			return "redirect:/shop/cart/shoppingCart.html";
		}
		
		if (customer != null) {
			if (cart.getCustomerId() != customer.getId().longValue()) {
				return "redirect:/shop/shoppingCart.html";
			}
		} else {
			customer = orderFacade.initEmptyCustomer(store);
			AnonymousCustomer anonymousCustomer = (AnonymousCustomer) req.getAttribute(Constants.ANONYMOUS_CUSTOMER);
			if (anonymousCustomer != null && anonymousCustomer.getBilling() != null) {
				Billing billing = customer.getBilling();
				billing.setCity(anonymousCustomer.getBilling().getCity());
				Map<String, Country> countriesMap = countryService.getCountriesMap(language);
				Country anonymousCountry = countriesMap.get(anonymousCustomer.getBilling().getCountry());
				if (anonymousCountry != null) {
					billing.setCountry(anonymousCountry);
				}
				Map<String, Zone> zonesMap = zoneService.getZones(language);
				Zone anonymousZone = zonesMap.get(anonymousCustomer.getBilling().getZone());
				if (anonymousZone != null) {
					billing.setZone(anonymousZone);
				}
				if (anonymousCustomer.getBilling().getPostalCode() != null) {
					billing.setPostalCode(anonymousCustomer.getBilling().getPostalCode());
				}
				customer.setBilling(billing);
			}
		}
		
		Set<ShoppingCartItem> items = cart.getLineItems();
		if (CollectionUtils.isEmpty(items)) {
			return "redirect:/shop/shoppingCart.html";
		}
		if (order == null) {
			order = orderFacade.initializeOrder(store, customer, cart, language);
		}
		
		boolean freeShoppingCart = shoppingCartService.isFreeShoppingCart(cart);
		boolean requiresShipping = shoppingCartService.requiresShipping(cart);
		ShippingQuote quote = null;
		
		if (requiresShipping) {
			quote = orderFacade.getShippingQuote(customer, cart, order, store, language);
			model.addAttribute("shippingQuote", quote);
		}
		
		if (quote != null) {
			if (StringUtils.isBlank(quote.getShippingReturnCode())) {
				if (order.getShippingSummary() == null) {
					ShippingSummary summary = orderFacade.getShippingSummary(quote, store, language);
					order.setShippingSummary(summary);
					req.getSession().setAttribute(Constants.SHIPPING_SUMMARY, summary);
				}
				if (order.getSelectedShippingOption() == null) {
					order.setSelectedShippingOption(quote.getSelectedShippingOption());
				}
				List<ShippingOption> options = quote.getShippingOptions();
				req.getSession().setAttribute(Constants.SHIPPING_OPTIONS, options);
				
			} 
			List<Country> shippingCountriesList = orderFacade.getShipToCountry(store, language);
			model.addAttribute("countries", shippingCountriesList);
			
		} else {

			List<Country> countries = countryService.getCountries(language);
			model.addAttribute("countries", countries);
		
		}
		
		if (quote != null && quote.getShippingReturnCode() != null && quote.getShippingReturnCode().equals(ShippingQuote.NO_SHIPPING_MODULE_CONFIGURED) ) {
			log.error("Shipping quote error " + quote.getShippingReturnCode());
			model.addAttribute("errorMessages", quote.getQuoteError());
		}
		
		if (quote != null && !StringUtils.isBlank(quote.getQuoteError())) {
			log.error("Shipping quote error", quote.getQuoteError());
			model.addAttribute("errorMessages", quote.getQuoteError());
		}
		
		if (quote != null && quote.getShippingReturnCode() != null && quote.getShippingReturnCode().equals(ShippingQuote.NO_SHIPPING_TO_SELECTED_COUNTRY)) {
			log.error("Shipping quote error " + quote.getShippingReturnCode());
			model.addAttribute("errorMessages", quote.getShippingReturnCode());
		}
		
		List<PaymentMethod> paymentMethods = paymentService.getAcceptedPaymentMethods(store);
		if (CollectionUtils.isEmpty(paymentMethods) && !freeShoppingCart) {
			log.error("No payment method configured");
			model.addAttribute("errorMessages", "No payments configured");
		}
		
		if (!CollectionUtils.isEmpty(paymentMethods)) {
			PaymentMethod defaultPaymentSelected = null;
			for (PaymentMethod paymentMethod : paymentMethods) {
				if (paymentMethod.isDefaultSelected()) {
					defaultPaymentSelected = paymentMethod;
					break;
				}
				
			}
			if (defaultPaymentSelected == null) {
				defaultPaymentSelected = paymentMethods.get(0);
				defaultPaymentSelected.setDefaultSelected(true);
			}
		}
		
		ShoppingCartData shoppingCart = shoppingCartFacade.getShoppingCartData(cart);
		model.addAttribute("cart", shoppingCart);
		
		OrderTotalSummary orderTotalSummary = orderFacade.calculateOrderTotal(store, order, language);
		order.setOrderTotalSummary(orderTotalSummary);
		
		super.setSessionAttribute(Constants.ORDER_SUMMARY, orderTotalSummary, req);
		model.addAttribute("order", order);
		model.addAttribute("paymentMethods", paymentMethods);
		
		return ControllerConstants.Templates.Checkout.checkout;
		
	}

}
