package org.orakzai.lab.shop.web.mapper.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.DigitalProduct;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.FinalPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.attribute.ProductAttributeService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.file.DigitalProductService;
import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductAttribute;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductDownload;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductPrice;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartAttributeItem;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.utils.AbstractDataPopulator;
import org.orakzai.lab.shop.web.constants.ApplicationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductPopulator extends AbstractDataPopulator<ShoppingCartItem, OrderProduct>{
	
	private ProductService productService;
	private DigitalProductService digitalProductService;
	private ProductAttributeService productAttributeService;
	
	@Override
	protected OrderProduct createTarget() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public OrderProduct populate(ShoppingCartItem source, OrderProduct target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(productService,"productService must be set");
		Validate.notNull(digitalProductService,"digitalProductService must be set");
		Validate.notNull(productAttributeService,"productAttributeService must be set");
		
		try {
			Product modelProduct = productService.getById(source.getId());
			if (modelProduct == null) {
				throw new ConversionException("Cannot get product with id (productId) " + source.getProductId());
			}
			if (modelProduct.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				throw new ConversionException("Invalid product id " + source.getProductId());
			}
			
			DigitalProduct digitalProduct = digitalProductService.getByProduct(store, modelProduct);
			if (digitalProduct != null) {
				OrderProductDownload orderProductDownload = new OrderProductDownload();
				orderProductDownload.setOrderProductFilename(digitalProduct.getProductFileName());
				orderProductDownload.setOrderProduct(target);
				orderProductDownload.setDownloadCount(0);
				orderProductDownload.setMaxdays(ApplicationConstants.MAX_DOWNLOAD_DAYS);
				target.getDownloads().add(orderProductDownload);
			}
			
			target.setOneTimeCharge(source.getItemPrice());	
			target.setProductName(source.getProduct().getDescriptions().iterator().next().getName());
			target.setProductQuantity(source.getQuantity());
			target.setSku(source.getProduct().getSku());
			
			FinalPrice finalPrice = source.getFinalPrice();
			
			if(finalPrice==null) {
				throw new ConversionException("Object final price not populated in shoppingCartItem (source)");
			}
			//Default price
			OrderProductPrice orderProductPrice = orderProductPrice(finalPrice);
			orderProductPrice.setOrderProduct(target);
			
			Set<OrderProductPrice> prices = new HashSet<>();
			prices.add(orderProductPrice);

			//Other prices
			List<FinalPrice> otherPrices = finalPrice.getAdditionalPrices();
			if(otherPrices!=null) {
				for(FinalPrice otherPrice : otherPrices) {
					OrderProductPrice other = orderProductPrice(otherPrice);
					other.setOrderProduct(target);
					prices.add(other);
				}
			}
			
			target.setPrices(prices);
			
			Set<ShoppingCartAttributeItem> attributeItems = source.getAttributes();
			if(!CollectionUtils.isEmpty(attributeItems)) {
				Set<OrderProductAttribute> attributes = new HashSet<OrderProductAttribute>();
				for(ShoppingCartAttributeItem attribute : attributeItems) {
					OrderProductAttribute orderProductAttribute = new OrderProductAttribute();
					orderProductAttribute.setOrderProduct(target);
					Long id = attribute.getProductAttributeId();
					ProductAttribute attr = productAttributeService.getById(id);
					if(attr==null) {
						throw new ConversionException("Attribute id " + id + " does not exists");
					}
					
					if(attr.getProduct().getMerchantStore().getId().intValue()!=store.getId().intValue()) {
						throw new ConversionException("Attribute id " + id + " invalid for this store");
					}
					
					orderProductAttribute.setProductAttributeIsFree(attr.isProductAttributeIsFree());
					orderProductAttribute.setProductAttributeName(attr.getProductOption().getDescriptionsSettoList().get(0).getName());
					orderProductAttribute.setProductAttributeValueName(attr.getProductOptionValue().getDescriptionsSettoList().get(0).getName());
					orderProductAttribute.setProductAttributePrice(attr.getProductAttributePrice());
					orderProductAttribute.setProductAttributeWeight(attr.getProductAttributeWeight());
					orderProductAttribute.setProductOptionId(attr.getProductOption().getId());
					orderProductAttribute.setProductOptionValueId(attr.getProductOptionValue().getId());
					attributes.add(orderProductAttribute);
				}
				target.setOrderAttributes(attributes);
			}

			
			
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		
		return target;
	}

	private OrderProductPrice orderProductPrice(FinalPrice price) {
		OrderProductPrice orderProductPrice = new OrderProductPrice();
		
		ProductPrice productPrice = price.getProductPrice();
		
		orderProductPrice.setDefaultPrice(productPrice.isDefaultPrice());

		orderProductPrice.setProductPrice(price.getFinalPrice());
		orderProductPrice.setProductPriceCode(productPrice.getCode());
		if(productPrice.getDescriptions()!=null && productPrice.getDescriptions().size()>0) {
			orderProductPrice.setProductPriceName(productPrice.getDescriptions().iterator().next().getName());
		}
		if(price.isDiscounted()) {
			orderProductPrice.setProductPriceSpecial(productPrice.getProductPriceSpecialAmount());
			orderProductPrice.setProductPriceSpecialStartDate(productPrice.getProductPriceSpecialStartDate());
			orderProductPrice.setProductPriceSpecialEndDate(productPrice.getProductPriceSpecialEndDate());
		}
		
		return orderProductPrice;
	}

}
