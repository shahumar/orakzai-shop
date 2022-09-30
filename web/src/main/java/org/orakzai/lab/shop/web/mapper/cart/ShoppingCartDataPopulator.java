package org.orakzai.lab.shop.web.mapper.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.OrderSummary;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotal;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartAttributeItem;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartCalculationService;
import org.orakzai.lab.shop.domain.utils.AbstractDataPopulator;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartAttribute;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.utils.ImageFilePathUtils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class ShoppingCartDataPopulator extends AbstractDataPopulator<ShoppingCart, ShoppingCartData>{

	private PricingService pricingService;
	
	private ShoppingCartCalculationService shoppingCartCalculationService;


	@Override
	protected ShoppingCartData createTarget() {
		
		return new ShoppingCartData();
	}
	
	public ShoppingCartCalculationService getOrderService() {
		return shoppingCartCalculationService;
	}
	
	public PricingService getPricingService() {
        return pricingService;
    }

	@Override
	public ShoppingCartData populate(ShoppingCart source, ShoppingCartData cart, MerchantStore store, Language language)
			throws ConversionException {
		int cartQuantity = 0;
		cart.setCode(source.getShoppingCartCode());
		var items = source.getLineItems();
		List<org.orakzai.lab.shop.web.dto.cart.ShoppingCartItem> shoppingCartItemList = Collections.emptyList();
		try {
			if (items != null) {
				shoppingCartItemList = new ArrayList<>();
				for (ShoppingCartItem item : items) {
					org.orakzai.lab.shop.web.dto.cart.ShoppingCartItem cartItem = new org.orakzai.lab.shop.web.dto.cart.ShoppingCartItem();
					cartItem.setCode(cart.getCode());
					cartItem.setProductCode(item.getProduct().getSku());
					cartItem.setProductVirtual(item.isProductVirtual());
					cartItem.setProductId(item.getProductId());
					cartItem.setId(item.getId());
					cartItem.setName(item.getProduct().getProductDescription().getName());
					cartItem.setPrice(pricingService.getDisplayAmount(item.getItemPrice(), store));
					cartItem.setQuantity(item.getQuantity());
					cartQuantity = cartQuantity + item.getQuantity();
					
					cartItem.setProductPrice(item.getItemPrice());
					cartItem.setSubTotal(pricingService.getDisplayAmount(item.getSubTotal(), store));
					ProductImage image = item.getProduct().getProductImage();
					if (image != null) {
						String imagePath = ImageFilePathUtils.buildProductImageFilePath(store, item.getProduct().getSku(), image.getProductImage());
						cartItem.setImage(imagePath);
					}
					var attributes = item.getAttributes();
					if (attributes != null) {
						var cartAttributes = new ArrayList<ShoppingCartAttribute>(); 
						for (ShoppingCartAttributeItem attribute : attributes) {
							ShoppingCartAttribute cartAttribute = new ShoppingCartAttribute();
							cartAttribute.setId(attribute.getId());
							cartAttribute.setAttributeId(attribute.getProductAttributeId());
							cartAttribute.setOptionId(attribute.getProductAttribute().getProductOptionValue().getId());
							var optionDescripions = attribute.getProductAttribute().getProductOption().getDescriptionsSettoList();
							var optionValueDescriptions = attribute.getProductAttribute().getProductOptionValue().getDescriptionsSettoList();
							if(!CollectionUtils.isEmpty(optionDescripions) && !CollectionUtils.isEmpty(optionValueDescriptions)) {
								cartAttribute.setOptionName(optionDescripions.get(0).getName());
								cartAttribute.setOptionValue(optionValueDescriptions.get(0).getName());
								cartAttributes.add(cartAttribute);
							}
						}
						cartItem.setShoppingCartAttributes(cartAttributes);
					}
					shoppingCartItemList.add(cartItem);
					
					
				}
			}
			if (CollectionUtils.isNotEmpty(shoppingCartItemList)) {
				cart.setShoppingCartItems(shoppingCartItemList);
			}
			
			OrderSummary summary = new OrderSummary();
			var productList = new ArrayList<ShoppingCartItem>();
			productList.addAll(source.getLineItems());
			summary.setProducts(productList);
			OrderTotalSummary orderSummary = shoppingCartCalculationService.calculate(source, store, language);
			if (CollectionUtils.isNotEmpty(orderSummary.getTotals())) {
				var totals = new ArrayList<org.orakzai.lab.shop.web.dto.order.OrderTotal>();
				for (OrderTotal oTotal : orderSummary.getTotals()) {
					org.orakzai.lab.shop.web.dto.order.OrderTotal total = new org.orakzai.lab.shop.web.dto.order.OrderTotal();
					total.setCode(oTotal.getOrderTotalCode());
					total.setValue(oTotal.getValue());
					totals.add(total);
				}
				cart.setTotals(totals);
				
			}
			
			cart.setSubTotal(pricingService.getDisplayAmount(orderSummary.getSubTotal(), store));
			cart.setTotal(pricingService.getDisplayAmount(orderSummary.getTotal(), store));
			cart.setQuantity(cartQuantity);
			cart.setId(source.getId());
		} catch (Exception e) {
			log.error("Error while converting cart Model to cart Data...", e);
			throw new ConversionException("Unable to create cart data", e);
		}
		return cart;
	}
	
	
	
	
	
	
}
