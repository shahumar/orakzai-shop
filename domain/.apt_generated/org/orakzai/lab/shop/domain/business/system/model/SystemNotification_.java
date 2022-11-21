package org.orakzai.lab.shop.domain.business.system.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.user.model.User;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemNotification.class)
public abstract class SystemNotification_ {

	public static volatile SingularAttribute<SystemNotification, LocalDate> endDate;
	public static volatile SingularAttribute<SystemNotification, Long> id;
	public static volatile SingularAttribute<SystemNotification, MerchantStore> merchantStore;
	public static volatile SingularAttribute<SystemNotification, String> value;
	public static volatile SingularAttribute<SystemNotification, User> user;
	public static volatile SingularAttribute<SystemNotification, AuditSection> auditSection;
	public static volatile SingularAttribute<SystemNotification, String> key;
	public static volatile SingularAttribute<SystemNotification, LocalDate> startDate;

	public static final String END_DATE = "endDate";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String VALUE = "value";
	public static final String USER = "user";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String KEY = "key";
	public static final String START_DATE = "startDate";

}

