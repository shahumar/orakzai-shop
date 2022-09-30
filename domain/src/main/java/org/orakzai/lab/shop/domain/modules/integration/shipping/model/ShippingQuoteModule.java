package org.orakzai.lab.shop.domain.modules.integration.shipping.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.orakzai.lab.shop.domain.business.common.model.Delivery;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.shipping.model.PackageDetails;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingConfiguration;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingOption;
import org.orakzai.lab.shop.domain.business.system.model.CustomIntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.orakzai.lab.shop.domain.modules.integration.IntegrationException;

public interface ShippingQuoteModule {
	
	public void validateModuleConfiguration(IntegrationConfiguration integrationConfiguration, MerchantStore store) throws IntegrationException;
	public CustomIntegrationConfiguration getCustomModuleConfiguration(MerchantStore store) throws IntegrationException;
	
	public List<ShippingOption> getShippingQuotes(List<PackageDetails> packages, BigDecimal orderTotal, Delivery delivery, MerchantStore store, IntegrationConfiguration configuration, IntegrationModule module, ShippingConfiguration shippingConfiguration, Locale locale) throws IntegrationException;

}
