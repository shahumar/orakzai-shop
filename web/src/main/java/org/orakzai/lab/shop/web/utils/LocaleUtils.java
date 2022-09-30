package org.orakzai.lab.shop.web.utils;

import java.util.Locale;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.constants.Constants;

public class LocaleUtils {

	public static Locale getLocale(MerchantStore store) {
		Locale defaultLocale = Constants.DEFAULT_LOCALE;
		Locale[] locales = Locale.getAvailableLocales();
		for (int i=0; i<locales.length; i++) {
			Locale l = locales[i];
//			if (l.getISO3Country().equals(store.getCurrency().getCode())) {
//				defaultLocale = l;
//				break;
//			}
		}
		
		return defaultLocale;
	}

	public static Locale getLocale(Language defaultLanguage) {
		return new Locale(defaultLanguage.getCode());
	}

}
