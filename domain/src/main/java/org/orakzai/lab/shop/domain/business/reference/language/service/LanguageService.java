package org.orakzai.lab.shop.domain.business.reference.language.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

public interface LanguageService extends SalesManagerEntityService<Integer, Language> {

	Language getByCode(String code) throws ServiceException;

	Map<String, Language> getLanguagesMap() throws ServiceException;

	List<Language> getLanguages() throws ServiceException;

	Locale toLocale(Language language);

	Language toLanguage(Locale locale);
	
}
