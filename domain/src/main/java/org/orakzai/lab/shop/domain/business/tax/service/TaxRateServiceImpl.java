package org.orakzai.lab.shop.domain.business.tax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.tax.dao.taxrate.TaxRateRepository;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.business.tax.model.taxrate.TaxRate;

@Service("taxRateService")
public class TaxRateServiceImpl extends SalesManagerEntityServiceImpl<Long, TaxRate>
		implements TaxRateService {

	private TaxRateRepository taxRateRepository;

	@Autowired
	public TaxRateServiceImpl(TaxRateRepository taxRateDao) {
		super(taxRateDao);

		this.taxRateRepository = taxRateDao;
	}

	@Override
	public List<TaxRate> listByStore(MerchantStore store)
			throws ServiceException {
		return taxRateRepository.findAllByMerchantStore(store);
	}

	@Override
	public List<TaxRate> listByStore(MerchantStore store, Language language)
			throws ServiceException {
		return taxRateRepository.listByStore(store, language);
	}


	@Override
	public TaxRate getByCode(String code, MerchantStore store)
			throws ServiceException {
		return taxRateRepository.findByCodeAndMerchantStore(code,store).get();
	}

	@Override
	public List<TaxRate> listByCountryZoneAndTaxClass(Country country, Zone zone, TaxClass taxClass, MerchantStore store, Language language) throws ServiceException {
		return taxRateRepository.findAllByCountryAndZoneAndTaxClass(country, zone, taxClass, store, language);
	}

	@Override
	public List<TaxRate> listByCountryStateProvinceAndTaxClass(Country country, String stateProvince, TaxClass taxClass, MerchantStore store, Language language) throws ServiceException {
		return taxRateRepository.findAllByCountryStateProvinceAndTaxClass(country, stateProvince, taxClass, store, language);
	}

	@Override
	public void delete(TaxRate taxRate) throws ServiceException {

		TaxRate t = this.getById(taxRate.getId());
		super.delete(t);

	}



}
