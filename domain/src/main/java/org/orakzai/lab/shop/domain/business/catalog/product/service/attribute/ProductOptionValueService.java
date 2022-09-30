//package org.orakzai.lab.shop.domain.business.catalog.product.service.attribute;
//
//import java.util.List;
//
//import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductOptionValue;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//
//public interface ProductOptionValueService extends SalesManagerEntityService<Long, ProductOptionValue> {
//
//	void saveOrUpdate(ProductOptionValue entity) throws ServiceException;
//
//	List<ProductOptionValue> getByName(MerchantStore store, String name,
//			Language language) throws ServiceException;
//
//	ProductOptionValue getById(MerchantStore store, Long id)
//			throws ServiceException;
//
//	List<ProductOptionValue> listByStore(MerchantStore store, Language language)
//			throws ServiceException;
//
//	List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store,
//			Language language) throws ServiceException;
//
//	ProductOptionValue getByCode(MerchantStore store, String optionValueCode);
//
//}
