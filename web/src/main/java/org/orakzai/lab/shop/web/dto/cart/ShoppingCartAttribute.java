package org.orakzai.lab.shop.web.dto.cart;

import java.io.Serializable;

import org.orakzai.lab.shop.web.dto.ShopEntity;

import lombok.Data;

@Data
public class ShoppingCartAttribute extends ShopEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private long optionId;
	private long optionValueId;
	private long attributeId;
	private String optionName;
	private String optionValue;
}
