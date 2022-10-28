package org.orakzai.lab.shop.web.controller.order.facade;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingQuote;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.web.dto.customer.PersistableCustomer;
import org.orakzai.lab.shop.web.dto.order.PersistableOrder;
import org.orakzai.lab.shop.web.dto.order.ReadableOrder;
import org.orakzai.lab.shop.web.dto.order.ReadableOrderList;
import org.orakzai.lab.shop.web.dto.order.ShopOrder;
import org.springframework.validation.BindingResult;

public interface OrderFacade {

	ShopOrder initializeOrder(MerchantStore store, Customer customer, ShoppingCart shoppingCart, Language language) throws Exception;
	
	void refreshOrder(ShopOrder order, MerchantStore store, Customer customer, ShoppingCart shoppingCart, Language language) throws Exception;
	
	OrderTotalSummary calculateOrderTotal(MerchantStore store, ShopOrder order, Language language) throws Exception;

	OrderTotalSummary calculateOrderTotal(MerchantStore store, PersistableOrder order, Language language) throws Exception;

	Order processOrder(ShopOrder order, Customer customer, MerchantStore store, Language language) throws ServiceException;

	Order processOrder(ShopOrder order, Customer customer, Transaction transaction, MerchantStore store, Language language) throws ServiceException;
	
	Customer initEmptyCustomer(MerchantStore store);
	
	List<Country> getShipToCountry(MerchantStore store, Language language)
			throws Exception;
	
	ShippingQuote getShippingQuote(PersistableCustomer customer, ShoppingCart cart, ShopOrder order,
			MerchantStore store, Language language) throws Exception;
	
	ShippingQuote getShippingQuote(Customer customer, ShoppingCart cart, PersistableOrder order,
			MerchantStore store, Language language) throws Exception;
	
	ShippingSummary getShippingSummary(ShippingQuote quote, MerchantStore store, Language language);
	void validateOrder(ShopOrder order, BindingResult bindingResult,
			Map<String, String> messagesResult, MerchantStore store,
			Locale locale) throws ServiceException;
	
	ReadableOrder getReadableOrder(Long orderId, MerchantStore store, Language language) throws Exception;
    
	ReadableOrderList getReadableOrderList(MerchantStore store, Customer customer, int start,
			int maxCount, Language language) throws Exception;
	
	ReadableOrderList getReadableOrderList(MerchantStore store, int start,
			int maxCount, Language language) throws Exception;

}
