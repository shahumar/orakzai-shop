package org.orakzai.lab.shop.domain.business.shoppingcart.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShoppingCart.class)
public abstract class ShoppingCart_ {

	public static volatile SetAttribute<ShoppingCart, ShoppingCartItem> lineItems;
	public static volatile SingularAttribute<ShoppingCart, Long> customerId;
	public static volatile SingularAttribute<ShoppingCart, Long> id;
	public static volatile SingularAttribute<ShoppingCart, MerchantStore> merchantStore;
	public static volatile SingularAttribute<ShoppingCart, AuditSection> auditSection;
	public static volatile SingularAttribute<ShoppingCart, String> shoppingCartCode;

	public static final String LINE_ITEMS = "lineItems";
	public static final String CUSTOMER_ID = "customerId";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String SHOPPING_CART_CODE = "shoppingCartCode";

}

