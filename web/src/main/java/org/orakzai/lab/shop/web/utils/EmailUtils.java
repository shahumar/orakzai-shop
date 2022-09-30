package org.orakzai.lab.shop.web.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.web.constants.Constants;
import org.springframework.context.MessageSource;

public class EmailUtils {

	private static final String EMAIL_ADMIN_LABEL = "EMAIL_ADMIN_LABEL";
	private static final String EMAIL_STORE_NAME = "EMAIL_STORE_NAME";
	private static final String EMAIL_FOOTER_COPYRIGHT = "EMAIL_FOOTER_COPYRIGHT";
	private static final String EMAIL_DISCLAIMER = "EMAIL_DISCLAIMER";
	private static final String EMAIL_SPAM_DISCLAIMER = "EMAIL_SPAM_DISCLAIMER";
	private static final String LOGOPATH = "LOGOPATH";

	public static Map<String, String> createEmailObjectsMap(String contextPath, @Valid MerchantStore store, MessageSource messages,
			Locale storeLocale) {
		var templateTokens = new HashMap<String, String>();
		String[] adminNameArg = {store.getStorename()};
		String[] adminEmailArg = {store.getStoreEmailAddress()};
		String[] copyArg = {store.getStorename(), DateUtil.getPresentYear()};
		templateTokens.put(EMAIL_ADMIN_LABEL, messages.getMessage("email.message.from", adminNameArg, storeLocale));
		templateTokens.put(EMAIL_STORE_NAME, store.getStorename());
		templateTokens.put(EMAIL_FOOTER_COPYRIGHT, messages.getMessage("email.copyright", copyArg, storeLocale));
		templateTokens.put(EMAIL_DISCLAIMER, messages.getMessage("email.disclaimer", adminEmailArg, storeLocale));
		templateTokens.put(EMAIL_SPAM_DISCLAIMER, messages.getMessage("email.spam.disclaimer", null, storeLocale));
		if (store.getStoreLogo() != null) {
			StringBuilder logoPath = new StringBuilder();
			String scheme = Constants.HTTP_SCHEME;
			logoPath
				.append("<img src='")
				.append(scheme)
				.append("://")
				.append(store.getDomainName())
				.append(contextPath)
				.append("/")
				.append(ImageFilePathUtils.buildStoreLogoFilePath(store))
				.append("' style='max-width:400px;'>");
			templateTokens.put(LOGOPATH, logoPath.toString());
		} else {
			templateTokens.put(LOGOPATH, store.getStorename());
		}
		
		return templateTokens;
	}

}
