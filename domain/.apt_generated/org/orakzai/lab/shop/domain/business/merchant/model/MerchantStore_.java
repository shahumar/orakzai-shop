package org.orakzai.lab.shop.domain.business.merchant.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MerchantStore.class)
public abstract class MerchantStore_ {

	public static volatile SingularAttribute<MerchantStore, Country> country;
	public static volatile SingularAttribute<MerchantStore, String> code;
	public static volatile SingularAttribute<MerchantStore, String> storestateprovince;
	public static volatile ListAttribute<MerchantStore, Language> languages;
	public static volatile SingularAttribute<MerchantStore, String> continueshoppingurl;
	public static volatile SingularAttribute<MerchantStore, String> seizeunitcode;
	public static volatile SingularAttribute<MerchantStore, String> storename;
	public static volatile SingularAttribute<MerchantStore, String> storecity;
	public static volatile SingularAttribute<MerchantStore, String> storeEmailAddress;
	public static volatile SingularAttribute<MerchantStore, String> storephone;
	public static volatile SingularAttribute<MerchantStore, String> weightunitcode;
	public static volatile SingularAttribute<MerchantStore, Language> defaultLanguage;
	public static volatile SingularAttribute<MerchantStore, Zone> zone;
	public static volatile SingularAttribute<MerchantStore, Boolean> useCache;
	public static volatile SingularAttribute<MerchantStore, String> storeTemplate;
	public static volatile SingularAttribute<MerchantStore, String> domainName;
	public static volatile SingularAttribute<MerchantStore, String> invoiceTemplate;
	public static volatile SingularAttribute<MerchantStore, String> storeLogo;
	public static volatile SingularAttribute<MerchantStore, Currency> currency;
	public static volatile SingularAttribute<MerchantStore, Integer> id;
	public static volatile SingularAttribute<MerchantStore, Boolean> currencyFormatNational;
	public static volatile SingularAttribute<MerchantStore, String> storeaddress;
	public static volatile SingularAttribute<MerchantStore, String> storepostalcode;
	public static volatile SingularAttribute<MerchantStore, LocalDate> inBusinessSince;

	public static final String COUNTRY = "country";
	public static final String CODE = "code";
	public static final String STORESTATEPROVINCE = "storestateprovince";
	public static final String LANGUAGES = "languages";
	public static final String CONTINUESHOPPINGURL = "continueshoppingurl";
	public static final String SEIZEUNITCODE = "seizeunitcode";
	public static final String STORENAME = "storename";
	public static final String STORECITY = "storecity";
	public static final String STORE_EMAIL_ADDRESS = "storeEmailAddress";
	public static final String STOREPHONE = "storephone";
	public static final String WEIGHTUNITCODE = "weightunitcode";
	public static final String DEFAULT_LANGUAGE = "defaultLanguage";
	public static final String ZONE = "zone";
	public static final String USE_CACHE = "useCache";
	public static final String STORE_TEMPLATE = "storeTemplate";
	public static final String DOMAIN_NAME = "domainName";
	public static final String INVOICE_TEMPLATE = "invoiceTemplate";
	public static final String STORE_LOGO = "storeLogo";
	public static final String CURRENCY = "currency";
	public static final String ID = "id";
	public static final String CURRENCY_FORMAT_NATIONAL = "currencyFormatNational";
	public static final String STOREADDRESS = "storeaddress";
	public static final String STOREPOSTALCODE = "storepostalcode";
	public static final String IN_BUSINESS_SINCE = "inBusinessSince";

}

