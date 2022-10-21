package org.orakzai.lab.shop.domain.business.reference.zone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.dao.ZoneRepository;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.reference.zone.model.ZoneDescription;
//import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone_;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.orakzai.lab.shop.domain.utils.CacheUtils;

@Slf4j
@Service("zoneService")
@CacheConfig(cacheNames = "shopme")
public class ZoneServiceImpl extends SalesManagerEntityServiceImpl<Long, Zone> implements
		ZoneService {

	private final static String ZONE_CACHE_PREFIX = "ZONES_";

	private ZoneRepository zoneRepository;

	@Autowired
	private CacheUtils cache;


	@Autowired
	public ZoneServiceImpl(ZoneRepository zoneRepository) {
		super(zoneRepository);
		this.zoneRepository = zoneRepository;
	}

	@Override
	public Zone getByCode(String code) {
		return zoneRepository.findByCode(code);
	}

	@Override
	public void addDescription(Zone zone, ZoneDescription description) throws ServiceException {
		if (zone.getDescriptions()!=null) {
				if(!zone.getDescriptions().contains(description)) {
					zone.getDescriptions().add(description);
					zoneRepository.save(zone);
				}
		} else {
			List<ZoneDescription> descriptions = new ArrayList<ZoneDescription>();
			descriptions.add(description);
			zone.setDescriptions(descriptions);
			zoneRepository.save(zone);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable
	public List<Zone> getZones(Country country, Language language) throws ServiceException {
		List<Zone> zones = null;
		try {
			if (zones == null) {
				zones = zoneRepository.findAllByLanguageAndCountry(country, language);
				for (Zone zone : zones) {
					ZoneDescription description = zone.getDescriptions().get(0);
					zone.setName(description.getName());
				}
			}

		} catch (Exception e) {
			log.error("getZones()", e);
		}
		return zones;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Zone> getZones(Language language) throws ServiceException {

		Map<String, Zone> zones = null;
		try {
			String cacheKey = ZONE_CACHE_PREFIX + language.getCode();
			zones = (Map<String, Zone>) cache.getFromCache(cacheKey);
			if(zones==null) {
				zones = new HashMap<String, Zone>();
				List<Zone> zns = zoneRepository.findAllByLanguage(language);
				for(Zone zone : zns) {
					ZoneDescription description = zone.getDescriptions().get(0);
					zone.setName(description.getName());
					zones.put(zone.getCode(), zone);

				}
				cache.putInCache(zones, cacheKey);
			}

		} catch (Exception e) {
			log.error("getZones()", e);
		}
		return zones;


	}

}
