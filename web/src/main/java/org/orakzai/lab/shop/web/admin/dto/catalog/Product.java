package org.orakzai.lab.shop.web.admin.dto.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product implements Serializable {
	
	private static final long serialVersionUID = -4531526676134574984L;
	
	@Valid
	private org.orakzai.lab.shop.domain.business.catalog.product.model.Product product;
	
	@Valid
	private List<ProductDescription> descriptions = new ArrayList<>();
	
	@Valid
	private ProductAvailability availability = null;
	
	@Valid
	private ProductPrice price = null;
	
	private MultipartFile image = null;
	
	private ProductImage productImage = null;
	
	@NotEmpty
	private String productPrice = "0";
	
	private String dateAvailable;
	
	private ProductDescription description = null;
		

}
