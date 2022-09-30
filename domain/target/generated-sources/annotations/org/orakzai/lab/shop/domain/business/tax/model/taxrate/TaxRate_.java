package org.orakzai.lab.shop.domain.business.tax.model.taxrate;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TaxRate.class)
public abstract class TaxRate_ {

	public static volatile SingularAttribute<TaxRate, TaxClass> taxClass;
	public static volatile SingularAttribute<TaxRate, Country> country;
	public static volatile SingularAttribute<TaxRate, TaxRate> parent;
	public static volatile SingularAttribute<TaxRate, String> code;
	public static volatile SingularAttribute<TaxRate, Integer> taxPriority;
	public static volatile SingularAttribute<TaxRate, String> stateProvince;
	public static volatile SingularAttribute<TaxRate, AuditSection> auditSection;
	public static volatile ListAttribute<TaxRate, TaxRateDescription> descriptions;
	public static volatile SingularAttribute<TaxRate, Boolean> piggyback;
	public static volatile SingularAttribute<TaxRate, BigDecimal> taxRate;
	public static volatile ListAttribute<TaxRate, TaxRate> taxRates;
	public static volatile SingularAttribute<TaxRate, Zone> zone;
	public static volatile SingularAttribute<TaxRate, Long> id;
	public static volatile SingularAttribute<TaxRate, MerchantStore> merchantStore;

	public static final String TAX_CLASS = "taxClass";
	public static final String COUNTRY = "country";
	public static final String PARENT = "parent";
	public static final String CODE = "code";
	public static final String TAX_PRIORITY = "taxPriority";
	public static final String STATE_PROVINCE = "stateProvince";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String PIGGYBACK = "piggyback";
	public static final String TAX_RATE = "taxRate";
	public static final String TAX_RATES = "taxRates";
	public static final String ZONE = "zone";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";

}

