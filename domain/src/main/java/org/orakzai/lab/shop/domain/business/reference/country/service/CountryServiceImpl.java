package org.orakzai.lab.shop.domain.business.reference.country.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.reference.country.dao.CountryRepository;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.model.CountryDescription;
//import org.orakzai.lab.shop.domain.business.reference.country.model.Country_;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.utils.CacheUtils;

@Service("countryService")
@CacheConfig(cacheNames = "shopme")
public class CountryServiceImpl extends SalesManagerEntityServiceImpl<Integer, Country>
		implements CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

	private CountryRepository countryRepository;

	@Autowired
	public CountryServiceImpl(CountryRepository countryRepository) {
		super(countryRepository);
		this.countryRepository = countryRepository;
	}

	public Country getByCode(String code) throws ServiceException {
		return countryRepository.findByIsoCode(code);
	}

	@Override
	public void addCountryDescription(Country country, CountryDescription description) throws ServiceException {
		country.getDescriptions().add(description);
		description.setCountry(country);
		update(country);
	}

	@Override
	public Map<String,Country> getCountriesMap(Language language) throws ServiceException {

		List<Country> countries = this.getCountries(language);

		Map<String,Country> returnMap = new LinkedHashMap<String,Country>();

		for(Country country : countries) {
			returnMap.put(country.getIsoCode(), country);
		}

		return returnMap;
	}


	@Override
	public List<Country> getCountries(final List<String> isoCodes, final Language language) throws ServiceException {
		List<Country> countryList = getCountries(language);
		List<Country> requestedCountryList = new ArrayList<Country>();
		if(!CollectionUtils.isEmpty(countryList)) {
			for(Country c : countryList) {
				if(isoCodes.contains(c.getIsoCode())) {
					requestedCountryList.add(c);
				}
			}
		}
		return requestedCountryList;
	}


	@SuppressWarnings("unchecked")
	@Override
	@Cacheable
	public List<Country> getCountries(Language language) throws ServiceException {
		List<Country> countries = null;
		try {
			countries = countryRepository.findByLanguage(language);
			for(Country country : countries) {

				CountryDescription description = country.getDescriptions().get(0);
				country.setName(description.getName());
			}

		} catch (Exception e) {
			LOGGER.error("getCountries()", e);
		}

		return countries;
	}


}
