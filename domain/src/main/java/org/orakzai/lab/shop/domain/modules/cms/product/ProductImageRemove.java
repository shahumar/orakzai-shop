package org.orakzai.lab.shop.domain.modules.cms.product;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.modules.cms.common.ImageRemove;


public interface ProductImageRemove extends ImageRemove {
	
	
	public void removeProductImage(ProductImage productImage) throws ServiceException;
	public void removeProductImages(Product product) throws ServiceException;
	


}
