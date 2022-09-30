package org.orakzai.lab.shop.domain.business.reference.zone.service;

import java.util.List;
import java.util.Map;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.reference.zone.model.ZoneDescription;

public interface ZoneService extends SalesManagerEntityService<Long, Zone> {

	Zone getByCode(String code);

	void addDescription(Zone zone, ZoneDescription description) throws ServiceException;

	List<Zone> getZones(Country country, Language language)
			throws ServiceException;

	Map<String, Zone> getZones(Language language) throws ServiceException;


}
