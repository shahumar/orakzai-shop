package org.orakzai.lab.shop.web.admin.controller.customers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerCriteria;
import org.orakzai.lab.shop.domain.business.customer.model.CustomerList;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionSet;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionType;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionValueDescription;
import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.customer.service.attribute.CustomerAttributeService;
import org.orakzai.lab.shop.domain.business.customer.service.attribute.CustomerOptionService;
import org.orakzai.lab.shop.domain.business.customer.service.attribute.CustomerOptionSetService;
import org.orakzai.lab.shop.domain.business.customer.service.attribute.CustomerOptionValueService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.business.system.service.EmailService;
import org.orakzai.lab.shop.domain.utils.ajax.AjaxPageableResponse;
import org.orakzai.lab.shop.domain.utils.ajax.AjaxResponse;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOption;
import org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOptionValue;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.orakzai.lab.shop.web.mapper.customer.CustomerOptionPopulator;
import org.orakzai.lab.shop.web.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	private static final String CUSTOMER_ID_PARAMETER = "customer";
	
	private final static String RESET_PASSWORD_TPL = "email_template_password_reset_customer.html";
	
	@Autowired
	private LabelUtils messages;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerOptionService customerOptionService;
	
	@Autowired
	private CustomerOptionValueService customerOptionValueService;
	
	@Autowired
	private CustomerOptionSetService customerOptionSetService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CustomerAttributeService customerAttributeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	EmailService emailService;
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value="/admin/customers/customer.html", method=RequestMethod.GET)
	public String displayCustomer(Long id, Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		this.setMenu(model, req);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		List<Language> languages = languageService.getLanguages();
		model.addAttribute("languages", languages);
		Customer customer = null;
		
		if (id != null && id != 0) {
			customer = customerService.getById(id);
			if (customer == null) {
				return "redirect:/admin/customers/list.html";
			}
			if (customer.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				return "redirect:/admin/customers/list.html";
			}
		} else {
			customer = new Customer();
		}
		
		Language language = (Language) req.getAttribute("LANGUAGE");
		List<Country> countries = countryService.getCountries(language);
		List<Zone> zones = zoneService.list();
		this.getCustomerOptions(model, customer, store, language);
		model.addAttribute("zones", zones);
		model.addAttribute("countries", countries);
		model.addAttribute("customer", customer);
		return ControllerConstants.Templates.Customer.adminCustomers;
	}
	
	
	@RequestMapping(value="/admin/customers/list.html", method=RequestMethod.GET)
	public String displayCustomers(Model model, HttpServletRequest req) throws Exception {
		this.setMenu(model, req);
		return ControllerConstants.Templates.Customer.adminCustomers;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/admin/customers/page.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pageCustomers(HttpServletRequest req, HttpServletResponse resp) {
		AjaxPageableResponse result = new AjaxPageableResponse();
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		try {
			int startRow = Integer.parseInt(req.getParameter("_startRow"));
			int endRow = Integer.parseInt(req.getParameter("_endRow"));
			String email = req.getParameter("email");
			String name = req.getParameter("name");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String country = req.getParameter("country");
			
			CustomerCriteria criteria = new CustomerCriteria();
			
			criteria.setStartIndex(startRow);
			criteria.setMaxCount(endRow);
			if (!StringUtils.isBlank(email))
				criteria.setEmail(email);
			if (!StringUtils.isBlank(name))
				criteria.setName(name);
			if (!StringUtils.isBlank(country))
				criteria.setCountry(country);
			if (!StringUtils.isBlank(firstName))
				criteria.setFirstName(firstName);
			if (!StringUtils.isBlank(lastName))
				criteria.setLastName(lastName);
			CustomerList customerList = customerService.listByStore(store, criteria);
			
			if (customerList.getCustomers() != null)
				for (Customer customer : customerList.getCustomers()) {
					@SuppressWarnings("rawtypes")
					Map entry = new HashMap();
					entry.put("id", customer.getId());
					entry.put("firstName", customer.getBilling().getFirstName());
					entry.put("lastName", customer.getBilling().getLastName());
					entry.put("email", customer.getEmailAddress());
					entry.put("country", customer.getBilling().getCountry().getIsoCode());
					result.addDataEntry(entry);
				}
		} catch (Exception e) {
			LOGGER.error("Error while paging orders", e);
			result.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		String returnString = result.toJSONString();
		return returnString;
		
	}
	
	private void setMenu(Model model, HttpServletRequest req) {
		Map<String, String> activeMenus = new HashMap<>();
		activeMenus.put("customer", "customer");
		activeMenus.put("customer-list", "customer-list");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)req.getAttribute("MENUMAP");
		Menu currentMenu = (Menu)menus.get("customer");
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
		
	}

	private void getCustomerOptions(Model model, Customer customer, MerchantStore store, Language language) throws Exception {
		Map<Long, CustomerOption> options = new HashMap<>();
		List<CustomerOptionSet> optionSet = customerOptionSetService.listByStore(store, language);
		if (!CollectionUtils.isEmpty(optionSet)) {
			CustomerOptionPopulator optionPopulator = new CustomerOptionPopulator();
			Set<CustomerAttribute> customerAttributes = customer.getAttributes();
			for (CustomerOptionSet optSet : optionSet) {
				org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOption custOption = optSet.getCustomerOption();
				if (!custOption.isActive()) {
					continue;
				}
				CustomerOption customerOption = options.get(custOption.getId());
				optionPopulator.setOptionSet(optSet);
				if (customerOption == null) {
					customerOption = new CustomerOption();
					customerOption.setId(custOption.getId());
					customerOption.setType(custOption.getCustomerOptionType());
					customerOption.setName(custOption.getDescriptionsSettoList().get(0).getName());
					
				}
				optionPopulator.populate(custOption, customerOption, store, language);
				options.put(customerOption.getId(), customerOption);
				if (!CollectionUtils.isEmpty(customerAttributes)) {
					for (CustomerAttribute customerAttribute : customerAttributes) {
						if (customerAttribute.getCustomerOption().getId().longValue() == customerOption.getId().longValue()) {
							CustomerOptionValue selectedValue = new CustomerOptionValue();
							org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionValue  attributeValue = customerAttribute.getCustomerOptionValue();
							selectedValue.setId(attributeValue.getId());
							CustomerOptionValueDescription optValue = attributeValue.getDescriptionsSettoList().get(0);
							selectedValue.setName(optValue.getName());
							customerOption.setDefaultValue(selectedValue);
							if (customerOption.getType().equalsIgnoreCase(CustomerOptionType.Text.name())) {
								selectedValue.setName(customerAttribute.getTextValue());
							}
						}
					}
				}
			}
		}
		model.addAttribute("options", options.values());
		
	}

}
