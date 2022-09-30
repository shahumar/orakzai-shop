package org.orakzai.lab.shop.domain.business.catalog.product.dao.attribute;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("productAttributeRepository")
public interface ProductAttributeRepository extends SalesManagerEntityDao<Long, ProductAttribute> {

	
	@Query("select pr from ProductAttribute pr join fetch pr.product p "
			+ "left join fetch pr.productOption prp left join fetch prp.merchantStore pms "
			+ "where prp.id=:id and prp.merchantStore=:store")
	List<ProductAttribute> getByOptionId(MerchantStore store, Long id);

	@Query("select pr from ProductAttribute pr join fetch pr.product p left join fetch pr.productOptionValue prp "
			+ "left join fetch prp.merchantStore pms where prp.merchantStore=:store and prp.id=:id")
	List<ProductAttribute> getByOptionValueId(MerchantStore store,
			Long id);

	@Query("select pr from ProductAttribute pr left join fetch pr.productOptionValue pov "
			+ "left join fetch pov.merchantStore join fetch pr.product left join fetch pov.descriptions povc "
			+ "where pr.product=:product and pov.merchantStore=:store and povc.language=:language")
	List<ProductAttribute> getByProduct(MerchantStore store, Product product, Language language);

	@Query("select pr from ProductAttribute pr join fetch pr.product p left join fetch pr.productOption prp "
			+ "left join fetch prp.merchantStore pms where prp.merchantStore=:store and pr.id in (:ids)")
	List<ProductAttribute> getByAttributeIds(MerchantStore store, List<Long> ids);

}
