package org.orakzai.lab.shop.domain.business.merchant.service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

public interface MerchantStoreService extends SalesManagerEntityService<Integer, MerchantStore>{


	//Collection<Product> getProducts(MerchantStore merchantStore) throws ServiceException;

	//MerchantStore getMerchantStore(Integer merchantStoreId) throws ServiceException;

	MerchantStore getMerchantStore(String merchantStoreCode)
			throws ServiceException;

	MerchantStore getByCode(String code) throws ServiceException ;

	void saveOrUpdate(MerchantStore store) throws ServiceException;
	
}
