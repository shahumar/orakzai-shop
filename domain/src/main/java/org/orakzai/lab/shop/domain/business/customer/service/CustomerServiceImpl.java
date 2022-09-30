package org.orakzai.lab.shop.domain.business.customer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.common.model.Address;
import org.orakzai.lab.shop.domain.business.customer.dao.CustomerRepository;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerCriteria;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerList;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
//import org.orakzai.lab.shop.domain.business.customer.service.attribute.CustomerAttributeService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.modules.utils.GeoLocation;

@Service("customerService")
public class CustomerServiceImpl extends SalesManagerEntityServiceImpl<Long, Customer> implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private CustomerRepository customerRepository;

//	@Autowired
//	private CustomerAttributeService customerAttributeService;

//	@Autowired
//	private GeoLocation geoLocation;


	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super(customerRepository);
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getByName(String firstName) {
//		return customerDAO.getByName(firstName);
		return null;
	}

	@Override
	public Customer getById(Long id) {
			return customerRepository.findById(id).get();
	}

	@Override
	public Customer getByNick(String nick) {
		return customerRepository.findByNick(nick).get();
	}

	@Override
	public Customer getByNickAndMerchantStore(String nick, MerchantStore store) {
		return customerRepository.findByNickAndMerchantStore(nick, store).get();
	}

	@Override
	public List<Customer> listByStore(MerchantStore store) {
//		return customerDAO.listByStore(store);
		return null;
	}

	@Override
	public CustomerList listByStore(MerchantStore store, CustomerCriteria criteria) {
//		return customerDAO.listByStore(store,criteria);
		return null;
	}

	@Override
	public Address getCustomerAddress(MerchantStore store, String ipAddress) throws ServiceException {

		try {
//			return geoLocation.getAddress(ipAddress);
			return null;
		} catch(Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void saveOrUpdate(Customer customer) throws ServiceException {

		LOGGER.debug("Creating Customer");

		if(customer.getId()!=null && customer.getId()>0) {
			super.update(customer);
		} else {

			super.create(customer);

		}
	}

	public void delete(Customer customer) throws ServiceException {
		customer = getById(customer.getId());

		//delete attributes
//		List<CustomerAttribute> attributes =customerAttributeService.getByCustomer(customer.getMerchantStore(), customer);
//		if(attributes!=null) {
//			for(CustomerAttribute attribute : attributes) {
//				customerAttributeService.delete(attribute);
//			}
//		}
//		customerDAO.delete(customer);
		return;

	}


}
