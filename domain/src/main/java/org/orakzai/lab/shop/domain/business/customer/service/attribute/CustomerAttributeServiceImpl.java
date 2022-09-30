//package org.orakzai.lab.shop.domain.business.customer.service.attribute;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import org.orakzai.lab.shop.domain.business.customer.dao.attribute.CustomerAttributeDao;
//import org.orakzai.lab.shop.domain.business.customer.model.Customer;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//
//@Service("customerAttributeService")
//public abstract class CustomerAttributeServiceImpl extends
//		SalesManagerEntityServiceImpl<Long, CustomerAttribute> implements CustomerAttributeService {
//
//	private CustomerAttributeDao customerAttributeDao;
//
//	@Autowired
//	public CustomerAttributeServiceImpl(CustomerAttributeDao customerAttributeDao) {
//		super(customerAttributeDao);
//		this.customerAttributeDao = customerAttributeDao;
//	}
//
//
//
//
//
//	@Override
//	public void saveOrUpdate(CustomerAttribute customerAttribute)
//			throws ServiceException {
//		if(customerAttribute.getId()!=null && customerAttribute.getId()>0) {
//			customerAttributeDao.update(customerAttribute);
//		} else {
//			customerAttributeDao.save(customerAttribute);
//		}
//
//	}
//
//
//	@Override
//	public CustomerAttribute getByCustomerOptionId(MerchantStore store, Long customerId, Long id) {
//		return customerAttributeDao.getByOptionId(store, customerId, id);
//	}
//
//
//
//	@Override
//	public List<CustomerAttribute> getByCustomer(MerchantStore store, Customer customer) {
//		return customerAttributeDao.getByCustomerId(store, customer.getId());
//	}
//
//
//	@Override
//	public List<CustomerAttribute> getByCustomerOptionValueId(MerchantStore store,
//			Long id) {
//		return customerAttributeDao.getByOptionValueId(store, id);
//	}
//
//	@Override
//	public List<CustomerAttribute> getByOptionId(MerchantStore store,
//			Long id) {
//		return customerAttributeDao.getByOptionId(store, id);
//	}
//
//}
