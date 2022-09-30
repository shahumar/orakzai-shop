package org.orakzai.lab.shop.domain.business.catalog.product.service.relationship;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationship;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationshipType;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

public interface ProductRelationshipService extends
		SalesManagerEntityService<Long, ProductRelationship> {

	void saveOrUpdate(ProductRelationship relationship) throws ServiceException;

	List<ProductRelationship> getByType(MerchantStore store, Product product,
			ProductRelationshipType type, Language language) throws ServiceException;


	List<ProductRelationship> getByType(MerchantStore store, Product product,
			ProductRelationshipType type)
			throws ServiceException;

	List<ProductRelationship> getByType(MerchantStore store,
			ProductRelationshipType type) throws ServiceException;

	List<ProductRelationship> listByProduct(Product product)
			throws ServiceException;

	List<ProductRelationship> getByType(MerchantStore store,
			ProductRelationshipType type, Language language)
			throws ServiceException;


	List<ProductRelationship> getGroups(MerchantStore store);

	void addGroup(MerchantStore store, String groupName) throws ServiceException;

	List<ProductRelationship> getByGroup(MerchantStore store, String groupName)
			throws ServiceException;

	void deleteGroup(MerchantStore store, String groupName)
			throws ServiceException;

	void deactivateGroup(MerchantStore store, String groupName)
			throws ServiceException;

	void activateGroup(MerchantStore store, String groupName)
			throws ServiceException;

	List<ProductRelationship> getByGroup(MerchantStore store, String groupName,
			Language language) throws ServiceException;

}
