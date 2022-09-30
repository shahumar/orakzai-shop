package org.orakzai.lab.shop.domain.business.catalog.product.service.availability;

import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;

public interface ProductAvailabilityService extends
		SalesManagerEntityService<Long, ProductAvailability> {

	void saveOrUpdate(ProductAvailability availability) throws ServiceException;

}
