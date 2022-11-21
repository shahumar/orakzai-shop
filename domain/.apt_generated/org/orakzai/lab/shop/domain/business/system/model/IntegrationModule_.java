package org.orakzai.lab.shop.domain.business.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IntegrationModule.class)
public abstract class IntegrationModule_ {

	public static volatile SingularAttribute<IntegrationModule, String> image;
	public static volatile SingularAttribute<IntegrationModule, String> code;
	public static volatile SingularAttribute<IntegrationModule, String> regions;
	public static volatile SingularAttribute<IntegrationModule, String> configuration;
	public static volatile SingularAttribute<IntegrationModule, String> configDetails;
	public static volatile SingularAttribute<IntegrationModule, String> module;
	public static volatile SingularAttribute<IntegrationModule, Long> id;
	public static volatile SingularAttribute<IntegrationModule, String> type;
	public static volatile SingularAttribute<IntegrationModule, AuditSection> auditSection;
	public static volatile SingularAttribute<IntegrationModule, Boolean> customModule;

	public static final String IMAGE = "image";
	public static final String CODE = "code";
	public static final String REGIONS = "regions";
	public static final String CONFIGURATION = "configuration";
	public static final String CONFIG_DETAILS = "configDetails";
	public static final String MODULE = "module";
	public static final String ID = "id";
	public static final String TYPE = "type";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String CUSTOM_MODULE = "customModule";

}

