package org.orakzai.lab.shop.domain.business.customer.dao.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOption;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;

@Repository("customerOptionDao")
public interface CustomerOptionDao extends SalesManagerEntityDao<Long, CustomerOption> {

//	List<CustomerOption> listByStore(MerchantStore store, Language language);
//
//
//
//	void saveOrUpdate(CustomerOption entity) throws ServiceException;
//
//
//	/**
//	 * Get a unique CustomerOption by code
//	 * @param store
//	 * @param optionCode
//	 * @return
//	 */
//	CustomerOption getByCode(MerchantStore store, String optionCode);

}
