package org.orakzai.lab.shop.domain.business.customer.service.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOption;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

public interface CustomerOptionService extends SalesManagerEntityService<Long, CustomerOption> {

	List<CustomerOption> listByStore(MerchantStore store, Language language)
			throws ServiceException;

	void saveOrUpdate(CustomerOption entity) throws ServiceException;

	CustomerOption getByCode(MerchantStore store, String optionCode);


}
