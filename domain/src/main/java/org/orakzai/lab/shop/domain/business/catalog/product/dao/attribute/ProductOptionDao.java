package org.orakzai.lab.shop.domain.business.catalog.product.dao.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductOption;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;

@Repository("productOptionDao")
public interface ProductOptionDao extends SalesManagerEntityDao<Long, ProductOption> {

//	List<ProductOption> listByStore(MerchantStore store, Language language);
//
//	List<ProductOption> getByName(MerchantStore store, String name,
//			Language language);
//
//	void saveOrUpdate(ProductOption entity) throws ServiceException;


//	List<ProductOption> getReadOnly(MerchantStore store,
//			Language language);
//
//	ProductOption getByCode(MerchantStore store, String optionCode);

}
