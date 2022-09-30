package org.orakzai.lab.shop.domain.business.reference.country.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("countryRepository")
public interface CountryRepository extends SalesManagerEntityDao<Integer,Country> {
	
	Country findByIsoCode(String code);
	
	@Query("select c From Country c left join fetch c.descriptions cd where cd.language = :language")
	List<Country> findByLanguage(Language language);

//	public List<Country> listByLanguage(Language language);
}
