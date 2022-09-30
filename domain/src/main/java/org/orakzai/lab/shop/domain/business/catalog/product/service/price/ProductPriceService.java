package org.orakzai.lab.shop.domain.business.catalog.product.service.price;

import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPriceDescription;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;

public interface ProductPriceService extends SalesManagerEntityService<Long, ProductPrice> {

	void addDescription(ProductPrice price, ProductPriceDescription description) throws ServiceException;

	void saveOrUpdate(ProductPrice price) throws ServiceException;


}
