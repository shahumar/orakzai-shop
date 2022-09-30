package org.orakzai.lab.shop.domain.business.payments.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.order.model.Order;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transaction.class)
public abstract class Transaction_ {

	public static volatile SingularAttribute<Transaction, TransactionType> transactionType;
	public static volatile SingularAttribute<Transaction, BigDecimal> amount;
	public static volatile SingularAttribute<Transaction, String> details;
	public static volatile SingularAttribute<Transaction, Long> id;
	public static volatile SingularAttribute<Transaction, LocalDate> transactionDate;
	public static volatile SingularAttribute<Transaction, AuditSection> auditSection;
	public static volatile SingularAttribute<Transaction, Order> order;
	public static volatile SingularAttribute<Transaction, PaymentType> paymentType;

	public static final String TRANSACTION_TYPE = "transactionType";
	public static final String AMOUNT = "amount";
	public static final String DETAILS = "details";
	public static final String ID = "id";
	public static final String TRANSACTION_DATE = "transactionDate";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String ORDER = "order";
	public static final String PAYMENT_TYPE = "paymentType";

}

