package org.orakzai.lab.shop.domain.business.order.model.orderaccount;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.order.model.Order;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderAccount.class)
public abstract class OrderAccount_ {

	public static volatile SetAttribute<OrderAccount, OrderAccountProduct> orderAccountProducts;
	public static volatile SingularAttribute<OrderAccount, Long> id;
	public static volatile SingularAttribute<OrderAccount, LocalDate> orderAccountStartDate;
	public static volatile SingularAttribute<OrderAccount, LocalDate> orderAccountEndDate;
	public static volatile SingularAttribute<OrderAccount, Integer> orderAccountBillDay;
	public static volatile SingularAttribute<OrderAccount, Order> order;

	public static final String ORDER_ACCOUNT_PRODUCTS = "orderAccountProducts";
	public static final String ID = "id";
	public static final String ORDER_ACCOUNT_START_DATE = "orderAccountStartDate";
	public static final String ORDER_ACCOUNT_END_DATE = "orderAccountEndDate";
	public static final String ORDER_ACCOUNT_BILL_DAY = "orderAccountBillDay";
	public static final String ORDER = "order";

}

