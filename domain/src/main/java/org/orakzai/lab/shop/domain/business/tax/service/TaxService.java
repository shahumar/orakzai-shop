//package org.orakzai.lab.shop.domain.business.tax.service;
//
//import java.util.List;
//import java.util.Locale;
//
//import org.orakzai.lab.shop.domain.business.customer.model.Customer;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.order.model.OrderSummary;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//import org.orakzai.lab.shop.domain.business.tax.model.TaxConfiguration;
//import org.orakzai.lab.shop.domain.business.tax.model.TaxItem;
//
//
//public interface TaxService   {
//
//	/**
//	 * Retrieves tax configurations (TaxConfiguration) for a given MerchantStore
//	 * @param store
//	 * @return
//	 * @throws ServiceException
//	 */
//	TaxConfiguration getTaxConfiguration(MerchantStore store)
//			throws ServiceException;
//
//	/**
//	 * Saves ShippingConfiguration to MerchantConfiguration table
//	 * @param shippingConfiguration
//	 * @param store
//	 * @throws ServiceException
//	 */
//	void saveTaxConfiguration(TaxConfiguration shippingConfiguration,
//			MerchantStore store) throws ServiceException;
//
//	/**
//	 * Calculates tax over an OrderSummary
//	 * @param orderSummary
//	 * @param customer
//	 * @param store
//	 * @param locale
//	 * @return
//	 * @throws ServiceException
//	 */
//	List<TaxItem> calculateTax(OrderSummary orderSummary, Customer customer,
//			MerchantStore store, Language language) throws ServiceException;
//
//
//}
