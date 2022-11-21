package org.orakzai.lab.shop.domain.business.content.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Content.class)
public abstract class Content_ {

	public static volatile SingularAttribute<Content, String> code;
	public static volatile SingularAttribute<Content, Boolean> visible;
	public static volatile SingularAttribute<Content, Integer> sortOrder;
	public static volatile SingularAttribute<Content, ContentPosition> contentPosition;
	public static volatile SingularAttribute<Content, Long> id;
	public static volatile SingularAttribute<Content, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Content, AuditSection> auditSection;
	public static volatile ListAttribute<Content, ContentDescription> descriptions;
	public static volatile SingularAttribute<Content, ContentType> contentType;

	public static final String CODE = "code";
	public static final String VISIBLE = "visible";
	public static final String SORT_ORDER = "sortOrder";
	public static final String CONTENT_POSITION = "contentPosition";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String CONTENT_TYPE = "contentType";

}

