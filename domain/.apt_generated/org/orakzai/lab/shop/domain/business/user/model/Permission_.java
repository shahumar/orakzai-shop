package org.orakzai.lab.shop.domain.business.user.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile ListAttribute<Permission, Group> groups;
	public static volatile SingularAttribute<Permission, Integer> id;
	public static volatile SingularAttribute<Permission, AuditSection> auditSection;
	public static volatile SingularAttribute<Permission, String> permissionName;

	public static final String GROUPS = "groups";
	public static final String ID = "id";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String PERMISSION_NAME = "permissionName";

}

