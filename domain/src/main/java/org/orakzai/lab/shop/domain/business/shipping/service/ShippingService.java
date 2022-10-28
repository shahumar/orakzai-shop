package org.orakzai.lab.shop.domain.business.shipping.service;

import java.util.List;
import java.util.Map;

import org.orakzai.lab.shop.domain.business.common.model.Delivery;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shipping.model.PackageDetails;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingConfiguration;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingOption;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingProduct;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingQuote;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.business.system.model.CustomIntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;

public interface ShippingService {

	public  List<String> getSupportedCountries(MerchantStore store)
			throws ServiceException;

	public  void setSupportedCountries(MerchantStore store,
			List<String> countryCodes) throws ServiceException;

	public List<IntegrationModule> getShippingMethods(MerchantStore store)
			throws ServiceException;

	Map<String, IntegrationConfiguration> getShippingModulesConfigured(
			MerchantStore store) throws ServiceException;

	void saveShippingQuoteModuleConfiguration(IntegrationConfiguration configuration,
			MerchantStore store) throws ServiceException;

	ShippingConfiguration getShippingConfiguration(MerchantStore store)
			throws ServiceException;

	void saveShippingConfiguration(ShippingConfiguration shippingConfiguration,
			MerchantStore store) throws ServiceException;

	void removeShippingQuoteModuleConfiguration(String moduleCode,
			MerchantStore store) throws ServiceException;

	List<PackageDetails> getPackagesDetails(List<ShippingProduct> products,
			MerchantStore store) throws ServiceException;

	ShippingQuote getShippingQuote(MerchantStore store, Delivery delivery,
			List<ShippingProduct> products, Language language)
			throws ServiceException;

	IntegrationConfiguration getShippingConfiguration(String moduleCode,
			MerchantStore store) throws ServiceException;

	CustomIntegrationConfiguration getCustomShippingConfiguration(
			String moduleCode, MerchantStore store) throws ServiceException;

	void saveCustomShippingConfiguration(String moduleCode,
			CustomIntegrationConfiguration shippingConfiguration,
			MerchantStore store) throws ServiceException;

	void removeCustomShippingQuoteModuleConfiguration(String moduleCode,
			MerchantStore store) throws ServiceException;

	ShippingSummary getShippingSummary(MerchantStore store, ShippingQuote shippingQuote,
			ShippingOption selectedShippingOption) throws ServiceException;

	List<Country> getShipToCountryList(MerchantStore store, Language language)
			throws ServiceException;

	boolean requiresShipping(List<ShoppingCartItem> items, MerchantStore store) throws ServiceException;




}