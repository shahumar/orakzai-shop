package org.orakzai.lab.shop.domain.business.catalog.product.service.relationship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.orakzai.lab.shop.domain.business.catalog.product.dao.relationship.ProductRelationshipRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationship;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationshipType;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

@Service("productRelationshipService")
public class ProductRelationshipServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductRelationship> implements
		ProductRelationshipService {


	private ProductRelationshipRepository productRelationshipRepository;

	@Autowired
	public ProductRelationshipServiceImpl(
			ProductRelationshipRepository productRelationshipRepository) {
			super(productRelationshipRepository);
			this.productRelationshipRepository = productRelationshipRepository;
	}

	@Override
	public void saveOrUpdate(ProductRelationship relationship) throws ServiceException {

		if(relationship.getId()!=null && relationship.getId()>0) {

			this.update(relationship);

		} else {
			this.create(relationship);
		}

	}


	@Override
	public void addGroup(MerchantStore store, String groupName) throws ServiceException {
		ProductRelationship relationship = new ProductRelationship();
		relationship.setCode(groupName);
		relationship.setStore(store);
		relationship.setActive(true);
		this.save(relationship);
	}

	@Override
	public List<ProductRelationship> getGroups(MerchantStore store) {
//		return productRelationshipDao.getGroups(store);
		return null;
	}

	@Override
	public void deleteGroup(MerchantStore store, String groupName) throws ServiceException {
//		List<ProductRelationship> entities = productRelationshipDao.getByGroup(store, groupName);
//		for(ProductRelationship relation : entities) {
//			this.delete(relation);
//		}
	}

	@Override
	public void deactivateGroup(MerchantStore store, String groupName) throws ServiceException {
//		List<ProductRelationship> entities = productRelationshipDao.getByGroup(store, groupName);
//		for(ProductRelationship relation : entities) {
//			relation.setActive(false);
//			this.saveOrUpdate(relation);
//		}
	}

	@Override
	public void activateGroup(MerchantStore store, String groupName) throws ServiceException {
		List<ProductRelationship> entities = this.getByGroup(store, groupName);
		for(ProductRelationship relation : entities) {
			relation.setActive(true);
			this.saveOrUpdate(relation);
		}
	}

	public void delete(ProductRelationship relationship) throws ServiceException {

		//throws detached exception so need to query first
		relationship = this.getById(relationship.getId());
		super.delete(relationship);


	}

	@Override
	public List<ProductRelationship> listByProduct(Product product) throws ServiceException {

//		return productRelationshipDao.listByProducts(product);
		return null;

	}


	@Override
	public List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type, Language language) throws ServiceException {

//		return productRelationshipDao.getByType(store, type.name(), product, language);
		return null;

	}

	@Override
	public List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type, Language language) throws ServiceException {
		return productRelationshipRepository.findByTypeAndLanguage(type.name(), language.getId());

	}

	@Override
	public List<ProductRelationship> getByType(MerchantStore store, ProductRelationshipType type) throws ServiceException {

//		return productRelationshipDao.getByType(store, type.name());
		return null;

	}

	@Override
	public List<ProductRelationship> getByGroup(MerchantStore store, String groupName) throws ServiceException {

//		return productRelationshipDao.getByType(store, groupName);
		return null;

	}

	@Override
	public List<ProductRelationship> getByGroup(MerchantStore store, String groupName, Language language) throws ServiceException {

//		return productRelationshipDao.getByType(store, groupName, language);
		return null;

	}

	@Override
	public List<ProductRelationship> getByType(MerchantStore store, Product product, ProductRelationshipType type) throws ServiceException {


//		return productRelationshipDao.getByType(store, type.name(), product);
		return null;


	}



}
