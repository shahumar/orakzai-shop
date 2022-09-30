package org.orakzai.lab.shop.domain.business.catalog.product.dao.type;

import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.springframework.stereotype.Repository;


public interface ProductTypeRepository extends SalesManagerEntityDao<Long, ProductType> {
	
	ProductType findByCode(String type);

}
