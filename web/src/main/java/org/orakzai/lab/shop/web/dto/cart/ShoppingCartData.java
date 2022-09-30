package org.orakzai.lab.shop.web.dto.cart;

import java.io.Serializable;
import java.util.List;

import org.orakzai.lab.shop.web.dto.ShopEntity;
import org.orakzai.lab.shop.web.dto.order.OrderTotal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value = "prototype")
public class ShoppingCartData extends ShopEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String message;
	private String code;
	private int quantity;
	private String total;
	private String subTotal;
	
	private List<OrderTotal> totals;
	private List<ShoppingCartItem> shoppingCartItems;

}
