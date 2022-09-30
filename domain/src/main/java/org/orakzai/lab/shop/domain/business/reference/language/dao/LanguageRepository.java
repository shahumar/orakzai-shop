package org.orakzai.lab.shop.domain.business.reference.language.dao;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;

@Repository("languageRepository")
public interface LanguageRepository extends SalesManagerEntityDao<Integer, Language> {
	
	Language findByCode(String code);
}
