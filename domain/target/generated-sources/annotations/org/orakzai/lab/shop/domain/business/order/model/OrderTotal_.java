package org.orakzai.lab.shop.domain.business.order.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderTotal.class)
public abstract class OrderTotal_ {

	public static volatile SingularAttribute<OrderTotal, OrderTotalType> orderTotalType;
	public static volatile SingularAttribute<OrderTotal, String> module;
	public static volatile SingularAttribute<OrderTotal, Integer> sortOrder;
	public static volatile SingularAttribute<OrderTotal, OrderValueType> orderValueType;
	public static volatile SingularAttribute<OrderTotal, Long> id;
	public static volatile SingularAttribute<OrderTotal, String> orderTotalCode;
	public static volatile SingularAttribute<OrderTotal, String> text;
	public static volatile SingularAttribute<OrderTotal, String> title;
	public static volatile SingularAttribute<OrderTotal, BigDecimal> value;
	public static volatile SingularAttribute<OrderTotal, Order> order;

	public static final String ORDER_TOTAL_TYPE = "orderTotalType";
	public static final String MODULE = "module";
	public static final String SORT_ORDER = "sortOrder";
	public static final String ORDER_VALUE_TYPE = "orderValueType";
	public static final String ID = "id";
	public static final String ORDER_TOTAL_CODE = "orderTotalCode";
	public static final String TEXT = "text";
	public static final String TITLE = "title";
	public static final String VALUE = "value";
	public static final String ORDER = "order";

}

