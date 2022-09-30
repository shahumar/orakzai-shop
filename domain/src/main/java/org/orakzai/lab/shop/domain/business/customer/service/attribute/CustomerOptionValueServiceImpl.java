//package org.orakzai.lab.shop.domain.business.customer.service.attribute;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import org.orakzai.lab.shop.domain.business.customer.dao.attribute.CustomerOptionValueDao;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionSet;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionValue;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//
//@Service("customerOptionValueService")
//public abstract class CustomerOptionValueServiceImpl extends
//		SalesManagerEntityServiceImpl<Long, CustomerOptionValue> implements
//		CustomerOptionValueService {
//
//	@Autowired
//	private CustomerAttributeService customerAttributeService;
//
//	private CustomerOptionValueDao customerOptionValueDao;
//
//	@Autowired
//	private CustomerOptionSetService customerOptionSetService;
//
//	@Autowired
//	public CustomerOptionValueServiceImpl(
//			CustomerOptionValueDao customerOptionValueDao) {
//			super(customerOptionValueDao);
//			this.customerOptionValueDao = customerOptionValueDao;
//	}
//
//
//	@Override
//	public List<CustomerOptionValue> listByStore(MerchantStore store, Language language) throws ServiceException {
//
//		return customerOptionValueDao.listByStore(store, language);
//	}
//
//
//
//
//	@Override
//	public void saveOrUpdate(CustomerOptionValue entity) throws ServiceException {
//
//
//		//save or update (persist and attach entities
//		if(entity.getId()!=null && entity.getId()>0) {
//
//			super.update(entity);
//
//		} else {
//
//			customerOptionValueDao.save(entity);
//
//		}
//
//	}
//
//
//	public void delete(CustomerOptionValue customerOptionValue) throws ServiceException  {
//
//		//remove all attributes having this option
//		List<CustomerAttribute> attributes = customerAttributeService.getByCustomerOptionValueId(customerOptionValue.getMerchantStore(), customerOptionValue.getId());
//
//		for(CustomerAttribute attribute : attributes) {
//			customerAttributeService.delete(attribute);
//		}
//		try{
//
//			List<CustomerOptionSet> optionSets = customerOptionSetService.listByOptionValue(customerOptionValue, customerOptionValue.getMerchantStore());
//
//			for(CustomerOptionSet optionSet : optionSets) {
//				customerOptionSetService.delete(optionSet);
//			}
//		} catch (ServiceException ex) {}
//
//		CustomerOptionValue option = super.getById(customerOptionValue.getId());
//
//		//remove option
//		customerOptionValueDao.delete(option);
//
//	}
//
//	@Override
//	public CustomerOptionValue getByCode(MerchantStore store, String optionValueCode) {
//		return customerOptionValueDao.getByCode(store, optionValueCode);
//	}
//
//
//
//}
