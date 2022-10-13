package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingOption;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;

import lombok.Data;

@Data
public class ShopOrder extends PersistableOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<ShoppingCartItem> shoppingCartItems;
	
	private OrderTotalSummary orderTotalSummary;
	
	private ShippingSummary shippingSummary;
	
	private ShippingOption selectedShippingOption = null;
	
	private String paymentMethodType = null;
	
	private Map<String,String> payment;
	
	private String errorMessage = null;

}
