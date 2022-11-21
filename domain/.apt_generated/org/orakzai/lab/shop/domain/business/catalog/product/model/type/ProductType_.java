package org.orakzai.lab.shop.domain.business.catalog.product.model.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductType.class)
public abstract class ProductType_ {

	public static volatile SingularAttribute<ProductType, String> code;
	public static volatile SingularAttribute<ProductType, Boolean> allowAddToCart;
	public static volatile SingularAttribute<ProductType, Long> id;
	public static volatile SingularAttribute<ProductType, AuditSection> auditSection;

	public static final String CODE = "code";
	public static final String ALLOW_ADD_TO_CART = "allowAddToCart";
	public static final String ID = "id";
	public static final String AUDIT_SECTION = "auditSection";

}

