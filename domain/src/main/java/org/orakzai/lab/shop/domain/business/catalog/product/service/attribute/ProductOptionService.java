//package org.orakzai.lab.shop.domain.business.catalog.product.service.attribute;
//
//import java.util.List;
//
//import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductOption;
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//
//public interface ProductOptionService extends SalesManagerEntityService<Long, ProductOption> {
//
//	List<ProductOption> listByStore(MerchantStore store, Language language)
//			throws ServiceException;
//
//
//	List<ProductOption> getByName(MerchantStore store, String name,
//			Language language) throws ServiceException;
//
//	void saveOrUpdate(ProductOption entity) throws ServiceException;
//
//
//	List<ProductOption> listReadOnly(MerchantStore store, Language language)
//			throws ServiceException;
//
//
//	ProductOption getByCode(MerchantStore store, String optionCode);
//
//
//
//
//}
