package org.orakzai.lab.shop.domain.business.catalog.product.service.image;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.ProductImageSize;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.content.model.ImageContentFile;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;


public interface ProductImageService extends SalesManagerEntityService<Long, ProductImage> {


	void addProductImage(Product product, ProductImage productImage, ImageContentFile inputImage)
			throws ServiceException;

	OutputContentFile getProductImage(ProductImage productImage, ProductImageSize size)
			throws ServiceException;

	List<OutputContentFile> getProductImages(Product product)
			throws ServiceException;

	void removeProductImage(ProductImage productImage) throws ServiceException;

	void saveOrUpdate(ProductImage productImage) throws ServiceException;

	OutputContentFile getProductImage(String storeCode, String productCode,
			String fileName, final ProductImageSize size) throws ServiceException;

	void addProductImages(Product product, List<ProductImage> productImages)
			throws ServiceException;
	
	void deleteProductImages(Product product) throws ServiceException;

}
