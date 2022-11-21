package org.orakzai.lab.shop.domain.business.user.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Group.class)
public abstract class Group_ {

	public static volatile SingularAttribute<Group, GroupType> groupType;
	public static volatile SingularAttribute<Group, String> groupName;
	public static volatile SetAttribute<Group, Permission> permissions;
	public static volatile SingularAttribute<Group, Integer> id;
	public static volatile SingularAttribute<Group, AuditSection> auditSection;

	public static final String GROUP_TYPE = "groupType";
	public static final String GROUP_NAME = "groupName";
	public static final String PERMISSIONS = "permissions";
	public static final String ID = "id";
	public static final String AUDIT_SECTION = "auditSection";

}

