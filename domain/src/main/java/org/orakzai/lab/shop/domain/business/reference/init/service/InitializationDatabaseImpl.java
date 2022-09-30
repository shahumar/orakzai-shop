package org.orakzai.lab.shop.domain.business.reference.init.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType;
import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.type.ProductTypeService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.merchant.service.MerchantStoreService;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.model.CountryDescription;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
import org.orakzai.lab.shop.domain.business.reference.currency.service.CurrencyService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.reference.zone.model.ZoneDescription;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.business.tax.service.TaxClassService;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;
import org.orakzai.lab.shop.domain.utils.reference.IntegrationModulesLoader;
import org.orakzai.lab.shop.domain.utils.reference.ZonesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("initializationDatabase")
public class InitializationDatabaseImpl implements InitializationDatabase{

//
    private String name;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private LanguageService languageService;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private TaxClassService taxClassService;

    @Autowired
    private ZonesLoader zonesLoader;

    @Autowired
    private IntegrationModulesLoader modulesLoader;

//    @Autowired
//    private ModuleConfigurationService moduleConfigurationService;


    @Autowired
    EntityManager em;
    
    
    @Override
    public boolean isEmpty() {
        return languageService.count() == 0;
    }

    @Override
	public boolean isEmptyInitialData() {
		
		return productService.count() == 0;
	}
    
    @Transactional
    @Override
    public void populate(String name) throws ServiceException {
        this.name = name;
        System.out.print(this.name);
        createLanguages();
        createCountries();
        createZones();
        createCurrencies();
        createSubReferences();
        createModules();
        createMerchant();
    }

    private void createMerchant() throws ServiceException {
        log.info(String.format("%s: Creating merchant ", name));
        LocalDate date = LocalDate.now();
        Language en = languageService.getByCode("en");
        Country c = countryService.getByCode("CA");
        Currency cc = currencyService.getByCode("CAD");
        Zone qc = zoneService.getByCode("QC");
        List<Language> languages = new ArrayList<>();
        languages.add(en);

        MerchantStore store = new MerchantStore();
        store.setCountry(c);
        store.setCurrency(cc);
        store.setDefaultLanguage(en);
        store.setInBusinessSince(date);
        store.setZone(qc);
        store.setStorename("Default store");
        store.setStorephone("99-999-9999");
        store.setCode(MerchantStore.DEFAULT_STORE);
        store.setStorecity("My city");
        store.setStoreaddress("1234 streed address");
        store.setStorepostalcode("0000");
        store.setStoreEmailAddress("shah@kmmrce.com");
        store.setDomainName("localhost:8080");
        store.setStoreTemplate("bootstrap");
        store.setLanguages(languages);
        merchantStoreService.create(store);

        TaxClass taxClass = new TaxClass(TaxClass.DEFAULT_TAX_CLASS);
        taxClass.setMerchantStore(store);
        taxClassService.create(taxClass);
    }

    private void createModules() throws ServiceException {
        try {
            List<IntegrationModule> modules = modulesLoader.loadIntegrationModules("reference/integrationmodules.json");
//            for (IntegrationModule module : modules)
//                moduleConfigurationService.create(module);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private void createSubReferences() throws ServiceException {
        log.info(String.format("%s : Loading catalog sub references ", name));
        ProductType productType = new ProductType();
        productType.setCode(ProductType.GENERAL_TYPE);
        productTypeService.create(productType);
    }

    private void createCurrencies() throws ServiceException {

        log.info("%s: Populating Currencies ", name);
        for (String code : SchemaConstant.CURRENCY_MAP.keySet()) {
            try {
                java.util.Currency c = java.util.Currency.getInstance(code);
                if (c == null) {
                    log.info(String.format("%s : Populating currencies : no currency for code : %s ", name, code));
                }
                Currency currency = new Currency();
                currency.setName(c.getCurrencyCode());
                currency.setCurrency(c);
                currencyService.create(currency);
            } catch (IllegalArgumentException e) {
                log.info(String.format("%s : Populating Currencies : no currency for code : %s ", name, code));
            }
        }
    }

    private void createZones() throws ServiceException {

        log.info(String.format("%s : Populating Zones ", name));
        try {
            Map<String, Zone> zoneMap = new HashMap<>();
            zoneMap = zonesLoader.loadZones("reference/zoneconfig.json");
            for (Map.Entry<String, Zone> entry : zoneMap.entrySet()) {
                String key = entry.getKey();
                Zone value = entry.getValue();
                if (value.getDescriptions() == null) {
                    log.warn("This zone " + key + " has no description");
                    continue;
                }
                List<ZoneDescription> zoneDescriptions = value.getDescriptions();
                value.setDescriptions(null);
                zoneService.create(value);
                for (ZoneDescription description : zoneDescriptions) {
                    description.setZone(value);
                    zoneService.addDescription(value, description);
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private void createCountries() throws ServiceException {
        log.info(String.format("%s: Populating countries ", name));
        List<Language> languages = languageService.list();
        for (String code : SchemaConstant.COUNTRY_ISO_CODE) {
            Locale locale = SchemaConstant.LOCALES.get(code);
            if (locale != null) {
                Country country = new Country(code);
                countryService.create(country);
                for (Language language : languages) {
                    String name = locale.getDisplayCountry(new Locale(language.getCode()));
                    CountryDescription description = new CountryDescription(language, name);
                    countryService.addCountryDescription(country, description);
                }
            }
        }
    }

    private void createLanguages() throws ServiceException {
        log.info(String.format("%s: Populating languages ", name));
        for (String code : SchemaConstant.LANGUAGE_ISO_CODE) {
            Language language = new Language(code);
            languageService.create(language);
        }
    }


}
