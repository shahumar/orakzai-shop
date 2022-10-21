package org.orakzai.lab.shop.domain.business.customer.service.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.customer.dao.attribute.CustomerAttributeRepository;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Service("customerAttributeService")
public class CustomerAttributeServiceImpl extends
		SalesManagerEntityServiceImpl<Long, CustomerAttribute> implements CustomerAttributeService {

	private CustomerAttributeRepository customerAttributeRepository;

	@Autowired
	public CustomerAttributeServiceImpl(CustomerAttributeRepository customerAttributeDao) {
		super(customerAttributeDao);
		this.customerAttributeRepository = customerAttributeDao;
	}





	@Override
	public void saveOrUpdate(CustomerAttribute customerAttribute)
			throws ServiceException {
		if(customerAttribute.getId()!=null && customerAttribute.getId()>0) {
			customerAttributeRepository.save(customerAttribute);
		} else {
			customerAttributeRepository.save(customerAttribute);
		}

	}


	@Override
	public CustomerAttribute getByCustomerOptionId(MerchantStore store, Long customerId, Long id) {
		return null;
//		return customerAttributeRepository.getByOptionId(store, customerId, id);
	}



	@Override
	public List<CustomerAttribute> getByCustomer(MerchantStore store, Customer customer) {
		return null;
//		return customerAttributeRepository.getByCustomerId(store, customer.getId());
	}


	@Override
	public List<CustomerAttribute> getByCustomerOptionValueId(MerchantStore store,
			Long id) {
		return null;
//		return customerAttributeRepository.getByOptionValueId(store, id);
	}

	@Override
	public List<CustomerAttribute> getByOptionId(MerchantStore store,
			Long id) {
		return null;
//		return customerAttributeRepository.getByOptionId(store, id);
	}

}
