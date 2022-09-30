//package org.orakzai.lab.shop.domain.business.customer.service.attribute;
//
//import java.util.List;
//
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOption;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionSet;
//import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionValue;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//
//public interface CustomerOptionSetService extends SalesManagerEntityService<Long, CustomerOptionSet> {
//
//
//
//	void saveOrUpdate(CustomerOptionSet entity) throws ServiceException;
//
//
//
//
//	List<CustomerOptionSet> listByStore(MerchantStore store,
//			Language language) throws ServiceException;
//
//
//
//
//	List<CustomerOptionSet> listByOption(CustomerOption option,
//			MerchantStore store) throws ServiceException;
//
//
//	List<CustomerOptionSet> listByOptionValue(CustomerOptionValue optionValue,
//			MerchantStore store) throws ServiceException;
//
//}
