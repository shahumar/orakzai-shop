package org.orakzai.lab.shop.domain.business.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemConfiguration.class)
public abstract class SystemConfiguration_ {

	public static volatile SingularAttribute<SystemConfiguration, Long> id;
	public static volatile SingularAttribute<SystemConfiguration, String> value;
	public static volatile SingularAttribute<SystemConfiguration, AuditSection> auditSection;
	public static volatile SingularAttribute<SystemConfiguration, String> key;

	public static final String ID = "id";
	public static final String VALUE = "value";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String KEY = "key";

}

