package org.orakzai.lab.shop.web.utils;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.business.system.service.EmailService;
import org.orakzai.lab.shop.domain.modules.email.Email;
import org.orakzai.lab.shop.web.constants.EmailConstants;
import org.orakzai.lab.shop.web.dto.customer.PersistableCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailTemplatesUtils {
	
	private static final String LINE_BREAK = "<br/>";
	
	@Autowired
	private MessageSource messages;
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private CountryService countryService;

	@Async
	public void sendRegistrationEmail(PersistableCustomer customer, MerchantStore store, Locale locale,
			String contextPath) {
		log.info("Sending welcome email to customer");
		try {
			var templateTokens = EmailUtils.createEmailObjectsMap(contextPath, store, messages, locale);
			templateTokens.put(EmailConstants.LABEL_HI, messages.getMessage("label.generic.hi", null, locale));
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_FIRSTNAME, customer.getBilling().getFirstName());
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_LASTNAME, customer.getBilling().getLastName());
			String[] greetingMessage = {store.getStorename(), FilePathUtils.buildCustomerUri(store, contextPath), store.getStoreEmailAddress()};
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_GREETING, messages.getMessage("email.customer.greeting", greetingMessage, locale));
			templateTokens.put(EmailConstants.EMAIL_USERNAME_LABEL, messages.getMessage("label.generic.username", null, locale));
			templateTokens.put(EmailConstants.EMAIL_PASSWORD_LABEL, messages.getMessage("label.generic.password", null, locale));
			templateTokens.put(EmailConstants.CUSTOMER_ACCESS_LABEL, messages.getMessage("label.customer.accessportal", null, locale));
			templateTokens.put(EmailConstants.ACCESS_NOW_LABEL, messages.getMessage("label.customer.accessnow", null, locale));
			templateTokens.put(EmailConstants.EMAIL_USER_NAME, customer.getUserName());
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_PASSWORD, customer.getClearPassword());
			
			String customerUrl = FilePathUtils.buildStoreUri(store, contextPath);
			templateTokens.put(EmailConstants.CUSTOMER_ACCESS_URL, customerUrl);
			
			Email mail = new Email();
			mail.setTo(customer.getEmailAddress());
			mail.setFrom(store.getStorename());
			mail.setFromEmail(store.getStoreEmailAddress());
			mail.setSubject(messages.getMessage("email.newuser.title", null, locale));
			mail.setTemplateName(EmailConstants.EMAIL_CUSTOMER_TPL);
			mail.setTemplateTokens(templateTokens);
			log.debug("Sending email to {} on their registered email id {}", customer.getBilling().getFirstName(), customer.getEmailAddress());
			emailService.sendHtmlEmail(store, mail);
			
		} catch (Exception e) {
			log.error("Error occured while sending email", e);
		}
	}

	@Async
	public void sendOrderEmail(Customer modelCustomer, Order order, Locale locale, Language language,
			MerchantStore store, String contextPath) {
		log.info("Sending welcome email to customer");
		try {
			var zones = zoneService.getZones(language);
			var countries = countryService.getCountriesMap(language);
			StringBuilder billing = new StringBuilder();
			if (StringUtils.isBlank(order.getBilling().getCompany())) {
				billing.append(order.getBilling().getFirstName())
					.append(" ")
					.append(order.getBilling().getLastName())
					.append(LINE_BREAK);
			} else {
				billing.append(order.getBilling().getCompany()).append(LINE_BREAK);
			}
		}
		
	}

}
