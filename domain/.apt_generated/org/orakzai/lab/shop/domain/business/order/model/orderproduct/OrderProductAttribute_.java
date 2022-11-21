package org.orakzai.lab.shop.domain.business.order.model.orderproduct;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProductAttribute.class)
public abstract class OrderProductAttribute_ {

	public static volatile SingularAttribute<OrderProductAttribute, Long> productOptionValueId;
	public static volatile SingularAttribute<OrderProductAttribute, BigDecimal> productAttributeWeight;
	public static volatile SingularAttribute<OrderProductAttribute, String> productAttributeName;
	public static volatile SingularAttribute<OrderProductAttribute, String> productAttributeValueName;
	public static volatile SingularAttribute<OrderProductAttribute, Long> productOptionId;
	public static volatile SingularAttribute<OrderProductAttribute, Boolean> productAttributeIsFree;
	public static volatile SingularAttribute<OrderProductAttribute, BigDecimal> productAttributePrice;
	public static volatile SingularAttribute<OrderProductAttribute, Long> id;
	public static volatile SingularAttribute<OrderProductAttribute, OrderProduct> orderProduct;

	public static final String PRODUCT_OPTION_VALUE_ID = "productOptionValueId";
	public static final String PRODUCT_ATTRIBUTE_WEIGHT = "productAttributeWeight";
	public static final String PRODUCT_ATTRIBUTE_NAME = "productAttributeName";
	public static final String PRODUCT_ATTRIBUTE_VALUE_NAME = "productAttributeValueName";
	public static final String PRODUCT_OPTION_ID = "productOptionId";
	public static final String PRODUCT_ATTRIBUTE_IS_FREE = "productAttributeIsFree";
	public static final String PRODUCT_ATTRIBUTE_PRICE = "productAttributePrice";
	public static final String ID = "id";
	public static final String ORDER_PRODUCT = "orderProduct";

}

