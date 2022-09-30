package org.orakzai.lab.shop.domain.business.catalog.product.dao.manufacturer;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.Manufacturer;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ManufacturerRepository extends SalesManagerEntityDao<Long, Manufacturer> {

//
//	List<Manufacturer> listByStore(MerchantStore store);
//	
//	int getCountManufAttachedProducts(  Manufacturer manufacturer  );
//
//	List<Manufacturer> listByProductsByCategoriesId(MerchantStore store,
//			List<Long> ids, Language language);
	
	
	@Query("from Manufacturer as m left join fetch m.descriptions md "
			+ "left join fetch m.merchantStore ms "
			+ "where m.merchantStore =:store and md.language =:language")
	List<Manufacturer> findAllByStoreAndLanguage(MerchantStore store, Language language);

}
