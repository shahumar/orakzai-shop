//package org.orakzai.lab.shop.domain.business.tax.service;
//
//import java.util.List;
//
//import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
//import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
//import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
//import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
//import org.orakzai.lab.shop.domain.business.tax.model.taxrate.TaxRate;
//
//public interface TaxRateService extends SalesManagerEntityService<Long, TaxRate> {
//
//	public List<TaxRate> listByStore(MerchantStore store) throws ServiceException;
//
//	List<TaxRate> listByCountryZoneAndTaxClass(Country country, Zone zone,
//			TaxClass taxClass, MerchantStore store, Language language)
//			throws ServiceException;
//
//	List<TaxRate> listByCountryStateProvinceAndTaxClass(Country country,
//			String stateProvince, TaxClass taxClass, MerchantStore store,
//			Language language) throws ServiceException;
//
//	 TaxRate getByCode(String code, MerchantStore store)
//			throws ServiceException;
//
//	List<TaxRate> listByStore(MerchantStore store, Language language)
//			throws ServiceException;
//
//
//
//}
