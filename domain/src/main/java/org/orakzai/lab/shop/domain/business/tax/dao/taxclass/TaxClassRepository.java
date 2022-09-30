package org.orakzai.lab.shop.domain.business.tax.dao.taxclass;

import java.util.List;
import java.util.Optional;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.springframework.stereotype.Repository;


@Repository("taxClassDao")
public interface TaxClassRepository  extends SalesManagerEntityDao<Long, TaxClass> {

	List<TaxClass> findAllByMerchantStore(MerchantStore store);

	Optional<TaxClass> findByCode(String code);

	Optional<TaxClass> findByCodeAndMerchantStore(String code, MerchantStore store);
	
	Optional<TaxClass> findById(Long id);

}
