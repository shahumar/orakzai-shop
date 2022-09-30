package org.orakzai.lab.shop.domain.business.customer.dao.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionSet;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;

@Repository("customerOptionSetDao")
public interface CustomerOptionSetDao extends SalesManagerEntityDao<Long, CustomerOptionSet> {
//
//	List<CustomerOptionSet> getByOptionId(MerchantStore store, Long id);
//
//	List<CustomerOptionSet> listByStore(MerchantStore store, Language language);
//
//	CustomerOptionSet getById(Long customerOptionSetId);
//
//	List<CustomerOptionSet> getByOptionValueId(MerchantStore store, Long id);







}
