package org.orakzai.lab.shop.web.dto.catalog.product;

import java.io.Serializable;
import java.util.List;

import org.orakzai.lab.shop.web.dto.catalog.ReadableImage;
import org.orakzai.lab.shop.web.dto.catalog.manufacturer.ReadableManufacturer;
import org.orakzai.lab.shop.web.dto.catalog.product.attribute.ReadableProductAttribute;

import lombok.Data;


@Data
public class ReadableProduct extends ProductEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ProductDescription description;
	private String finalPrice = "0";
	private String originalPrice = null;
	private boolean discounted = false;
	private ReadableImage image;
	private List<ReadableImage> images;
	private ReadableManufacturer manufacturer;
	private List<ReadableProductAttribute> attributes;

}
