package org.orakzai.lab.shop.domain.business.tax.dao.taxrate;

import java.util.List;
import java.util.Optional;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.business.tax.model.taxrate.TaxRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("taxRateRepository")
public interface TaxRateRepository  extends SalesManagerEntityDao<Long, TaxRate> {

	List<TaxRate> findAllByMerchantStore(MerchantStore store);
	
	

	@Query("select tr from TaxRate tr left join fetch tr.merchantStore s left join fetch "
			+ "tr.descriptions d left join fetch tr.country c left join fetch tr.zone z left join fetch tr.parent p "
			+ "where tr.merchantStore=:store and (tr.zone=:zone or tr.id is Null) and tr.country=:country and d.language=:language order by tr.taxPriority asc")
	List<TaxRate> findAllByCountryAndZone(Country country, Zone zone,
			MerchantStore store, Language language);

	@Query("select tr from TaxRate tr left join fetch tr.merchantStore s left join fetch "
			+ "tr.descriptions d left join fetch tr.country c left join fetch tr.zone z left join fetch tr.parent p "
			+ "where tr.merchantStore=:store and tr.stateProvince=:stateProvince and tr.country=:country "
			+ "and d.language =:language order by tr.taxPriority asc")
	List<TaxRate> findAllByCountryStateProvince(Country country,
			String stateProvince, MerchantStore store, Language language);

	Optional<TaxRate> findByCodeAndMerchantStore(String code, MerchantStore store);

	@Query("select tr from TaxRate tr left join fetch tr.merchantStore s left join fetch "
			+ "tr.descriptions d left join fetch tr.country c left join fetch tr.zone z left join fetch tr.parent p "
			+ "where tr.merchantStore=:store  and d.language=:language order by tr.taxPriority asc")
	List<TaxRate> listByStore(MerchantStore store, Language language);

}
