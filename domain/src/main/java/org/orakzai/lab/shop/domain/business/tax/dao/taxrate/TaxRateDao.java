package org.orakzai.lab.shop.domain.business.tax.dao.taxrate;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.business.tax.model.taxrate.TaxRate;
import org.springframework.stereotype.Repository;


@Repository("taxRateDao")
public interface TaxRateDao  extends SalesManagerEntityDao<Long, TaxRate> {

//	List<TaxRate> listByStore(MerchantStore store);
//
//	List<TaxRate> listByCountryZoneAndTaxClass(Country country, Zone zone,
//			TaxClass taxClass, MerchantStore store, Language language);
//
//	List<TaxRate> listByCountryStateProvinceAndTaxClass(Country country,
//			String stateProvince, TaxClass taxClass, MerchantStore store,
//			Language language);
//
//	TaxRate getByCode(String code, MerchantStore store);
//
//	List<TaxRate> listByStore(MerchantStore store, Language language);

}
