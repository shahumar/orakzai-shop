package org.orakzai.lab.shop.domain.business.reference.zone.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface ZoneRepository extends SalesManagerEntityDao<Long,Zone> {

//
//	List<Zone> listByLanguage(Language language);
	
	Zone findByCode(String code);
	
	@Query("select z from Zone z left join fetch z.descriptions d "
			+ "where d.language=:language and z.country=:country "
			+ "order by d.name ASC")
	List<Zone> findAllByLanguageAndCountry(Country country, Language language);

}
