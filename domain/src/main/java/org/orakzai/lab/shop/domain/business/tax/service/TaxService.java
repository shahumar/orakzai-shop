package org.orakzai.lab.shop.domain.business.tax.service;

import java.util.List;
import java.util.Locale;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.OrderSummary;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.tax.model.TaxConfiguration;
import org.orakzai.lab.shop.domain.business.tax.model.TaxItem;


public interface TaxService   {

	TaxConfiguration getTaxConfiguration(MerchantStore store)
			throws ServiceException;

	void saveTaxConfiguration(TaxConfiguration shippingConfiguration,
			MerchantStore store) throws ServiceException;

	List<TaxItem> calculateTax(OrderSummary orderSummary, Customer customer,
			MerchantStore store, Language language) throws ServiceException;


}
