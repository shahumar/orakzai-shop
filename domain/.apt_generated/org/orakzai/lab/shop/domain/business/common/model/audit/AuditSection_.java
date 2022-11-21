package org.orakzai.lab.shop.domain.business.common.model.audit;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuditSection.class)
public abstract class AuditSection_ {

	public static volatile SingularAttribute<AuditSection, LocalDateTime> dateCreated;
	public static volatile SingularAttribute<AuditSection, LocalDateTime> dateModified;
	public static volatile SingularAttribute<AuditSection, String> modifiedBy;

	public static final String DATE_CREATED = "dateCreated";
	public static final String DATE_MODIFIED = "dateModified";
	public static final String MODIFIED_BY = "modifiedBy";

}

