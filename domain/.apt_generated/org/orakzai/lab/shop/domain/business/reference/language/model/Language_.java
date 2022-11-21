package org.orakzai.lab.shop.domain.business.reference.language.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Language.class)
public abstract class Language_ {

	public static volatile SingularAttribute<Language, String> code;
	public static volatile ListAttribute<Language, MerchantStore> stores;
	public static volatile SingularAttribute<Language, Integer> sortOrder;
	public static volatile SingularAttribute<Language, Integer> id;
	public static volatile ListAttribute<Language, MerchantStore> storesDefaultLanguage;
	public static volatile SingularAttribute<Language, AuditSection> auditSection;

	public static final String CODE = "code";
	public static final String STORES = "stores";
	public static final String SORT_ORDER = "sortOrder";
	public static final String ID = "id";
	public static final String STORES_DEFAULT_LANGUAGE = "storesDefaultLanguage";
	public static final String AUDIT_SECTION = "auditSection";

}

