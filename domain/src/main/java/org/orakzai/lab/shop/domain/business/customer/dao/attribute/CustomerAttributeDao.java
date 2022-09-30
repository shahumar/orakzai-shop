package org.orakzai.lab.shop.domain.business.customer.dao.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.springframework.stereotype.Repository;

@Repository("customerAttributeDao")
public interface CustomerAttributeDao extends SalesManagerEntityDao<Long, CustomerAttribute> {

//	CustomerAttribute getByOptionId(MerchantStore store, Long customerId, Long id);
//
//	List<CustomerAttribute> getByOptionValueId(MerchantStore store, Long id);
//
//	List<CustomerAttribute> getByOptionId(MerchantStore store, Long id);
//
//	List<CustomerAttribute> getByCustomerId(MerchantStore store, Long customerId);



}
