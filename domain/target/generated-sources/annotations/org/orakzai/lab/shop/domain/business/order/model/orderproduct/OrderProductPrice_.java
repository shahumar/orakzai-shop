package org.orakzai.lab.shop.domain.business.order.model.orderproduct;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProductPrice.class)
public abstract class OrderProductPrice_ {

	public static volatile SingularAttribute<OrderProductPrice, Boolean> defaultPrice;
	public static volatile SingularAttribute<OrderProductPrice, BigDecimal> productPriceSpecial;
	public static volatile SingularAttribute<OrderProductPrice, LocalDate> productPriceSpecialEndDate;
	public static volatile SingularAttribute<OrderProductPrice, String> productPriceName;
	public static volatile SingularAttribute<OrderProductPrice, String> productPriceCode;
	public static volatile SingularAttribute<OrderProductPrice, LocalDate> productPriceSpecialStartDate;
	public static volatile SingularAttribute<OrderProductPrice, Long> id;
	public static volatile SingularAttribute<OrderProductPrice, OrderProduct> orderProduct;
	public static volatile SingularAttribute<OrderProductPrice, BigDecimal> productPrice;

	public static final String DEFAULT_PRICE = "defaultPrice";
	public static final String PRODUCT_PRICE_SPECIAL = "productPriceSpecial";
	public static final String PRODUCT_PRICE_SPECIAL_END_DATE = "productPriceSpecialEndDate";
	public static final String PRODUCT_PRICE_NAME = "productPriceName";
	public static final String PRODUCT_PRICE_CODE = "productPriceCode";
	public static final String PRODUCT_PRICE_SPECIAL_START_DATE = "productPriceSpecialStartDate";
	public static final String ID = "id";
	public static final String ORDER_PRODUCT = "orderProduct";
	public static final String PRODUCT_PRICE = "productPrice";

}

