package org.orakzai.lab.shop.domain.business.catalog.product.dao.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductOptionValue;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;

@Repository("productOptionValueDao")
public interface ProductOptionValueDao extends SalesManagerEntityDao<Long, ProductOptionValue> {
//
//	List<ProductOptionValue> listByStore(MerchantStore store, Language language);
//
//	ProductOptionValue getById(MerchantStore store, Long id);
//
//	List<ProductOptionValue> getByName(MerchantStore store, String name,
//			Language language);
//
//	List<ProductOptionValue> listByStoreNoReadOnly(MerchantStore store,
//			Language language);
//
//	ProductOptionValue getByCode(MerchantStore store, String optionValueCode);

}
