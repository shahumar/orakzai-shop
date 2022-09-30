package org.orakzai.lab.shop.domain.business.catalog.product.service.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

public interface ProductAttributeService extends
		SalesManagerEntityService<Long, ProductAttribute> {

	void saveOrUpdate(ProductAttribute productAttribute)
			throws ServiceException;

	List<ProductAttribute> getByOptionId(MerchantStore store,
			Long id) throws ServiceException;

	List<ProductAttribute> getByOptionValueId(MerchantStore store,
			Long id) throws ServiceException;

	List<ProductAttribute> getByProductId(MerchantStore store, Product product, Language language)
			throws ServiceException;

	List<ProductAttribute> getByAttributeIds(MerchantStore store, List<Long> ids)
			throws ServiceException;
}
