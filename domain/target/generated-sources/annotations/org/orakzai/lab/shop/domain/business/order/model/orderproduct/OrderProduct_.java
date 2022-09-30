package org.orakzai.lab.shop.domain.business.order.model.orderproduct;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.order.model.Order;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderProduct.class)
public abstract class OrderProduct_ {

	public static volatile SingularAttribute<OrderProduct, Integer> productQuantity;
	public static volatile SetAttribute<OrderProduct, OrderProductDownload> downloads;
	public static volatile SetAttribute<OrderProduct, OrderProductAttribute> orderAttributes;
	public static volatile SingularAttribute<OrderProduct, Long> id;
	public static volatile SingularAttribute<OrderProduct, String> sku;
	public static volatile SingularAttribute<OrderProduct, BigDecimal> oneTimeCharge;
	public static volatile SetAttribute<OrderProduct, OrderProductPrice> prices;
	public static volatile SingularAttribute<OrderProduct, String> productName;
	public static volatile SingularAttribute<OrderProduct, Order> order;

	public static final String PRODUCT_QUANTITY = "productQuantity";
	public static final String DOWNLOADS = "downloads";
	public static final String ORDER_ATTRIBUTES = "orderAttributes";
	public static final String ID = "id";
	public static final String SKU = "sku";
	public static final String ONE_TIME_CHARGE = "oneTimeCharge";
	public static final String PRICES = "prices";
	public static final String PRODUCT_NAME = "productName";
	public static final String ORDER = "order";

}

