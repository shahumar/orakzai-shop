package org.orakzai.lab.shop.domain.business.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MerchantConfiguration.class)
public abstract class MerchantConfiguration_ {

	public static volatile SingularAttribute<MerchantConfiguration, MerchantConfigurationType> merchantConfigurationType;
	public static volatile SingularAttribute<MerchantConfiguration, Long> id;
	public static volatile SingularAttribute<MerchantConfiguration, MerchantStore> merchantStore;
	public static volatile SingularAttribute<MerchantConfiguration, AuditSection> auditSection;
	public static volatile SingularAttribute<MerchantConfiguration, String> value;
	public static volatile SingularAttribute<MerchantConfiguration, String> key;

	public static final String MERCHANT_CONFIGURATION_TYPE = "merchantConfigurationType";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String VALUE = "value";
	public static final String KEY = "key";

}

