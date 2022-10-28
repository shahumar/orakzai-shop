package org.orakzai.lab.shop.web.mapper.order;

import org.apache.commons.lang3.Validate;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.utils.AbstractDataPopulator;
import org.orakzai.lab.shop.web.dto.order.ReadableShippingSummary;

import lombok.Data;

@Data
public class ReadableShippingSummaryPopulator extends AbstractDataPopulator<ShippingSummary, ReadableShippingSummary>{
	
	private PricingService pricingService;
	
	@Override
	protected ReadableShippingSummary createTarget() {
		return new ReadableShippingSummary();
	}
	
	@Override
	public ReadableShippingSummary populate(ShippingSummary source, ReadableShippingSummary target, MerchantStore store,
			Language language) throws ConversionException {
		Validate.notNull(pricingService, "PricingService must be set");
		try {
			target.setFreeShipping(source.isFreeShipping());
			target.setHandling(source.getHandling());
			target.setShipping(source.getShipping());
			target.setShippingModule(source.getShippingModule());
			target.setShippingOption(source.getShippingOption());
			target.setTaxOnShipping(source.isTaxOnShipping());
			target.setHandlingText(pricingService.getDisplayAmount(source.getHandling(), store));
			target.setShippingText(pricingService.getDisplayAmount(source.getShipping(), store));
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		return target;
	}

}
