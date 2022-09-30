package org.orakzai.lab.shop.domain.business.customer.dao;

import java.util.List;
import java.util.Optional;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerCriteria;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerList;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.springframework.stereotype.Repository;


@Repository("customerRepository")
public interface CustomerRepository extends SalesManagerEntityDao<Long, Customer> {
	
//	public List<Customer> getByName(String name);
//
//	List<Customer> listByStore(MerchantStore store);
//
	Optional<Customer> findByNick(String nick);
	Optional<Customer> findByNickAndMerchantStore(String nick, MerchantStore store);
//
//	CustomerList listByStore(MerchantStore store, CustomerCriteria criteria);
//
//	Customer getByNick(String nick, int storeId);
	
	
}
