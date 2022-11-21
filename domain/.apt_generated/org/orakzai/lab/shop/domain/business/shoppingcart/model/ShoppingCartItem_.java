package org.orakzai.lab.shop.domain.business.shoppingcart.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShoppingCartItem.class)
public abstract class ShoppingCartItem_ {

	public static volatile SingularAttribute<ShoppingCartItem, Integer> quantity;
	public static volatile SingularAttribute<ShoppingCartItem, Long> productId;
	public static volatile SingularAttribute<ShoppingCartItem, ShoppingCart> shoppingCart;
	public static volatile SetAttribute<ShoppingCartItem, ShoppingCartAttributeItem> attributes;
	public static volatile SingularAttribute<ShoppingCartItem, Long> id;
	public static volatile SingularAttribute<ShoppingCartItem, AuditSection> auditSection;

	public static final String QUANTITY = "quantity";
	public static final String PRODUCT_ID = "productId";
	public static final String SHOPPING_CART = "shoppingCart";
	public static final String ATTRIBUTES = "attributes";
	public static final String ID = "id";
	public static final String AUDIT_SECTION = "auditSection";

}

