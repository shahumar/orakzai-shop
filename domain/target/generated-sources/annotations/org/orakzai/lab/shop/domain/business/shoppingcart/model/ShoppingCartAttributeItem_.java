package org.orakzai.lab.shop.domain.business.shoppingcart.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShoppingCartAttributeItem.class)
public abstract class ShoppingCartAttributeItem_ {

	public static volatile SingularAttribute<ShoppingCartAttributeItem, ShoppingCartItem> shoppingCartItem;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, Long> id;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, AuditSection> auditSection;
	public static volatile SingularAttribute<ShoppingCartAttributeItem, Long> productAttributeId;

	public static final String SHOPPING_CART_ITEM = "shoppingCartItem";
	public static final String ID = "id";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String PRODUCT_ATTRIBUTE_ID = "productAttributeId";

}

