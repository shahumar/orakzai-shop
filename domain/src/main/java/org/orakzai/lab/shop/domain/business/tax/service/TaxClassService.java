package org.orakzai.lab.shop.domain.business.tax.service;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;

public interface TaxClassService extends SalesManagerEntityService<Long, TaxClass> {

	public List<TaxClass> listByStore(MerchantStore store) throws ServiceException;

	TaxClass getByCode(String code) throws ServiceException;

	TaxClass getByCode(String code, MerchantStore store)
			throws ServiceException;

}
