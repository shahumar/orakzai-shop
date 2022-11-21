package org.orakzai.lab.shop.domain.business.order.model.orderaccount;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrderAccountProduct.class)
public abstract class OrderAccountProduct_ {

	public static volatile SingularAttribute<OrderAccountProduct, Long> orderAccountProductId;
	public static volatile SingularAttribute<OrderAccountProduct, Integer> orderAccountProductLastTransactionStatus;
	public static volatile SingularAttribute<OrderAccountProduct, LocalDate> orderAccountProductEndDate;
	public static volatile SingularAttribute<OrderAccountProduct, LocalDate> orderAccountProductStartDate;
	public static volatile SingularAttribute<OrderAccountProduct, LocalDate> orderAccountProductLastStatusDate;
	public static volatile SingularAttribute<OrderAccountProduct, OrderProduct> orderProduct;
	public static volatile SingularAttribute<OrderAccountProduct, Integer> orderAccountProductStatus;
	public static volatile SingularAttribute<OrderAccountProduct, LocalDate> orderAccountProductAccountedDate;
	public static volatile SingularAttribute<OrderAccountProduct, Integer> orderAccountProductPaymentFrequencyType;
	public static volatile SingularAttribute<OrderAccountProduct, OrderAccount> orderAccount;
	public static volatile SingularAttribute<OrderAccountProduct, LocalDate> orderAccountProductEot;

	public static final String ORDER_ACCOUNT_PRODUCT_ID = "orderAccountProductId";
	public static final String ORDER_ACCOUNT_PRODUCT_LAST_TRANSACTION_STATUS = "orderAccountProductLastTransactionStatus";
	public static final String ORDER_ACCOUNT_PRODUCT_END_DATE = "orderAccountProductEndDate";
	public static final String ORDER_ACCOUNT_PRODUCT_START_DATE = "orderAccountProductStartDate";
	public static final String ORDER_ACCOUNT_PRODUCT_LAST_STATUS_DATE = "orderAccountProductLastStatusDate";
	public static final String ORDER_PRODUCT = "orderProduct";
	public static final String ORDER_ACCOUNT_PRODUCT_STATUS = "orderAccountProductStatus";
	public static final String ORDER_ACCOUNT_PRODUCT_ACCOUNTED_DATE = "orderAccountProductAccountedDate";
	public static final String ORDER_ACCOUNT_PRODUCT_PAYMENT_FREQUENCY_TYPE = "orderAccountProductPaymentFrequencyType";
	public static final String ORDER_ACCOUNT = "orderAccount";
	public static final String ORDER_ACCOUNT_PRODUCT_EOT = "orderAccountProductEot";

}

