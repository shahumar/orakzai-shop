package org.orakzai.lab.shop.domain.business.reference.language.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.reference.language.dao.LanguageRepository;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//import org.orakzai.lab.shop.domain.business.reference.language.model.Language_;
import org.orakzai.lab.shop.domain.utils.CacheUtils;

@Service("languageService")
@CacheConfig(cacheNames = "shopme")
public class LanguageServiceImpl extends SalesManagerEntityServiceImpl<Integer, Language>
	implements LanguageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageServiceImpl.class);

	
	private LanguageRepository languageRepository;

	@Autowired
	public LanguageServiceImpl(LanguageRepository languageRepository) {
		super(languageRepository);
		this.languageRepository = languageRepository;
	}

	@Override
	public Language getByCode(String code) throws ServiceException {
		return languageRepository.findByCode(code);
	}

	@Override
	public Locale toLocale(Language language) {
		return new Locale(language.getCode());
	}

	@Override
	public Language toLanguage(Locale locale) {

		try {
			Language lang = getLanguagesMap().get(locale.getLanguage());
			return lang;
		} catch (Exception e) {
			LOGGER.error("Cannot convert locale " + locale.getLanguage() + " to language");
		}

		return null;

	}

	@Override
	public Map<String,Language> getLanguagesMap() throws ServiceException {

		List<Language> langs = this.getLanguages();

		Map<String,Language> returnMap = new LinkedHashMap<String,Language>();

		for(Language lang : langs) {

			returnMap.put(lang.getCode(), lang);

		}

		return returnMap;


	}


	@Override
	@SuppressWarnings("unchecked")
	@Cacheable
	public List<Language> getLanguages() throws ServiceException {
		List<Language> langs = new ArrayList<Language>();
		try {
			if(langs == null || langs.size() == 0) {
				langs = this.list();
			}

		} catch (Exception e) {
			LOGGER.error("getCountries()", e);
		}
		return langs;
	}

}
