package org.orakzai.lab.shop.domain.modules.integration.payment.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.payments.model.Payment;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentType;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
import org.orakzai.lab.shop.domain.business.payments.model.TransactionType;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.orakzai.lab.shop.domain.modules.integration.IntegrationException;
import org.orakzai.lab.shop.domain.modules.integration.payment.model.PaymentModule;

public class MoneyOrderPayment implements PaymentModule {

	@Override
	public void validateModuleConfiguration(
			IntegrationConfiguration integrationConfiguration,
			MerchantStore store) throws IntegrationException {
		
		List<String> errorFields = null;
		
		
		Map<String,String> keys = integrationConfiguration.getIntegrationKeys();
		
		//validate integrationKeys['address']
		if(keys==null || StringUtils.isBlank(keys.get("address"))) {
			errorFields = new ArrayList<String>();
			errorFields.add("address");
		}
		
		if(errorFields!=null) {
			IntegrationException ex = new IntegrationException(IntegrationException.ERROR_VALIDATION_SAVE);
			ex.setErrorFields(errorFields);
			throw ex;
			
		}
		
		
		
			return;

	}

	@Override
	public Transaction initTransaction(MerchantStore store, Customer customer,
			BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		//NOT REQUIRED
		return null;
	}

	@Override
	public Transaction authorize(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		//NOT REQUIRED
		return null;
	}

/*	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment, Transaction transaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		//NOT REQUIRED
		return null;
	}*/

	@Override
	public Transaction authorizeAndCapture(MerchantStore store, Customer customer,
			List<ShoppingCartItem> items, BigDecimal amount, Payment payment,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setTransactionDate(LocalDate.now());
		transaction.setTransactionType(TransactionType.AUTHORIZECAPTURE);
		transaction.setPaymentType(PaymentType.MONEYORDER);

		
		return transaction;
		
		
		
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store, Transaction transaction,
			Order order, BigDecimal amount, 
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		throw new IntegrationException("Transaction not supported");
	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer,
			Order order, Transaction capturableTransaction,
			IntegrationConfiguration configuration, IntegrationModule module)
			throws IntegrationException {
		// TODO Auto-generated method stub
		return null;
	}

}
