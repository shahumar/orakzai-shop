package org.orakzai.lab.shop.web.admin.controller.merchantstore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.merchant.service.MerchantStoreService;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
import org.orakzai.lab.shop.domain.business.reference.currency.service.CurrencyService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.business.system.service.EmailService;
import org.orakzai.lab.shop.domain.modules.email.Email;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.admin.dto.reference.Size;
import org.orakzai.lab.shop.web.admin.dto.reference.Weight;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.constants.EmailConstants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.orakzai.lab.shop.web.utils.DateUtil;
import org.orakzai.lab.shop.web.utils.EmailUtils;
import org.orakzai.lab.shop.web.utils.FilePathUtils;
import org.orakzai.lab.shop.web.utils.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/store")
public class MerchantStoreController {
	
	private static final String NEW_STORE_TMPL = "template_new_store.html";
	@Autowired
	private LanguageService languageService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private MessageSource messages;
	@Autowired
	private ZoneService zoneService;
	@Autowired
	private MerchantStoreService storeService;
	@Autowired
	private EmailService emailService;
	
	@PreAuthorize("hasRole('STORE')")
	@GetMapping("/store.html")
	public String displayMerchantStore(Model model, HttpServletRequest req, HttpServletResponse resp, Locale locale) throws Exception {
		setMenu(model, req);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);		
		return displayMerchantStore(store, model, req, resp, locale);
	}
	
	@PreAuthorize("hasRole('STORE')")
	@GetMapping("/storeCreate.html")
	public String createView(Model model, HttpServletRequest req, HttpServletResponse resp, Locale locale) throws Exception {
		
		setMenu(model, req);
		MerchantStore store = new MerchantStore();
		MerchantStore sessionStore = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		store.setCurrency(sessionStore.getCurrency());
		store.setCountry(sessionStore.getCountry());
		store.setZone(sessionStore.getZone());
		store.setStorestateprovince(sessionStore.getStorestateprovince());
		store.setLanguages(sessionStore.getLanguages());
		store.setDomainName(sessionStore.getDomainName());
		return displayMerchantStore(store, model, req, resp, locale);
	}
	
	
	@PreAuthorize("hasRole('STORE')")
	@PostMapping("/save.html")
	public String saveMerchantStore(@Valid @ModelAttribute("store") MerchantStore store, BindingResult result, Model model, HttpServletRequest req, HttpServletResponse resp, Locale locale) throws Exception {
		setMenu(model, req);
		MerchantStore sessionStore = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		
		if (store.getId() != null) {
			if (store.getId().intValue() != sessionStore.getId().intValue()) {
				return "redirect:/admin/store/store.html";
			}
		}
		
		LocalDate dt = LocalDate.now();
		if (!StringUtils.isBlank(store.getDateBusinessSince())) {
			try {
				dt = DateUtil.getDate(store.getDateBusinessSince());
				store.setInBusinessSince(dt);
			} catch (Exception e) {
				ObjectError er = new ObjectError("dateBusinessSince", messages.getMessage("message.invalid.date", null, locale));
				result.addError(er);
			}
		}
		var currencies = currencyService.list();
		var language = (Language) req.getAttribute(Constants.LANGUAGE);
		var languages = languageService.getLanguages();
		var countries = countryService.getCountries(language);
		var weights = new ArrayList<Weight>();
		weights.add(new Weight("LB", messages.getMessage("label.generic.weightunit.LB", null, locale)));
		weights.add(new Weight("KG", messages.getMessage("label.generic.weightunit.KG", null, locale)));
		var sizes = new ArrayList<Size>();
		sizes.add(new Size("CM", messages.getMessage("label.generic.sizeunit.CM", null, locale)));
		sizes.add(new Size("In", messages.getMessage("label.generic.sizeunit.IN", null, locale)));
		model.addAttribute("weights", weights);
		model.addAttribute("sizes", sizes);
		model.addAttribute("countries", countries);
		model.addAttribute("allLanguages", languages);
		model.addAttribute("currencies", currencies);
		
		var c = countryService.getByCode(store.getCountry().getIsoCode());
		var zonesList = zoneService.getZones(c, language);
		if ((zonesList == null || zonesList.size() == 0) && StringUtils.isBlank(store.getStorestateprovince())) {
			ObjectError err = new ObjectError("zone.code", messages.getMessage("merchant.zone.invalid", null, locale));
			result.addError(err);
			
		}
		if (result.hasErrors()) {
			return ControllerConstants.Templates.STORE_TPL;
		}
			
		c = countryService.getByCode(c.getIsoCode());
		var zone = store.getZone();
		if (zone != null) {
			zone = zoneService.getByCode(zone.getCode());
		}
		var currency = store.getCurrency();
		currency = currencyService.getById(currency.getId());
		var supportedLanguages = store.getLanguages();
		var languagesMap = languageService.getLanguagesMap();
		var supportedLanguagesList = supportedLanguages.stream()
				.map(lang -> languagesMap.get(lang.getCode()))
				.filter(lang -> lang != null)
				.collect(Collectors.toList());
		
		var defaultLanguage = store.getDefaultLanguage();
		defaultLanguage = languageService.getByCode(defaultLanguage.getCode());
		if (defaultLanguage != null) {
			store.setDefaultLanguage(defaultLanguage);
		}
		Locale storeLocale = LocaleUtils.getLocale(defaultLanguage);
		store.setStoreTemplate(sessionStore.getStoreTemplate());
		store.setCountry(c);
		store.setZone(zone);
		store.setCurrency(currency);
		store.setDefaultLanguage(defaultLanguage);
		store.setLanguages(supportedLanguagesList);
		storeService.saveOrUpdate(store);
		if(!store.getCode().equals(sessionStore.getCode())) {
			try {
				var templateTokens = EmailUtils.createEmailObjectsMap(req.getContextPath(), store, messages, storeLocale);
				templateTokens.put(EmailConstants.EMAIL_NEW_STORE_TEXT, messages.getMessage("email.newstore.info", null, storeLocale));
				templateTokens.put(EmailConstants.EMAIL_STORE_NAME, messages.getMessage("email.newstore.name", new String[] {store.getStorename()}, storeLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_STORE_INFO_LABEL, messages.getMessage("email.newstore.info", null, storeLocale));

				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL_LABEL, messages.getMessage("label.adminurl", null, storeLocale));
				templateTokens.put(EmailConstants.EMAIL_ADMIN_URL, FilePathUtils.buildAdminUri(store, req));
				Email email = new Email();
				email.setFrom(store.getStorename());
				email.setFromEmail(store.getStoreEmailAddress());
				email.setSubject(messages.getMessage("email.newstore.title", null, storeLocale));
				email.setTo(store.getStoreEmailAddress());
				email.setTemplateName(NEW_STORE_TMPL);
				email.setTemplateTokens(templateTokens);
				emailService.sendHtmlEmail(store, email);
				
			} catch (Exception e) {
				System.out.println(e + "");
			}
		}
		sessionStore = storeService.getMerchantStore(sessionStore.getCode());
		req.getSession().setAttribute(Constants.ADMIN_STORE, sessionStore);
		model.addAttribute("success", "success");
		model.addAttribute("store", store);
		
		return ControllerConstants.Templates.STORE_TPL;
	}

	private String displayMerchantStore(MerchantStore store, Model model, HttpServletRequest req,
			HttpServletResponse resp, Locale locale) throws Exception {
		setMenu(model, req);
		Language language = (Language) req.getAttribute(Constants.LANGUAGE);
		List<Language> languages = languageService.getLanguages();
		List<Currency> currencies = currencyService.list();
		LocalDate dt = store.getInBusinessSince();
		if (dt != null) {
			store.setDateBusinessSince(DateUtil.formatDate(dt));
		} else {
			store.setDateBusinessSince(DateUtil.formatDate(LocalDate.now()));
		}
		List<Country> countries = countryService.getCountries(language); 
		var weights = new ArrayList<Weight>();
		weights.add(new Weight("LB", messages.getMessage("label.generic.weightunit.LB", null, locale)));
		weights.add(new Weight("KG", messages.getMessage("label.generic.weightunit.KG", null, locale)));
		
		var sizes = new ArrayList<Size>();
		sizes.add(new Size("CM",messages.getMessage("label.generic.sizeunit.CM", null, locale)));
		sizes.add(new Size("IN",messages.getMessage("label.generic.sizeunit.IN", null, locale)));
		model.addAttribute("countries", countries);
		model.addAttribute("currencies", currencies);
		model.addAttribute("allLanguages", languages);
		model.addAttribute("weights", weights);
		model.addAttribute("sizes", sizes);
		model.addAttribute("store", store);
		return ControllerConstants.Templates.STORE_TPL;
	}

	private void setMenu(Model model, HttpServletRequest req) {
		var activeMenus = new HashMap<String, String>();
		activeMenus.put("store", "store");
		activeMenus.put("storeDetails", "storeDetails");
		
		var menus = (Map<String, Menu>) req.getAttribute("MENUMAP");
		Menu current = (Menu) menus.get("store");
		model.addAttribute("currentMenu", current);
		model.addAttribute("activeMenus", activeMenus);
		
	}

}
