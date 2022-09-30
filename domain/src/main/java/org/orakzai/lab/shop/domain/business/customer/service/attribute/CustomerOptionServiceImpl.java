//package org.orakzai.lab.shop.domain.business.customer.service.attribute;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import org.orakzai.lab.shop.domain.business.customer.dao.attribute.CustomerOptionDao;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOption;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionSet;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//
//@Service("customerOptionService")
//public abstract class CustomerOptionServiceImpl extends
//		SalesManagerEntityServiceImpl<Long, CustomerOption> implements CustomerOptionService {
//
//
//	private CustomerOptionDao customerOptionDao;
//
//	@Autowired
//	private CustomerAttributeService customerAttributeService;
//
//	@Autowired
//	private CustomerOptionSetService customerOptionSetService;
//
//
//	@Autowired
//	public CustomerOptionServiceImpl(
//			CustomerOptionDao customerOptionDao) {
//			super(customerOptionDao);
//			this.customerOptionDao = customerOptionDao;
//	}
//
//	@Override
//	public List<CustomerOption> listByStore(MerchantStore store, Language language) throws ServiceException {
//
//		return customerOptionDao.listByStore(store, language);
//
//	}
//
//
//	@Override
//	public void saveOrUpdate(CustomerOption entity) throws ServiceException {
//
//
//		//save or update (persist and attach entities
//		if(entity.getId()!=null && entity.getId()>0) {
//			super.update(entity);
//		} else {
//			customerOptionDao.save(entity);
//		}
//
//	}
//
//
//	@Override
//	public void delete(CustomerOption customerOption) throws ServiceException {
//
//		//remove all attributes having this option
//		List<CustomerAttribute> attributes = customerAttributeService.getByOptionId(customerOption.getMerchantStore(), customerOption.getId());
//
//		for(CustomerAttribute attribute : attributes) {
//			customerAttributeService.delete(attribute);
//		}
//
//		CustomerOption option = this.getById(customerOption.getId());
//		try{
//			List<CustomerOptionSet> optionSets = customerOptionSetService.listByOption(customerOption, customerOption.getMerchantStore());
//
//			for(CustomerOptionSet optionSet : optionSets) {
//				customerOptionSetService.delete(optionSet);
//			}
//		} catch (ServiceException e) {}
//
//
//		//remove option
//		customerOptionDao.delete(option);
//
//	}
//
//	@Override
//	public CustomerOption getByCode(MerchantStore store, String optionCode) {
//		return customerOptionDao.getByCode(store, optionCode);
//	}
//
//
//
//
//
//
//
//}
