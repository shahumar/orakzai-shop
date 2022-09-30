package org.orakzai.lab.shop.web.dto.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.orakzai.lab.shop.web.dto.ShopEntity;

import lombok.Data;

@Data
public class ShoppingCartItem extends ShopEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String price;
	private String image;
	private BigDecimal productPrice;
	private int quantity;
	private long productId;
	private String productCode;
	private String code;//shopping cart code
	private boolean productVirtual;
	
	private String subTotal;
	
	private List<ShoppingCartAttribute> shoppingCartAttributes;

}
