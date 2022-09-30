package org.orakzai.lab.shop.domain.business.customer.service;


import java.util.List;

import org.orakzai.lab.shop.domain.business.common.model.Address;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerCriteria;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerList;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

public interface CustomerService  extends SalesManagerEntityService<Long, Customer> {

	public List<Customer> getByName(String firstName);

	List<Customer> listByStore(MerchantStore store);

	Customer getByNick(String nick);
	void saveOrUpdate(Customer customer) throws ServiceException ;

	CustomerList listByStore(MerchantStore store, CustomerCriteria criteria);

	Customer getByNickAndMerchantStore(String nick, MerchantStore store);

	
	Address getCustomerAddress(MerchantStore store, String ipAddress)
			throws ServiceException;


}
