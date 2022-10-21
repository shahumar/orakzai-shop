package org.orakzai.lab.shop.domain.business.customer.dao.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionValue;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;

@Repository("customerOptionValueDao")
public interface CustomerOptionValueRepository extends SalesManagerEntityDao<Long, CustomerOptionValue> {

//	List<CustomerOptionValue> listByStore(MerchantStore store, Language language);
//
//	CustomerOptionValue getByCode(MerchantStore store, String optionValueCode);



}
