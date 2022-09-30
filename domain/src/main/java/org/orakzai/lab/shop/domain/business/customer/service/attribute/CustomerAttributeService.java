//package org.orakzai.lab.shop.domain.business.customer.service.attribute;
//
//import java.util.List;
//
//import org.orakzai.lab.shop.domain.business.customer.model.Customer;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//
//public interface CustomerAttributeService extends
//		SalesManagerEntityService<Long, CustomerAttribute> {
//
//	void saveOrUpdate(CustomerAttribute customerAttribute)
//			throws ServiceException;
//
//	CustomerAttribute getByCustomerOptionId(MerchantStore store,
//			Long customerId, Long id);
//
//	List<CustomerAttribute> getByCustomerOptionValueId(MerchantStore store,
//			Long id);
//
//	List<CustomerAttribute> getByOptionId(MerchantStore store, Long id);
//
//
//	List<CustomerAttribute> getByCustomer(MerchantStore store, Customer customer);
//
//
//}
