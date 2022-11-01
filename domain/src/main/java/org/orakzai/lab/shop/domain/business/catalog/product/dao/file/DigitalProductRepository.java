package org.orakzai.lab.shop.domain.business.catalog.product.dao.file;

import java.util.Optional;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.DigitalProduct;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("digitalProductRepository")
public interface DigitalProductRepository extends SalesManagerEntityDao<Long, DigitalProduct> {

	@Query("Select dp from DigitalProduct inner join fetch dp.product p inner join fetch p.merchantStore "
			+ "where p.merchantStore=:store and dp.product=:product")
	Optional<DigitalProduct> findByStoreAndProduct(MerchantStore store, Product product);


}
