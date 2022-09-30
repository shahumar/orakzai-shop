package org.orakzai.lab.shop.domain.modules.cms.product;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.ProductImageSize;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.modules.cms.common.ImageGet;

public interface ProductImageGet extends ImageGet{
	
	/**
	 * Used for accessing the path directly
	 * @param merchantStoreCode
	 * @param product
	 * @param imageName
	 * @return
	 * @throws ServiceException
	 */
	public OutputContentFile getProductImage(final String merchantStoreCode, final String productCode, final String imageName) throws ServiceException;
	public OutputContentFile getProductImage(final String merchantStoreCode, final String productCode, final String imageName, final ProductImageSize size) throws ServiceException;
	public OutputContentFile getProductImage(ProductImage productImage) throws ServiceException;
	public List<OutputContentFile> getImages(Product product) throws ServiceException;


}
