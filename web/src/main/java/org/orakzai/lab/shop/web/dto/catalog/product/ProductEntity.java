package org.orakzai.lab.shop.web.dto.catalog.product;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;


@Data
public class ProductEntity extends Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private BigDecimal price;
	private int quantity = 0;
	private String sku;
	private boolean productShipeable = false;
	private boolean productVirtual = false;
	private int quantityOrderMaximum =-1;//default unlimited
	private int quantityOrderMinimum = 1;//default 1
	private boolean productIsFree;
	private boolean available;
	private BigDecimal productLength;
	private BigDecimal productWidth;
	private BigDecimal productHeight;
	private BigDecimal productWeight;
	private Double rating = 0D;
	private int ratingCount;
	private int sortOrder;
	private String dateAvailable;

}
