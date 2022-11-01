package org.orakzai.lab.shop.web.controller.order.facade;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.attribute.ProductAttributeService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.file.DigitalProductService;
import org.orakzai.lab.shop.domain.business.common.model.Billing;
import org.orakzai.lab.shop.domain.business.common.model.Delivery;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.order.model.OrderSummary;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatus;
import org.orakzai.lab.shop.domain.business.order.model.payment.CreditCard;
import org.orakzai.lab.shop.domain.business.order.service.OrderService;
import org.orakzai.lab.shop.domain.business.payments.model.CreditCardPayment;
import org.orakzai.lab.shop.domain.business.payments.model.CreditCardType;
import org.orakzai.lab.shop.domain.business.payments.model.Payment;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentType;
import org.orakzai.lab.shop.domain.business.payments.model.PaypalPayment;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingProduct;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingQuote;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.business.shipping.service.ShippingService;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartService;
import org.orakzai.lab.shop.domain.utils.CreditCardUtils;
import org.orakzai.lab.shop.web.controller.customer.facade.CustomerFacade;
import org.orakzai.lab.shop.web.dto.customer.Address;
import org.orakzai.lab.shop.web.dto.customer.PersistableCustomer;
import org.orakzai.lab.shop.web.dto.order.OrderEntity;
import org.orakzai.lab.shop.web.dto.order.OrderTotal;
import org.orakzai.lab.shop.web.dto.order.PersistableOrder;
import org.orakzai.lab.shop.web.dto.order.ReadableOrder;
import org.orakzai.lab.shop.web.dto.order.ReadableOrderList;
import org.orakzai.lab.shop.web.dto.order.ShopOrder;
import org.orakzai.lab.shop.web.mapper.customer.PersistableCustomerPopulator;
import org.orakzai.lab.shop.web.mapper.order.OrderProductPopulator;
import org.orakzai.lab.shop.web.utils.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("orderFacade")
public class OrderFacadeImpl implements OrderFacade {
	
	@Autowired
	private CustomerFacade customerFacade;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ShippingService shippingService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private DigitalProductService digitalProductService;

	@Autowired
	private ProductAttributeService productAttributeService;

	@Autowired
	private ProductService productService;

	@Override
	public ShopOrder initializeOrder(MerchantStore store, Customer customer, ShoppingCart shoppingCart,
			Language language) throws Exception {
		ShopOrder order = new ShopOrder();
		OrderStatus orderStatus = OrderStatus.ORDERED;
		order.setOrderStatus(orderStatus);
		if (customer == null)
			customer = this.initEmptyCustomer(store);
		PersistableCustomer persistableCustomer = persistableCustomer(customer, store, language);
		order.setCustomer(persistableCustomer);
		var items = new ArrayList<ShoppingCartItem>(shoppingCart.getLineItems());
		order.setShoppingCartItems(items);
		return order;
	}

	private PersistableCustomer persistableCustomer(Customer customer, MerchantStore store, Language language) throws Exception {
		PersistableCustomerPopulator customerPopulator = new PersistableCustomerPopulator();
		PersistableCustomer persistableCustomer = customerPopulator.populate(customer, new PersistableCustomer(), store, language);
		return persistableCustomer;
	}

	@Override
	public void refreshOrder(ShopOrder order, MerchantStore store, Customer customer, ShoppingCart shoppingCart,
			Language language) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderTotalSummary calculateOrderTotal(MerchantStore store, ShopOrder order, Language language)
			throws Exception {
		Customer customer = customerFacade.getCustomerModel(order.getCustomer(), store, language);
		OrderTotalSummary summary = this.calculateOrderTotal(store, customer, order, language);
		this.setOrderTotals(order, summary);
		return summary;
	}

	private void setOrderTotals(OrderEntity order, OrderTotalSummary summary) {
		List<OrderTotal> totals = new ArrayList<>();
		var orderTotals = summary.getTotals();
		orderTotals.stream()
			.forEach(t -> {
				OrderTotal total = new OrderTotal();
				total.setCode(t.getOrderTotalCode());
				total.setTitle(t.getTitle());
				total.setValue(t.getValue());
				totals.add(total);
			});
		order.setTotals(totals);
		
	}

	private OrderTotalSummary calculateOrderTotal(MerchantStore store, Customer customer, PersistableOrder order,
			Language language) throws Exception {
		OrderTotalSummary orderTotalSummary = null;
		OrderSummary summary = new OrderSummary();
		if (order instanceof ShopOrder) {
			ShopOrder o = (ShopOrder) order;
			summary.setProducts(o.getShoppingCartItems());
			if (o.getShippingSummary() != null) {
				summary.setShippingSummary(o.getShippingSummary());
			}
			orderTotalSummary = orderService.calculateOrderTotal(summary, customer, store, language);
		} else {
			throw new Exception("calculateOrderTotal not yet implemented for PersistableOrder");
		}
		
		return orderTotalSummary;
	}

	@Override
	public OrderTotalSummary calculateOrderTotal(MerchantStore store, PersistableOrder order, Language language)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order processOrder(ShopOrder order, Customer customer, MerchantStore store, Language language)
			throws ServiceException {
		
		return this.processOrderModel(order, customer, null, store, language);
	}

	@Override
	public Order processOrder(ShopOrder order, Customer customer, Transaction transaction, MerchantStore store,
			Language language) throws ServiceException {

		return this.processOrderModel(order, customer, transaction, store, language);
	}

	private Order processOrderModel(ShopOrder order, Customer customer, Transaction transaction, MerchantStore store,
			Language language) throws ServiceException {
		try {
			if (order.isShipToBillingAdress()) {
				PersistableCustomer orderCustomer = order.getCustomer();
				
				Address billing = orderCustomer.getBilling();
				orderCustomer.setDelivery(billing);
			}
			
			Order modelOrder = new Order();
			modelOrder.setDatePurchased(LocalDate.now());
			modelOrder.setBilling(customer.getBilling());
			modelOrder.setDelivery(customer.getDelivery());
			modelOrder.setPaymentModuleCode(order.getPaymentModule());
			modelOrder.setPaymentType(PaymentType.valueOf(order.getPaymentMethodType()));
			modelOrder.setShippingModuleCode(order.getShippingModule());
			modelOrder.setLocale(LocaleUtils.getLocale(store));
			
			List<ShoppingCartItem> shoppingCartItems = order.getShoppingCartItems();
			Set<OrderProduct> orderProducts = new LinkedHashSet<>();
			
			OrderProductPopulator orderProductPopulator = new OrderProductPopulator();
			orderProductPopulator.setDigitalProductService(digitalProductService);
			orderProductPopulator.setProductAttributeService(productAttributeService);
			orderProductPopulator.setProductService(productService);
			
			for(ShoppingCartItem item : shoppingCartItems) {
				OrderProduct orderProduct = new OrderProduct();
				orderProduct = orderProductPopulator.populate(item, orderProduct , store, language);
				orderProduct.setOrder(modelOrder);
				orderProducts.add(orderProduct);
			}
			
			modelOrder.setOrderProducts(orderProducts);
			OrderTotalSummary summary = order.getOrderTotalSummary();
			List<org.orakzai.lab.shop.domain.business.order.model.OrderTotal> totals = summary.getTotals();
			
			Collections.sort(totals, (x, y) -> Integer.compare(x.getSortOrder(), y.getSortOrder()));
			
			var modalTotals = totals.stream()
					.map((total) -> {
						total.setOrder(modelOrder);
						return total;
					})
					.collect(Collectors.toSet());
			
			modelOrder.setOrderTotal(modalTotals);
			modelOrder.setTotal(order.getOrderTotalSummary().getTotal());
			modelOrder.setCurrency(store.getCurrency());
			modelOrder.setMerchant(store);
			
			orderCustomer(customer, modelOrder, language);
			if(!StringUtils.isBlank(order.getShippingModule())) {
				modelOrder.setShippingModuleCode(order.getShippingModule());
			}
			
			String paymentType = order.getPaymentMethodType();
			Payment payment = new Payment();
			payment.setPaymentType(PaymentType.valueOf(paymentType));
			
			if (PaymentType.CREDITCARD.name().equals(paymentType)) {
				
				payment = new CreditCardPayment();
				((CreditCardPayment)payment).setCardOwner(order.getPayment().get("creditcard_card_holder"));
				((CreditCardPayment)payment).setCredidCardValidationNumber(order.getPayment().get("creditcard_card_cvv"));
				((CreditCardPayment)payment).setCreditCardNumber(order.getPayment().get("creditcard_card_number"));
				((CreditCardPayment)payment).setExpirationMonth(order.getPayment().get("creditcard_card_expirationmonth"));
				((CreditCardPayment)payment).setExpirationYear(order.getPayment().get("creditcard_card_expirationyear"));
				
				
				CreditCardType creditCardType =null;
				String cardType = order.getPayment().get("creditcard_card_type");
				
				if(cardType.equalsIgnoreCase(CreditCardType.AMEX.name())) {
					creditCardType = CreditCardType.AMEX;
				} else if(cardType.equalsIgnoreCase(CreditCardType.VISA.name())) {
					creditCardType = CreditCardType.VISA;
				} else if(cardType.equalsIgnoreCase(CreditCardType.MASTERCARD.name())) {
					creditCardType = CreditCardType.MASTERCARD;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DINERS.name())) {
					creditCardType = CreditCardType.DINERS;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DISCOVERY.name())) {
					creditCardType = CreditCardType.DISCOVERY;
				}
				
				((CreditCardPayment)payment).setCreditCard(creditCardType);
				
				CreditCard cc = new CreditCard();
				cc.setCardType(creditCardType);
				cc.setCcCvv(((CreditCardPayment)payment).getCredidCardValidationNumber());
				cc.setCcOwner(((CreditCardPayment)payment).getCardOwner());
				cc.setCcExpires(((CreditCardPayment)payment).getExpirationMonth() + "-" + ((CreditCardPayment)payment).getExpirationYear());
			
				//hash credit card number
				String maskedNumber = CreditCardUtils.maskCardNumber(order.getPayment().get("creditcard_card_number"));
				cc.setCcNumber(maskedNumber);
				modelOrder.setCreditCard(cc);
			}
			
			if(PaymentType.PAYPAL.name().equals(paymentType)) {
				//check for previous transaction
				if(transaction==null) {
					throw new ServiceException("payment.error");
				}
				
				payment = new PaypalPayment();
				
				((PaypalPayment)payment).setPayerId(transaction.getTransactionDetails().get("PAYERID"));
				((PaypalPayment)payment).setPaymentToken(transaction.getTransactionDetails().get("TOKEN"));
					
			}
			
			modelOrder.setPaymentModuleCode(order.getPaymentModule());
			payment.setModuleName(order.getPaymentModule());

			if(transaction!=null) {
				orderService.processOrder(modelOrder, customer, order.getShoppingCartItems(), summary, payment, store);
			} else {
				orderService.processOrder(modelOrder, customer, order.getShoppingCartItems(), summary, payment, transaction, store);
			}
			
			return modelOrder;
			
			
		} catch (ServiceException se) {
			throw se;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private void orderCustomer(Customer customer, Order order, Language language) throws Exception{
		order.setBilling(customer.getBilling());
		order.setDelivery(customer.getDelivery());
		order.setCustomerEmailAddress(customer.getEmailAddress());
		order.setCustomerId(customer.getId());
		
	}

	@Override
	public Customer initEmptyCustomer(MerchantStore store) {
		Customer customer = new Customer();
		Billing billing = new Billing();
		billing.setCountry(store.getCountry());
		billing.setZone(store.getZone());
		billing.setState(store.getStorestateprovince());
		billing.setPostalCode(store.getStorepostalcode());
		customer.setBilling(billing);
		
		Delivery delivery = new Delivery();
		delivery.setCountry(store.getCountry());
		delivery.setZone(store.getZone());
		delivery.setState(store.getStorestateprovince());
		delivery.setPostalCode(store.getStorepostalcode());
		customer.setDelivery(delivery);
		return customer;
	}

	@Override
	public List<Country> getShipToCountry(MerchantStore store, Language language) throws Exception {
		
		return shippingService.getShipToCountryList(store, language);
	}

	@Override
	public ShippingQuote getShippingQuote(PersistableCustomer persistableCustomer, ShoppingCart cart, ShopOrder order,
			MerchantStore store, Language language) throws Exception {
		List<ShippingProduct> shippingProducts = shoppingCartService.createShippingProduct(cart);
		if (CollectionUtils.isEmpty(shippingProducts)) {
			return null;
		}
		Customer customer = customerFacade.getCustomerModel(persistableCustomer, store, language);
		Delivery delivery = new Delivery();
		
		if (order.isShipToBillingAdress()) {
			Billing billing = customer.getBilling();
			delivery.setAddress(billing.getAddress());
			delivery.setCompany(billing.getCompany());
			delivery.setPostalCode(billing.getPostalCode());
			delivery.setState(billing.getState());
			delivery.setCountry(billing.getCountry());
			delivery.setZone(billing.getZone());
		} else {
			delivery = customer.getDelivery();
		}
		ShippingQuote quote = shippingService.getShippingQuote(store, delivery, shippingProducts, language);
		
		return quote;
	}

	@Override
	public ShippingQuote getShippingQuote(Customer customer, ShoppingCart cart, PersistableOrder order,
			MerchantStore store, Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingSummary getShippingSummary(ShippingQuote quote, MerchantStore store, Language language) {
		if (quote.getSelectedShippingOption() != null) {
			ShippingSummary summary = new ShippingSummary();
			summary.setFreeShipping(quote.isFreeShipping());
			summary.setTaxOnShipping(quote.isApplyTaxOnShipping());
			summary.setHandling(quote.getHandlingFees());
			summary.setShipping(quote.getSelectedShippingOption().getOptionPrice());
			summary.setShippingOption(quote.getSelectedShippingOption().getOptionName());
			summary.setShippingModule(quote.getShippingModuleCode());
			return summary;
		}
		return null;
	}

	@Override
	public void validateOrder(ShopOrder order, BindingResult bindingResult, Map<String, String> messagesResult,
			MerchantStore store, Locale locale) throws ServiceException {
		Validate.notNull(messagesResult, "messagesResult should not be null");
		try {
			if (StringUtils.isBlank(order.getCustomer().getBilling().getFirstName())) {
				String firstName = "customer.billing.firstName";
				String message = messages.getMessage("NotEmpty.customer.firstName", null, locale);
				FieldError error = new FieldError(firstName, firstName, message);
				bindingResult.addError(error);
				messagesResult.put(firstName, message);
			}
			if (StringUtils.isBlank(order.getCustomer().getBilling().getLastName())) {
				String lastName = "customer.billing.lastName";
				String message = messages.getMessage("NotEmpty.customer.lastName", null, locale);
				FieldError error = new FieldError(lastName, lastName, message);
				bindingResult.addError(error);
				messagesResult.put(lastName, message);
			}
			if (StringUtils.isBlank(order.getCustomer().getEmailAddress())) {
				String email = "customer.emailAddress";
				String message = messages.getMessage("NotEmpty.customer.emailAddress", null, locale);
				FieldError error = new FieldError(email, email, message);
				bindingResult.addError(error);
				messagesResult.put(email, message);
			}
			
			if (StringUtils.isBlank(order.getCustomer().getBilling().getAddress())) {
				String address = "customer.billing.address";
				String message = messages.getMessage("NotEmpty.customer.billing.address", null, locale);
				FieldError error = new FieldError(address, address, message);
				bindingResult.addError(error);
				messagesResult.put(address, message);
			}
			
			if (StringUtils.isBlank(order.getCustomer().getBilling().getCity())) {
				String city = "customer.billing.city";
				String message = messages.getMessage("NotEmpty.customer.billing.city", null, locale);
				FieldError error = new FieldError(city, city, message);
				bindingResult.addError(error);
				messagesResult.put(city, message);
			}
			
			if (StringUtils.isBlank(order.getCustomer().getBilling().getCountry())) {
				String country = "customer.billing.country";
				String message = messages.getMessage("NotEmpty.customer.billing.country", null, locale);
				FieldError error = new FieldError(country, country, message);
				bindingResult.addError(error);
				messagesResult.put(country, message);
			}
			
			if (StringUtils.isBlank(order.getCustomer().getBilling().getZone()) && StringUtils.isBlank(order.getCustomer().getBilling().getStateProvince())) {
				String state = "customer.billing.stateProvince";
				String message = messages.getMessage("NotEmpty.customer.billing.stateProvince", null, locale);
				FieldError error = new FieldError(state, state, message);
				bindingResult.addError(error);
				messagesResult.put(state, message);
			}
			
			if (StringUtils.isBlank(order.getCustomer().getBilling().getPhone())) {
				String phone = "customer.billing.phone";
				String message = messages.getMessage("NotEmpty.customer.billing.phone", null, locale);
				FieldError error = new FieldError(phone, phone, message);
				bindingResult.addError(error);
				messagesResult.put(phone, message);
			}
			
			if (StringUtils.isBlank(order.getCustomer().getBilling().getPostalCode())) {
				String postalCode = "customer.billing.postalCode";
				String message = messages.getMessage("NotEmpty.customer.billing.postalCode", null, locale);
				FieldError error = new FieldError(postalCode, postalCode, message);
				bindingResult.addError(error);
				messagesResult.put(postalCode, message);
			}
			
			if (!order.isShipToBillingAdress()) {
				if (StringUtils.isBlank(order.getCustomer().getDelivery().getFirstName())) {
					String firstName = "customer.delivery.firstName";
					String message = messages.getMessage("NotEmpty.customer.shipping.firstName", null, locale);
					FieldError error = new FieldError(firstName, firstName, message);
					bindingResult.addError(error);
					messagesResult.put(firstName, message);
				}
				if (StringUtils.isBlank(order.getCustomer().getDelivery().getLastName())) {
					String lastName = "customer.delivery.lastName";
					String message = messages.getMessage("NotEmpty.customer.shipping.lastName", null, locale);
					FieldError error = new FieldError(lastName, lastName, message);
					bindingResult.addError(error);
					messagesResult.put(lastName, message);
				}
				
				if (StringUtils.isBlank(order.getCustomer().getDelivery().getAddress())) {
					String address = "customer.delivery.address";
					String message = messages.getMessage("NotEmpty.customer.shipping.address", null, locale);
					FieldError error = new FieldError(address, address, message);
					bindingResult.addError(error);
					messagesResult.put(address, message);
				}
				
				if (StringUtils.isBlank(order.getCustomer().getDelivery().getCity())) {
					String city = "customer.delivery.city";
					String message = messages.getMessage("NotEmpty.customer.shipping.city", null, locale);
					FieldError error = new FieldError(city, city, message);
					bindingResult.addError(error);
					messagesResult.put(city, message);
				}
				
				if (StringUtils.isBlank(order.getCustomer().getDelivery().getCountry())) {
					String country = "customer.delivery.country";
					String message = messages.getMessage("NotEmpty.customer.shipping.country", null, locale);
					FieldError error = new FieldError(country, country, message);
					bindingResult.addError(error);
					messagesResult.put(country, message);
				}
				
				if (StringUtils.isBlank(order.getCustomer().getDelivery().getZone()) && StringUtils.isBlank(order.getCustomer().getDelivery().getStateProvince())) {
					String state = "customer.delivery.stateProvince";
					String message = messages.getMessage("NotEmpty.customer.shipping.stateProvince", null, locale);
					FieldError error = new FieldError(state, state, message);
					bindingResult.addError(error);
					messagesResult.put(state, message);
				}
				
				
				if (StringUtils.isBlank(order.getCustomer().getDelivery().getPostalCode())) {
					String postalCode = "customer.delivery.postalCode";
					String message = messages.getMessage("NotEmpty.customer.shipping.postalCode", null, locale);
					FieldError error = new FieldError(postalCode, postalCode, message);
					bindingResult.addError(error);
					messagesResult.put(postalCode, message);
				}
			}
			
			if (bindingResult.hasErrors()) {
				return;
			}
			
			String paymentType = order.getPaymentMethodType();
			
			if (!shoppingCartService.isFreeShoppingCart(order.getShoppingCartItems()) && paymentType == null) {
				
			}
			
			if (paymentType == null) {
				ServiceException serviceException = new ServiceException(ServiceException.EXCEPTION_VALIDATION, "payment.required");
				throw serviceException;
			}
			
			if (shippingService.requiresShipping(order.getShoppingCartItems(), store) && order.getSelectedShippingOption() == null) {
				ServiceException serviceException = new ServiceException(ServiceException.EXCEPTION_VALIDATION, "shipping.required");
				throw serviceException;
			}
			
			if (PaymentType.CREDITCARD.name().equals(paymentType)) {
				String cco = order.getPayment().get("creditcard_card_holder");
				String cvv = order.getPayment().get("creditcard_card_cvv");
				String ccn = order.getPayment().get("creditcard_card_number");
				String ccm = order.getPayment().get("creditcard_card_expirationmonth");
				String ccd = order.getPayment().get("creditcard_card_expirationyear");
				
				if(StringUtils.isBlank(cco) || StringUtils.isBlank(cvv) || StringUtils.isBlank(ccn) ||
						StringUtils.isBlank(ccm) || StringUtils.isBlank(ccd)) {
						ObjectError error = new ObjectError("creditcard_card_holder",messages.getMessage("NotEmpty.customer.shipping.stateProvince", null, locale));
		            	bindingResult.addError(error);
		            	messagesResult.put("creditcard_card_holder",messages.getMessage("NotEmpty.customer.shipping.stateProvince", null, locale));
		            return;
				}
				
				CreditCardType creditCardType = null;
				String cardType = order.getPayment().get("creditcard_card_type");
				
				if (cardType.equalsIgnoreCase(CreditCardType.AMEX.name())) {
					creditCardType = CreditCardType.AMEX;
				} else if(cardType.equalsIgnoreCase(CreditCardType.VISA.name())) {
					creditCardType = CreditCardType.VISA;
				} else if(cardType.equalsIgnoreCase(CreditCardType.MASTERCARD.name())) {
					creditCardType = CreditCardType.MASTERCARD;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DINERS.name())) {
					creditCardType = CreditCardType.DINERS;
				} else if(cardType.equalsIgnoreCase(CreditCardType.DISCOVERY.name())) {
					creditCardType = CreditCardType.DISCOVERY;
				}
				
				if(creditCardType==null) {
					ServiceException serviceException = new ServiceException(ServiceException.EXCEPTION_VALIDATION,"cc.type");
					throw serviceException;
				}
			}
			
		} catch (ServiceException e) {
			log.error("Error while commiting order", e);
			throw e;
		}
		
	}

	@Override
	public ReadableOrder getReadableOrder(Long orderId, MerchantStore store, Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableOrderList getReadableOrderList(MerchantStore store, Customer customer, int start, int maxCount,
			Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadableOrderList getReadableOrderList(MerchantStore store, int start, int maxCount, Language language)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
