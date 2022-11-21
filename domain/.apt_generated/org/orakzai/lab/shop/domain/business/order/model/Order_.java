package org.orakzai.lab.shop.domain.business.order.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.Billing;
import org.orakzai.lab.shop.domain.business.common.model.Delivery;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatus;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatusHistory;
import org.orakzai.lab.shop.domain.business.order.model.payment.CreditCard;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentType;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, OrderType> orderType;
	public static volatile SingularAttribute<Order, Delivery> delivery;
	public static volatile SingularAttribute<Order, String> customerEmailAddress;
	public static volatile SingularAttribute<Order, String> ipAddress;
	public static volatile SingularAttribute<Order, OrderChannel> channel;
	public static volatile SetAttribute<Order, OrderProduct> orderProducts;
	public static volatile SingularAttribute<Order, MerchantStore> merchant;
	public static volatile SetAttribute<Order, OrderStatusHistory> orderHistory;
	public static volatile SingularAttribute<Order, Locale> locale;
	public static volatile SetAttribute<Order, OrderTotal> orderTotal;
	public static volatile SingularAttribute<Order, PaymentType> paymentType;
	public static volatile SingularAttribute<Order, String> shippingModuleCode;
	public static volatile SingularAttribute<Order, Billing> billing;
	public static volatile SingularAttribute<Order, String> paymentModuleCode;
	public static volatile SingularAttribute<Order, LocalDate> orderDateFinished;
	public static volatile SingularAttribute<Order, BigDecimal> total;
	public static volatile SingularAttribute<Order, BigDecimal> currencyValue;
	public static volatile SingularAttribute<Order, LocalDate> datePurchased;
	public static volatile SingularAttribute<Order, Long> customerId;
	public static volatile SingularAttribute<Order, Currency> currency;
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, LocalDate> lastModified;
	public static volatile SingularAttribute<Order, CreditCard> creditCard;
	public static volatile SingularAttribute<Order, OrderStatus> status;

	public static final String ORDER_TYPE = "orderType";
	public static final String DELIVERY = "delivery";
	public static final String CUSTOMER_EMAIL_ADDRESS = "customerEmailAddress";
	public static final String IP_ADDRESS = "ipAddress";
	public static final String CHANNEL = "channel";
	public static final String ORDER_PRODUCTS = "orderProducts";
	public static final String MERCHANT = "merchant";
	public static final String ORDER_HISTORY = "orderHistory";
	public static final String LOCALE = "locale";
	public static final String ORDER_TOTAL = "orderTotal";
	public static final String PAYMENT_TYPE = "paymentType";
	public static final String SHIPPING_MODULE_CODE = "shippingModuleCode";
	public static final String BILLING = "billing";
	public static final String PAYMENT_MODULE_CODE = "paymentModuleCode";
	public static final String ORDER_DATE_FINISHED = "orderDateFinished";
	public static final String TOTAL = "total";
	public static final String CURRENCY_VALUE = "currencyValue";
	public static final String DATE_PURCHASED = "datePurchased";
	public static final String CUSTOMER_ID = "customerId";
	public static final String CURRENCY = "currency";
	public static final String ID = "id";
	public static final String LAST_MODIFIED = "lastModified";
	public static final String CREDIT_CARD = "creditCard";
	public static final String STATUS = "status";

}

