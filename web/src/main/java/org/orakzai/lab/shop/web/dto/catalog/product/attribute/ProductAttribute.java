package org.orakzai.lab.shop.web.dto.catalog.product.attribute;

import java.io.Serializable;

import org.orakzai.lab.shop.web.dto.Entity;

import lombok.Data;

@Data
public class ProductAttribute extends Entity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ProductOption option;
	private ProductOptionValue optionValue;

}
