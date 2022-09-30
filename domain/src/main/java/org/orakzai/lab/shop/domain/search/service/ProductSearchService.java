package org.orakzai.lab.shop.domain.search.service;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

public interface ProductSearchService {
	
	public void index(MerchantStore store, Product product) throws ServiceException;

}
