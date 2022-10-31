package org.orakzai.lab.shop.web.utils;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotal;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.business.system.service.EmailService;
import org.orakzai.lab.shop.domain.modules.email.Email;
import org.orakzai.lab.shop.web.constants.ApplicationConstants;
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

	private static final Object TABLE = null;

	private static final Object TR = null;

	private static final Object TD = null;

	private static final Object CLOSING_TD = null;

	private static final String CLOSING_TR = null;

	private static final Object TR_BORDER = null;

	private static final Object CLOSING_TABLE = null;
	
	@Autowired
	private MessageSource messages;
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private CountryService countryService;

	@Autowired
	private PricingService pricingService;

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
	public void sendOrderEmail(Customer customer, Order order, Locale locale, Language language,
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
			billing.append(order.getBilling().getAddress()).append(LINE_BREAK);
			billing.append(order.getBilling().getCity()).append(", ");
			if (order.getBilling().getZone() != null) {
				var zone = zones.get(order.getBilling().getZone().getCode());
				if (zone != null) {
					billing.append(zone.getName());
				}
				billing.append(LINE_BREAK);
			} else if(!StringUtils.isBlank(order.getBilling().getState())) {
				billing.append(order.getBilling().getState()).append(LINE_BREAK);
				
			}
			Country country = countries.get(order.getBilling().getCountry().getIsoCode());
			if (country != null) {
				billing.append(country.getName()).append(" ");
			}
			billing.append(order.getBilling().getPostalCode());
			
			StringBuilder shipping = null;
			if (order.getDelivery() != null && !StringUtils.isBlank(order.getDelivery().getFirstName())) {
				shipping = new StringBuilder();
				if (StringUtils.isBlank(order.getDelivery().getCompany())) {
					shipping.append(order.getDelivery().getFirstName()).append(" ")
						.append(order.getDelivery().getLastName()).append(LINE_BREAK);
				} else {
					shipping.append(order.getDelivery().getCompany()).append(LINE_BREAK);
				}
				shipping.append(order.getDelivery().getAddress()).append(LINE_BREAK);
				shipping.append(order.getDelivery().getCity()).append(", ");
				if (order.getDelivery().getZone() != null) {
					var zone = zones.get(order.getDelivery().getZone().getCode());
					if (zone != null) {
						shipping.append(zone.getName());
					}
					shipping.append(LINE_BREAK);
				} else if (!StringUtils.isBlank(order.getDelivery().getState())) {
					shipping.append(order.getDelivery().getState()).append(LINE_BREAK);
				}
				Country deliveryCountry = countries.get(order.getDelivery().getCountry().getIsoCode());
				if (country!=null) {
					shipping.append(deliveryCountry.getName()).append(" ");
				}
				shipping.append(order.getDelivery().getPostalCode());
			}
			
			if (shipping == null && StringUtils.isNotBlank(order.getShippingModuleCode())) {
				shipping = billing;
			}
			StringBuilder orderTable = new StringBuilder();
			orderTable.append(TABLE);
			for (OrderProduct product : order.getOrderProducts()) {
				orderTable.append(TR)
					.append(TD)
						.append(product.getProductName())
						.append(" - ")
						.append(product.getSku())
						.append(CLOSING_TD)
					.append(TD)
						.append(messages.getMessage("label.quantity", null, locale))
						.append(": ")
						.append(product.getProductQuantity())
						.append(CLOSING_TD)
					.append(TD)
						.append(pricingService.getDisplayAmount(product.getOneTimeCharge(), store))
						.append(CLOSING_TD)
				.append(CLOSING_TR);
			}
			
			for (OrderTotal total : order.getOrderTotal()) {
				orderTable.append(TR_BORDER)
					.append(TD)
					.append(CLOSING_TD)
					.append(TD)
					.append("<strong>");
				if (total.getModule().equals("tax")) {
					orderTable.append(total.getText()).append(": ");
				} else {
					orderTable.append(messages.getMessage(total.getOrderTotalCode(), null, locale)).append(": ");
				}
				orderTable.append("</strong>");
				orderTable.append(CLOSING_TD);
				orderTable.append(TD);
				orderTable.append("<strong>");
				orderTable.append(pricingService.getDisplayAmount(total.getValue(), store));
				orderTable.append("</strong>");
				orderTable.append(CLOSING_TD);
				orderTable.append(CLOSING_TR);
			}
			orderTable.append(CLOSING_TABLE);
			var templateTokens = EmailUtils.createEmailObjectsMap(contextPath, store, messages, locale);
			templateTokens.put(EmailConstants.LABEL_HI, messages.getMessage("label.generic.hi", null, locale));
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_FIRSTNAME, order.getBilling().getFirstName());
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_LASTNAME, order.getBilling().getLastName());
			String[] params = {String.valueOf(order.getId())};
			String[] dt = {DateUtil.formatDate(order.getDatePurchased())};
			templateTokens.put(EmailConstants.EMAIL_ORDER_NUMBER, messages.getMessage("email.order.confirmation", params, locale));
			templateTokens.put(EmailConstants.EMAIL_ORDER_DATE, messages.getMessage("email.order.ordered", dt, locale));
			templateTokens.put(EmailConstants.EMAIL_ORDER_THANKS, messages.getMessage("email.order.thanks", null, locale));
			templateTokens.put(EmailConstants.ADDRESS_BILLING, billing.toString());
			
			templateTokens.put(EmailConstants.ORDER_PRODUCTS_DETAILS, orderTable.toString());
			templateTokens.put(EmailConstants.EMAIL_ORDER_DETAILS_TITLE, messages.getMessage("label.order.details", null, locale));
			templateTokens.put(EmailConstants.ADDRESS_BILLING_TITLE, messages.getMessage("label.customer.billinginformation", null, locale));
			templateTokens.put(EmailConstants.PAYMENT_METHOD_TITLE, messages.getMessage("label.order.paymentmode", null, locale));
			StringBuilder paymentDetails = new StringBuilder();
			paymentDetails.append("payment.type.")
				.append(order.getPaymentType().name());
			
			templateTokens.put(EmailConstants.PAYMENT_METHOD_DETAILS, messages.getMessage(paymentDetails.toString(), null, locale));
			if (StringUtils.isNotBlank(order.getShippingModuleCode())) {
				templateTokens.put(EmailConstants.SHIPPING_METHOD_DETAILS, messages.getMessage(new StringBuilder()
						.append("module.shipping.")
						.append(order.getShippingModuleCode()).toString(), new String[] {store.getStorename()}, locale));
				templateTokens.put(EmailConstants.ADDRESS_SHIPPING_TITLE, messages.getMessage("label.order.shippingmethod", null, locale));
				templateTokens.put(EmailConstants.ADDRESS_DELIVERY_TITLE, messages.getMessage("label.customer.shippinginformation", null, locale));
				templateTokens.put(EmailConstants.SHIPPING_METHOD_TITLE, messages.getMessage("label.customer.shippinginformation", null, locale));
				templateTokens.put(EmailConstants.ADDRESS_DELIVERY, shipping.toString());
			} else {
				templateTokens.put(EmailConstants.SHIPPING_METHOD_DETAILS, "");
				templateTokens.put(EmailConstants.ADDRESS_SHIPPING_TITLE, "");
				templateTokens.put(EmailConstants.ADDRESS_DELIVERY_TITLE, "");
				templateTokens.put(EmailConstants.SHIPPING_METHOD_TITLE, "");
				templateTokens.put(EmailConstants.ADDRESS_DELIVERY, "");
			}
			String status = messages.getMessage("label.order."+order.getStatus().name(), null, order.getStatus().name(), locale);
			String[] statusMessage = {DateUtil.formatDate(order.getDatePurchased()), status};
			templateTokens.put(EmailConstants.ORDER_STATUS, messages.getMessage("email.order.status", statusMessage, locale));
			
			String[] title = {store.getStorename(), String.valueOf(order.getId())};
			
			Email email = new Email();
			email.setFrom(store.getStorename());
			email.setFromEmail(store.getStoreEmailAddress());
			email.setSubject(messages.getMessage("email.order.title", title, locale));
			email.setTo(customer.getEmailAddress());
			email.setTemplateName(EmailConstants.EMAIL_CUSTOMER_TPL);
			email.setTemplateTokens(templateTokens);
			log.debug("Sending email to {} on their registered email id {} ", customer.getBilling().getFirstName(), customer.getEmailAddress());
			emailService.sendHtmlEmail(store, email);		
			
		} catch (Exception e) {
			log.error("Error occured while sending welcome email ", e);
		}
		
	}
	
	@Async
	public void sendOrderDownloadEmail(Customer customer, Order order, MerchantStore store, Locale locale,
			String contextPath) {
		log.info("Sending download email to customer");
		try {
			var templateTokens = EmailUtils.createEmailObjectsMap(contextPath, store, messages, locale);
			templateTokens.put(EmailConstants.LABEL_HI, messages.getMessage("label.generic.hi", null, locale));
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_FIRSTNAME, customer.getBilling().getFirstName());
			templateTokens.put(EmailConstants.EMAIL_CUSTOMER_LASTNAME, customer.getBilling().getLastName());
			String[] downloadMessage = {String.valueOf(ApplicationConstants.MAX_DOWNLOAD_DAYS), 
					String.valueOf(order.getId()), 
					FilePathUtils.buildCustomerUri(store, contextPath),
					store.getStoreEmailAddress()};
			templateTokens.put(EmailConstants.EMAIL_ORDER_DOWNLOAD, messages.getMessage("email.order.download.text", downloadMessage, locale));
			templateTokens.put(EmailConstants.CUSTOMER_ACCESS_LABEL, messages.getMessage("label.customer.accessportal", null, locale));
			templateTokens.put(EmailConstants.ACCESS_NOW_LABEL, messages.getMessage("label.customer.accessnow", null, locale));
			
			String customerUrl = FilePathUtils.buildStoreUri(store, contextPath);
			templateTokens.put(EmailConstants.CUSTOMER_ACCESS_URL, customerUrl);
			
			String[] orderInfo = {String.valueOf(order.getId())};
			
			Email email = new Email();
			email.setFrom(store.getStorename());
			email.setFromEmail(store.getStoreEmailAddress());
			email.setSubject(messages.getMessage("email.order.download.title", orderInfo, locale));
			email.setTo(customer.getEmailAddress());
			email.setTemplateName(EmailConstants.EMAIL_ORDER_DOWNLOAD_TPL);
			email.setTemplateTokens(templateTokens);
			log.debug("Sending email to {} with download info ", customer.getEmailAddress());
			emailService.sendHtmlEmail(store, email);
		} catch (Exception e) {
			log.error("Error occured while sending order download email ", e);
		}
		
	}

}
