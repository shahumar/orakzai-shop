package org.orakzai.lab.shop.domain.business.catalog.product.service.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.catalog.product.dao.type.ProductTypeRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType;
//import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType_;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;

@Service("productTypeService")
public class ProductTypeServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductType>
		implements ProductTypeService {

	private ProductTypeRepository productTypeRepository;

	@Autowired
	public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
			super(productTypeRepository);
			this.productTypeRepository = productTypeRepository;
	}

	@Override
	public ProductType getProductType(String productTypeCode) throws ServiceException {
		return productTypeRepository.findByCode(productTypeCode);
		
	}



}
