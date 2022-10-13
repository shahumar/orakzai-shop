package org.orakzai.lab.shop.domain.business.payments.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.payments.model.CreditCardType;
import org.orakzai.lab.shop.domain.business.payments.model.Payment;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentMethod;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.orakzai.lab.shop.domain.modules.integration.payment.model.PaymentModule;

public interface PaymentService {



	public List<IntegrationModule> getPaymentMethods(MerchantStore store)
			throws ServiceException;

	Map<String, IntegrationConfiguration> getPaymentModulesConfigured(
			MerchantStore store) throws ServiceException;

	Transaction processPayment(Customer customer, MerchantStore store, Payment payment, List<ShoppingCartItem> items, Order order) throws ServiceException;
	Transaction processRefund(Order order, Customer customer, MerchantStore store, BigDecimal amount) throws ServiceException;

	IntegrationModule getPaymentMethodByType(MerchantStore store, String type)
			throws ServiceException;


	IntegrationModule getPaymentMethodByCode(MerchantStore store, String name)
			throws ServiceException;

	void savePaymentModuleConfiguration(IntegrationConfiguration configuration,
			MerchantStore store) throws ServiceException;

	void validateCreditCard(String number, CreditCardType creditCard, String month, String date)
			throws ServiceException;

	IntegrationConfiguration getPaymentConfiguration(String moduleCode,
			MerchantStore store) throws ServiceException;

	void removePaymentModuleConfiguration(String moduleCode, MerchantStore store)
			throws ServiceException;

	Transaction processCapturePayment(Order order, Customer customer,
			MerchantStore store)
			throws ServiceException;

	List<PaymentMethod> getAcceptedPaymentMethods(MerchantStore store)
			throws ServiceException;

	PaymentModule getPaymentModule(String paymentModuleCode)
			throws ServiceException;

}