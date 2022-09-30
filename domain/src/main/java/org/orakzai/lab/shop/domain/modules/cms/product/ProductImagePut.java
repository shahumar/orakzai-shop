package org.orakzai.lab.shop.domain.modules.cms.product;

import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.content.model.ImageContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;


public interface ProductImagePut {
	
	
	public void addProductImage(ProductImage productImage, ImageContentFile contentImage) throws ServiceException;


}
