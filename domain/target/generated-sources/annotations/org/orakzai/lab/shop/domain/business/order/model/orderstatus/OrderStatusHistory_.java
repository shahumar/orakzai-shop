package org.orakzai.lab.shop.domain.business.order.model.orderstatus;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.order.model.Order;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderStatusHistory.class)
public abstract class OrderStatusHistory_ {

	public static volatile SingularAttribute<OrderStatusHistory, String> comments;
	public static volatile SingularAttribute<OrderStatusHistory, Integer> customerNotified;
	public static volatile SingularAttribute<OrderStatusHistory, Long> id;
	public static volatile SingularAttribute<OrderStatusHistory, LocalDate> dateAdded;
	public static volatile SingularAttribute<OrderStatusHistory, Order> order;
	public static volatile SingularAttribute<OrderStatusHistory, OrderStatus> status;

	public static final String COMMENTS = "comments";
	public static final String CUSTOMER_NOTIFIED = "customerNotified";
	public static final String ID = "id";
	public static final String DATE_ADDED = "dateAdded";
	public static final String ORDER = "order";
	public static final String STATUS = "status";

}

