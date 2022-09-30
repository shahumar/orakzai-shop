package org.orakzai.lab.shop.domain.business.catalog.product.dao.relationship;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationship;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("productRelationshipRepository")
public interface ProductRelationshipRepository extends SalesManagerEntityDao<Long, ProductRelationship> {

	@Query("select distinct pr from ProductRelationship as pr left join fetch pr.product p "
			+ "join fetch pr.relatedProduct rp "
			+ "left join fetch rp.attributes pattr "
			+ "left join fetch rp.descriptions rpd "
			+ "left join fetch rp.images pd "
			+ "left join fetch rp.merchantStore rpm "
			+ "left join fetch rpm.currency rpmc "
			+ "left join fetch rp.availabilities pa "
			+ "left join fetch rp.manufacturer m "
			+ "left join fetch m.descriptions md "
			+ "left join fetch pa.prices pap "
			+ "left join fetch pap.descriptions papd "
			+ "where pr.code=:code and rpd.language.id=:langId")
	List<ProductRelationship> findByTypeAndLanguage(String code, Integer langId);

//	List<ProductRelationship> getByType(MerchantStore store, String type, Product product, Language language);
//
//	List<ProductRelationship> getByType(MerchantStore store, String type,
//			Product product);
//
//	List<ProductRelationship> getByType(MerchantStore store, String type);
//
//	List<ProductRelationship> listByProducts(Product product);
//
//	List<ProductRelationship> getByType(MerchantStore store, String type,
//			Language language);
//
//	List<ProductRelationship> getGroups(MerchantStore store);
//
//	List<ProductRelationship> getByGroup(MerchantStore store, String group);



}
