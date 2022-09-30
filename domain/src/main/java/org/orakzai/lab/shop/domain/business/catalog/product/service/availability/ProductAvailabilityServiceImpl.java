package org.orakzai.lab.shop.domain.business.catalog.product.service.availability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.catalog.product.dao.availability.ProductAvailabilityRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;

@Service("productAvailabilityService")
public class ProductAvailabilityServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductAvailability> implements
		ProductAvailabilityService {


	private ProductAvailabilityRepository productAvailabilityRepository;

	@Autowired
	public ProductAvailabilityServiceImpl(ProductAvailabilityRepository productAvailabilityRepository) {
			super(productAvailabilityRepository);
			this.productAvailabilityRepository = productAvailabilityRepository;
	}


	@Override
	public void saveOrUpdate(ProductAvailability availability) throws ServiceException {

		if(availability.getId()!=null && availability.getId()>0) {

			this.update(availability);

		} else {
			this.create(availability);
		}

	}



}
