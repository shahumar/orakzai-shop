package org.orakzai.lab.shop.domain.business.common.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Description.class)
public abstract class Description_ {

	public static volatile SingularAttribute<Description, String> name;
	public static volatile SingularAttribute<Description, String> description;
	public static volatile SingularAttribute<Description, Language> language;
	public static volatile SingularAttribute<Description, Long> id;
	public static volatile SingularAttribute<Description, String> title;
	public static volatile SingularAttribute<Description, AuditSection> auditSection;

	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String AUDIT_SECTION = "auditSection";

}

