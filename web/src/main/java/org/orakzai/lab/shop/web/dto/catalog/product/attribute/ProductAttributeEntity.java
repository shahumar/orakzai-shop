package org.orakzai.lab.shop.web.dto.catalog.product.attribute;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductAttributeEntity extends ProductAttribute implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private BigDecimal productAttributePrice;
	private int sortOrder;
	private BigDecimal productAttributeWeight;
	private boolean attributeDefault=false;
	

}
