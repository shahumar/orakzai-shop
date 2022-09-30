package org.orakzai.lab.shop.domain.business.catalog.product.service.type;

import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;

public interface ProductTypeService extends SalesManagerEntityService<Long, ProductType> {

	ProductType getProductType(String productTypeCode) throws ServiceException;

}
