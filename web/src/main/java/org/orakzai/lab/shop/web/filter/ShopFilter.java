package org.orakzai.lab.shop.web.filter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.merchant.service.MerchantStoreService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.customer.Address;
import org.orakzai.lab.shop.web.dto.customer.AnonymousCustomer;
import org.orakzai.lab.shop.web.utils.GeoLocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ShopFilter implements HandlerInterceptor {

	private final static String STORE_REQUEST_PARAMETER = "store";
	private final static String SERVICES_URL_PATTERN = "/services";
	private final static String REFERENCE_URL_PATTERN = "/reference";

	@Autowired
	MerchantStoreService merchantService;

	@Autowired
	LanguageService languageService;
	
	@Autowired
	private CustomerService customerService;

	public ShopFilter() {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");

		if (request.getRequestURL().toString().toLowerCase().contains(SERVICES_URL_PATTERN)
				|| request.getRequestURL().toString().toLowerCase().contains(REFERENCE_URL_PATTERN)) {
			return true;
		}

		String currentPath = System.getProperty("user.dir");
		try {
			MerchantStore store = (MerchantStore) request.getSession().getAttribute(Constants.MERCHANT_STORE);
			String storeCode = request.getParameter(STORE_REQUEST_PARAMETER);
			if (!StringUtils.isBlank(storeCode)) {
				if (store != null) {
					if (!store.getCode().equals(storeCode)) {
						store = setMerchantStoreInSession(request, storeCode);
					}
				} else {
					store = setMerchantStoreInSession(request, storeCode);
				}

			}
			if (store == null) {
				store = setMerchantStoreInSession(request, MerchantStore.DEFAULT_STORE);

			}
			request.setAttribute(Constants.MERCHANT_STORE, store);
			// set language and locale in session
			
			Customer customer = (Customer) request.getSession().getAttribute(Constants.CUSTOMER);
			if (customer != null) {
				if (customer.getMerchantStore().getId().intValue() != store.getId().intValue()) {
					request.getSession().removeAttribute(Constants.CUSTOMER	);
				}
				request.setAttribute(Constants.CUSTOMER, customer);
			}
			
			if (customer == null) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
					System.out.println(auth.getName());
					customer = customerService.getByNick(auth.getName());
					if (customer != null) {
						request.setAttribute(Constants.CUSTOMER, customer);
					}
				}
			}
			
			AnonymousCustomer anonymousCustomer = (AnonymousCustomer) request.getSession().getAttribute(Constants.ANONYMOUS_CUSTOMER);
			
			if (anonymousCustomer == null) {
				Address address = null;
				try {
					String ipAddress = GeoLocationUtils.getClientIpAddress(request);
					var geoAddress = customerService.getCustomerAddress(store, ipAddress);
					if (geoAddress != null) {
						address = new Address();
						address.setCountry(geoAddress.getCountry());
						address.setCity(geoAddress.getCity());
						address.setZone(geoAddress.getZone());
						address.setPostalCode(geoAddress.getPostalCode());
					}
				} catch (Exception e) {
					log.error("Cannot get geo ip component", e);
				}
				if (address == null) {
					address = new Address();
					address.setCountry(store.getCountry().getIsoCode());
					if (store.getZone() != null) {
						address.setZone(store.getZone().getCode());
					} else {
						address.setStateProvince(store.getStorestateprovince());
					}
					address.setPostalCode(store.getStorepostalcode());
					
				}
				anonymousCustomer = new AnonymousCustomer();
				anonymousCustomer.setBilling(address);
				request.getSession().setAttribute(Constants.ANONYMOUS_CUSTOMER, anonymousCustomer);
			} else {
				request.setAttribute(Constants.ANONYMOUS_CUSTOMER, anonymousCustomer);
			}

			Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE);
			if (language == null) {
				language = languageService.getByCode(Constants.DEFAULT_LANGUAGE);
				request.getSession().setAttribute(Constants.LANGUAGE, language);
			}
			request.setAttribute(Constants.LANGUAGE, language);

			Locale locale = request.getLocale();

		} catch (Exception e) {
			log.error("Error in StoreFilter", e);
		}

		return true;
	}

	private MerchantStore setMerchantStoreInSession(HttpServletRequest request, String storeCode) throws Exception {
		log.debug("Setting Merchantstore ", storeCode);
		log.debug("Request object ", request);
		if (storeCode == null || request == null) {
			return null;
		}
		MerchantStore store = merchantService.getByCode(storeCode);
		log.debug("Merchant store by code", store);
		if (store != null) {
			request.getSession().setAttribute(Constants.MERCHANT_STORE, store);
		}

		return store;
	}
}
