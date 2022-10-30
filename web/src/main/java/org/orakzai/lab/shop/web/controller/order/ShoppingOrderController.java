package org.orakzai.lab.shop.web.controller.order;

import static org.orakzai.lab.shop.web.constants.Constants.SHIPPING_OPTIONS;
import static org.orakzai.lab.shop.web.constants.Constants.SHIPPING_SUMMARY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.common.model.Billing;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.order.service.OrderService;
import org.orakzai.lab.shop.domain.business.order.service.orderproduct.OrderProductDownloadService;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentMethod;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
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
import org.orakzai.lab.shop.web.controller.customer.facade.CustomerFacade;
import org.orakzai.lab.shop.web.controller.order.facade.OrderFacade;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.dto.customer.AnonymousCustomer;
import org.orakzai.lab.shop.web.dto.customer.PersistableCustomer;
import org.orakzai.lab.shop.web.dto.order.ReadableShippingSummary;
import org.orakzai.lab.shop.web.dto.order.ShopOrder;
import org.orakzai.lab.shop.web.dto.userpassword.UserReset;
import org.orakzai.lab.shop.web.mapper.order.ReadableShippingSummaryPopulator;
import org.orakzai.lab.shop.web.utils.EmailTemplatesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private MessageSource messages;

	@Autowired
	private CustomerFacade customerFacade;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderProductDownloadService orderProductDownloadService;

	@Autowired
	private EmailTemplatesUtils emailTemplateUtils;

	@Autowired
	private OrderService orderService;

	@SuppressWarnings("unused")
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
	
	@GetMapping("/commitOrder.html")
	public String commitOrder(@CookieValue("cart") String cookie, @Valid @ModelAttribute("order") ShopOrder order, BindingResult bindingResult, Model model, HttpServletRequest req, HttpServletResponse resp, Locale locale) throws Exception {
		var store = (MerchantStore) req.getAttribute(Constants.MERCHANT_STORE);
		Language language = (Language) req.getAttribute(Constants.LANGUAGE);
		
		try {
			String shoppingCartCode = (String) req.getSession().getAttribute(Constants.SHOPPING_CART);
			if (shoppingCartCode == null) {
				if (cookie == null) {
					return ControllerConstants.Templates.Pages.timeout;
				}
				String merchantCookie[] = cookie.split("_");
				String merchantStoreCode = merchantCookie[0];
				if (!merchantStoreCode.equals(store.getCode())) {
					return ControllerConstants.Templates.Pages.timeout;
				}
				shoppingCartCode = merchantCookie[1];
			}
			
			ShoppingCart cart = null;
			if (StringUtils.isBlank(shoppingCartCode)) {
				return ControllerConstants.Templates.Pages.timeout;
			}
			cart = shoppingCartFacade.getShoppingCartModel(shoppingCartCode, store);
			var items = cart.getLineItems();
			var cartItems = new ArrayList<ShoppingCartItem>(items);
			order.setShoppingCartItems(cartItems);
			var paymentMethods = paymentService.getAcceptedPaymentMethods(store);
			boolean freeShoppingCart = shoppingCartService.isFreeShoppingCart(cart);
			
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
			
			ShippingQuote quote = orderFacade.getShippingQuote(order.getCustomer(), cart, order, store, language);
			model.addAttribute("shippingQuote", quote);
			model.addAttribute("paymentMethods", paymentMethods);
			if (quote != null) {
				var shippingCountriesList = orderFacade.getShipToCountry(store, language);
				model.addAttribute("countries", shippingCountriesList);
			} else {
				var countries = countryService.getCountries(language);
				model.addAttribute("countries", countries);
			}
			
			if (order.getSelectedShippingOption() != null) {
				var summary = (ShippingSummary) req.getSession().getAttribute(SHIPPING_SUMMARY);
				@SuppressWarnings("unchecked")
				var options = (List<ShippingOption>) req.getSession().getAttribute(SHIPPING_OPTIONS);
				if (summary == null) {
					summary = orderFacade.getShippingSummary(quote, store, language);
					req.getSession().setAttribute(SHIPPING_SUMMARY, summary);
				}
				if (options == null) {
					options = quote.getShippingOptions();
					req.getSession().setAttribute(Constants.SHIPPING_OPTIONS, options);
				}
				
				ReadableShippingSummary readableSummary = new ReadableShippingSummary();
				ReadableShippingSummaryPopulator readableSummaryPopulator = new ReadableShippingSummaryPopulator();
				readableSummaryPopulator.setPricingService(pricingService);
				readableSummaryPopulator.populate(summary, readableSummary, store, language);
				
				if(!CollectionUtils.isEmpty(options)) {
					ShippingOption quoteOption = null;
					ShippingOption selectedOption = order.getSelectedShippingOption();
					
					for (ShippingOption shipOption : options) {
						if (!StringUtils.isBlank(shipOption.getOptionId()) && shipOption.getOptionId().equals(selectedOption.getOptionId())) {
							quoteOption = shipOption;
						}
					}
					if(quoteOption==null) {
						quoteOption = options.get(0);
					}
					readableSummary.setSelectedShippingOption(quoteOption);
					readableSummary.setShippingOptions(options);
					summary.setShippingOption(quoteOption.getOptionId());
					summary.setShipping(quoteOption.getOptionPrice());
					
				}
				
				order.setShippingSummary(summary);
				
			}
			
			OrderTotalSummary totalSummary = super.getSessionAttribute(Constants.ORDER_SUMMARY, req);
			if (totalSummary == null) {
				totalSummary = orderFacade.calculateOrderTotal(store, order, language);
				super.setSessionAttribute(Constants.ORDER_SUMMARY, totalSummary, req);
			}
			order.setOrderTotalSummary(totalSummary);
			orderFacade.validateOrder(order, bindingResult, new HashMap<String, String>(), store, locale);
			if (bindingResult.hasErrors()) {
				log.info("found {} validation error while validating in customer registration ", bindingResult.getErrorCount());
				return ControllerConstants.Templates.Checkout.checkout;
			}
			@SuppressWarnings("unused")
			Order modelOrder = this.commitOrder(order, req, locale);
			
			
		} catch (ServiceException e) {
			log.error("Error while creating an order", e);
			String defaultMessage = messages.getMessage("message.error", null, locale);
			model.addAttribute("errorMessages", defaultMessage);
			
			if (e.getExceptionType() == ServiceException.EXCEPTION_VALIDATION) {
				if (!StringUtils.isBlank(e.getMessageCode())) {
					String messageLabel = messages.getMessage(e.getMessageCode(), null, defaultMessage, locale);
					model.addAttribute("errorMessages", messageLabel);
				}
			} else if (e.getExceptionType() == ServiceException.EXCEPTION_PAYMENT_DECLINED) {
				String paymentDeclinedMessage = messages.getMessage("message.payment.declined", null, locale);
				if (!StringUtils.isBlank(e.getMessageCode())) {
					String messageLabel = messages.getMessage(e.getMessageCode(), null, paymentDeclinedMessage, locale);
					model.addAttribute("errorMessages", messageLabel);
				} else {
					model.addAttribute("errorMessages", paymentDeclinedMessage);
				}
			}
			
			return ControllerConstants.Templates.Checkout.checkout;
		} catch (Exception e) {
			log.error("Error while commiting order", e);
			throw e;
		}
		
		return "redirect:/shop/order/confirmation.html";
	}

	private Order commitOrder(@Valid ShopOrder order, HttpServletRequest req, Locale locale) throws Exception, ServiceException {
		var store = (MerchantStore) req.getAttribute(Constants.MERCHANT_STORE);
		var language = (Language) req.getAttribute(Constants.LANGUAGE);
		
		String userName = null;
		String password = null;
		
		PersistableCustomer customer = order.getCustomer();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Customer authCustomer = null;
		
		if (auth != null && req.isUserInRole("AUTH_CUSTOMER")) {
			authCustomer = customerFacade.getCustomerByUserName(auth.getName(), store);
			customer.setUserName(authCustomer.getNick());
			customer.setEncodedPassword(authCustomer.getPassword());
			customer.setId(authCustomer.getId());
		} else {
			customer.setId(null);
		}
		
		if (customer.getId() == null || customer.getId() == 0) {
			password = UserReset.generateRandomString();
			String encodedPassword = passwordEncoder.encode(password);
			customer.setEncodedPassword(encodedPassword);
		}
		
		if (order.isShipToBillingAdress()) {
			customer.setDelivery(customer.getBilling());
		}
		
		Customer modelCustomer = null;
		
		try {
			if (authCustomer == null) {
				modelCustomer = customerFacade.getCustomerModel(customer, store, language);
				customerFacade.setCustomerModelDefaultProperties(modelCustomer, store);
				userName = modelCustomer.getNick();
				log.debug("About to persist volatile customer to database");
				customerService.saveOrUpdate(modelCustomer);
			} else {
				modelCustomer = customerFacade.populateCustomerModel(authCustomer, customer, store, language);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		Order modelOrder = null;
		Transaction initialTransaction = (Transaction) super.getSessionAttribute(Constants.INIT_TRANSACTION_KEY, req);
		if (initialTransaction != null) {
			modelOrder = orderFacade.processOrder(order, modelCustomer, initialTransaction, store, language);
		} else {
			modelOrder = orderFacade.processOrder(order, modelCustomer, store, language);
		}
		
		super.setSessionAttribute(Constants.ORDER_ID, modelOrder.getId(), req);
		
		super.setSessionAttribute(Constants.ORDER_ID_TOKEN, modelOrder.getId(), req);
		
		String cartCode = super.getSessionAttribute(Constants.SHOPPING_CART, req);
		
		if (StringUtils.isNotBlank(cartCode)) {
			try {
				shoppingCartFacade.deleteShoppingCart(cartCode, store);
			} catch (Exception e) {
				log.error("Cannot delete cart " + cartCode, e);
				throw new ServiceException(e);
			}
		}
		
		super.removeAttribute(Constants.ORDER, req);
        super.removeAttribute(Constants.ORDER_SUMMARY, req);
        super.removeAttribute(Constants.INIT_TRANSACTION_KEY, req);
        super.removeAttribute(Constants.SHIPPING_OPTIONS, req);
        super.removeAttribute(Constants.SHIPPING_SUMMARY, req);
        super.removeAttribute(Constants.SHOPPING_CART, req);
        
        try {
        	modelCustomer = customerFacade.getCustomerByUserName(modelCustomer.getNick(), store);
        	var orderProductDownloads = orderProductDownloadService.getByOrderId(modelOrder.getId());
        	if (CollectionUtils.isNotEmpty(orderProductDownloads)) {
        		log.debug("Is user authenticated ? ", auth.isAuthenticated());
        		if (auth != null && req.isUserInRole("AUTH_CUSTOMER")) {
        			
        		} else {
        			customerFacade.authenticate(modelCustomer, userName, password);
        			super.setSessionAttribute(Constants.CUSTOMER, modelCustomer, req);
        		}
        		
        		if (order.getCustomer().getId() == null || order.getCustomer().getId().longValue() == 0) {
        			customer.setClearPassword(password);
        			customer.setUserName(userName);
        			emailTemplateUtils.sendRegistrationEmail(customer, store, locale, req.getContextPath());
        		}
        	} 
        	emailTemplateUtils.sendOrderEmail(modelCustomer, modelOrder, locale, language, store, req.getContextPath());
        	if (orderService.hasDownloadFiles(modelOrder)) {
        		emailTemplateUtils.sendOrderDownloadEmail(modelCustomer, modelOrder, store, locale, req.getContextPath());
        	}
        	
        } catch (Exception e) {
			log.error("Error while post processing order", e);
		}
		
		return modelOrder;
	}

}
