package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersistableOrderProduct extends OrderProductEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String productName;
	private String price;
	private String subTotal;
	private String sku;
	private String image;

}
