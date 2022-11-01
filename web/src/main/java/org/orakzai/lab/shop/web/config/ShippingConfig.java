package org.orakzai.lab.shop.web.config;

import java.util.HashMap;
import java.util.Map;

import org.orakzai.lab.shop.domain.modules.integration.shipping.impl.CustomWeightBasedShippingQuote;
import org.orakzai.lab.shop.domain.modules.integration.shipping.impl.UPSShippingQuote;
import org.orakzai.lab.shop.domain.modules.integration.shipping.impl.USPSShippingQuote;
import org.orakzai.lab.shop.domain.modules.integration.shipping.model.ShippingQuoteModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShippingConfig {
	
	@Bean
	public ShippingQuoteModule usps() {
		return new USPSShippingQuote();
	}
	
	@Bean
	public ShippingQuoteModule ups() {
		return new UPSShippingQuote();
	}
	
	@Bean
	public ShippingQuoteModule weightBased() {
		return new CustomWeightBasedShippingQuote();
	}
	
//	@Bean
//	public ShippingQuoteModule storePickUp() {
//		return new StorePickupShippingQuote();
//	}
//	
//	@Bean
//	public ShippingQuoteModule priceByDistance() {
//		return new PriceByDistanceShippingQuoteRules();
//	}
//	
//	@Bean
//	public ShippingQuoteModule customQuoteRules() {
//		return new CustomShippingQuoteRules();
//	}
	
	
	@Bean
	public Map<String, ShippingQuoteModule> paymentModules() {
		var modules = new HashMap<String, ShippingQuoteModule>();
		modules.put("usps", usps());
		modules.put("ups", ups());
		modules.put("weightBased", weightBased());
//		modules.put("storePickUp", storePickUp());
//		modules.put("priceByDistance", priceByDistance());
//		modules.put("customQuoteRules", customQuoteRules());
		return modules;
	}
}
