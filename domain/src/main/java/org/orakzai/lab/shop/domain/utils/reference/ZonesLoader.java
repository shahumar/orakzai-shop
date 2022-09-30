package org.orakzai.lab.shop.domain.utils.reference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.reference.zone.model.ZoneDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ZonesLoader {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CountryService countryService;

    public Map<String, Zone> loadZones(String jsonFilePath) throws Exception {

        List<Language> languages = languageService.list();

        List<Country> countries = countryService.list();
        Map<String, Country> countriesMap = new HashMap<>();
        for (Country country : countries) {
            countriesMap.put(country.getIsoCode(), country);
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            InputStream in =
                    this.getClass().getClassLoader().getResourceAsStream(jsonFilePath);

            @SuppressWarnings("unchecked")
            Map<String, Object> data = mapper.readValue(in, Map.class);

            Map<String, Zone> zonesMap = new HashMap<String, Zone>();
            Map<String, List<ZoneDescription>> zonesDescriptionsMap = new HashMap<String, List<ZoneDescription>>();
            Map<String, String> zonesMark = new HashMap<String, String>();

            for (Language l : languages) {
                @SuppressWarnings("rawtypes")
                List langList = (List) data.get(l.getCode());
                if (langList != null) {
                    for (Object z : langList) {
                        @SuppressWarnings("unchecked")
                        Map<String, String> e = (Map<String, String>) z;
                        String zoneCode = e.get("zoneCode");
                        ZoneDescription zoneDescription = new ZoneDescription();
                        zoneDescription.setLanguage(l);
                        zoneDescription.setName(e.get("zoneName"));
                        Zone zone = null;
                        List<ZoneDescription> descriptions = null;
                        if (!zonesMap.containsKey(zoneCode)) {
                            zone = new Zone();
                            Country country = countriesMap.get(e.get("countryCode"));
                            if (country == null) {
                                log.warn("Country is null for " + zoneCode + " and country code " + e.get("countryCode"));
                                continue;
                            }
                            zone.setCountry(country);
                            zonesMap.put(zoneCode, zone);
                            zone.setCode(zoneCode);

                        }

                        if (zonesMark.containsKey(l.getCode() + "_" + zoneCode)) {
                            log.warn("This zone seems to be a duplicate !  " + zoneCode + " and language code " + l.getCode());
                            continue;
                        }

                        zonesMark.put(l.getCode() + "_" + zoneCode, l.getCode() + "_" + zoneCode);

                        if (zonesDescriptionsMap.containsKey(zoneCode)) {
                            descriptions = zonesDescriptionsMap.get(zoneCode);
                        } else {
                            descriptions = new ArrayList<ZoneDescription>();
                            zonesDescriptionsMap.put(zoneCode, descriptions);
                        }

                        descriptions.add(zoneDescription);

                    }
                }

            }

            for (Map.Entry<String, Zone> entry : zonesMap.entrySet()) {
                String key = entry.getKey();
                Zone value = entry.getValue();

                //get descriptions
                List<ZoneDescription> descriptions = zonesDescriptionsMap.get(key);
                if (descriptions != null) {
                    value.setDescriptions(descriptions);
                }
            }

            return zonesMap;


        } catch (Exception e) {
            throw new ServiceException(e);
        }


    }

}
