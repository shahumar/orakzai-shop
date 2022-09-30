package org.orakzai.lab.shop.web.controller.reference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.utils.ajax.AjaxResponse;
import org.orakzai.lab.shop.web.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReferenceController {
	
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ZoneService zoneService;

	@PostMapping(value={"/admin/reference/provinces.html", "/shop/reference/provinces.html"}, produces = "application/json")
	public @ResponseBody String getProvinces(HttpServletRequest req, HttpServletResponse resp) {
		String countryCode = req.getParameter("countryCode");
		String lang = req.getParameter("lang");
		log.debug("Province Country code " + countryCode);
		AjaxResponse response = new AjaxResponse();
		try {
			Language language = null;
			if (!StringUtils.isBlank(lang)) {
				language = languageService.getByCode(lang);
			}
			if (language == null) {
				language = (Language) req.getAttribute(Constants.LANGUAGE);
			}
			if (language == null) {
				language = languageService.getByCode(Constants.DEFAULT_LANGUAGE);
			}
			Map<String, Country> countriesMap = countryService.getCountriesMap(language);
			Country country = countriesMap.get(countryCode);
			List<Zone> zones = zoneService.getZones(country, language);
			if (zones != null && zones.size() > 0) {
				for (Zone zone : zones) {
					Map entry = new HashMap();
					entry.put("name", zone.getName());
					entry.put("code", zone.getCode());
					entry.put("id", zone.getId());
					response.addDataEntry(entry);
				}
			}
			response.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
		} catch (Exception e) {
			log.error("GetProvinces()", e);
			response.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		String returnString = response.toJSONString();
		return returnString;
	}

}
