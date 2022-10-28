package org.orakzai.lab.shop.web.controller.order.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import org.orakzai.lab.shop.domain.business.common.model.Billing;
import org.orakzai.lab.shop.domain.business.common.model.Delivery;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.order.model.OrderSummary;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatus;
import org.orakzai.lab.shop.domain.business.order.service.OrderService;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingQuote;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.web.controller.customer.facade.CustomerFacade;
import org.orakzai.lab.shop.web.dto.customer.PersistableCustomer;
import org.orakzai.lab.shop.web.dto.order.OrderEntity;
import org.orakzai.lab.shop.web.dto.order.OrderTotal;
import org.orakzai.lab.shop.web.dto.order.PersistableOrder;
import org.orakzai.lab.shop.web.dto.order.ReadableOrder;
import org.orakzai.lab.shop.web.dto.order.ReadableOrderList;
import org.orakzai.lab.shop.web.dto.order.ShopOrder;
import org.orakzai.lab.shop.web.mapper.customer.PersistableCustomerPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service("orderFacade")
public class OrderFacadeImpl implements OrderFacade {
	
	@Autowired
	private CustomerFacade customerFacade;
	
	@Autowired
	private OrderService orderService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order processOrder(ShopOrder order, Customer customer, Transaction transaction, MerchantStore store,
			Language language) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingQuote getShippingQuote(PersistableCustomer customer, ShoppingCart cart, ShopOrder order,
			MerchantStore store, Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingQuote getShippingQuote(Customer customer, ShoppingCart cart, PersistableOrder order,
			MerchantStore store, Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingSummary getShippingSummary(ShippingQuote quote, MerchantStore store, Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateOrder(ShopOrder order, BindingResult bindingResult, Map<String, String> messagesResult,
			MerchantStore store, Locale locale) throws ServiceException {
		// TODO Auto-generated method stub
		
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
