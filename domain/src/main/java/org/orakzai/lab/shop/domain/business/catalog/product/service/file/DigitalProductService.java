package org.orakzai.lab.shop.domain.business.catalog.product.service.file;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.DigitalProduct;
import org.orakzai.lab.shop.domain.business.content.model.InputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;


public interface DigitalProductService extends SalesManagerEntityService<Long, DigitalProduct> {

	void saveOrUpdate(DigitalProduct digitalProduct) throws ServiceException;

	void addProductFile(Product product, DigitalProduct digitalProduct,
			InputContentFile inputFile) throws ServiceException;

	DigitalProduct getByProduct(MerchantStore store, Product product)
			throws ServiceException;


}
