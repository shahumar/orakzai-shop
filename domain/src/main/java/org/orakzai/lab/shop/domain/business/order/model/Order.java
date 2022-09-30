package org.orakzai.lab.shop.domain.business.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Billing;
import org.orakzai.lab.shop.domain.business.common.model.Delivery;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatus;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatusHistory;
import org.orakzai.lab.shop.domain.business.order.model.payment.CreditCard;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentType;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ORDERS", schema = SchemaConstant.SALESMANAGER_SCHEMA)
public class Order extends SalesManagerEntity<Long, Order> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name ="ORDER_ID" , unique=true , nullable=false )
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "ORDER_ID_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column (name ="ORDER_STATUS")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column (name ="LAST_MODIFIED")
    private LocalDate lastModified;

    //the customer object can be detached. An order can exist and the customer deleted
    @Column (name ="CUSTOMER_ID")
    private Long customerId;

    @Column (name ="DATE_PURCHASED")
    private LocalDate datePurchased;

    //used for an order payable on multiple installment
    @Column (name ="ORDER_DATE_FINISHED")
    private LocalDate orderDateFinished;

    //What was the exchange rate
    @Column (name ="CURRENCY_VALUE")
    private BigDecimal currencyValue = new BigDecimal(1);//default 1-1

    @Column (name ="ORDER_TOTAL")
    private BigDecimal total;

    @Column (name ="IP_ADDRESS")
    private String ipAddress;

    @Column (name ="CHANNEL")
    @Enumerated(value = EnumType.STRING)
    private OrderChannel channel;

    @Column (name ="ORDER_TYPE")
    @Enumerated(value = EnumType.STRING)
    private OrderType orderType = OrderType.ORDER;

    @Column (name ="PAYMENT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Column (name ="PAYMENT_MODULE_CODE")
    private String paymentModuleCode;


    @Column (name ="SHIPPING_MODULE_CODE")
    private String shippingModuleCode;


    @Embedded
    private Delivery delivery = null;

    @Valid
    @Embedded
    private Billing billing = null;

    @Embedded
    private CreditCard creditCard = null;


    @ManyToOne(targetEntity = Currency.class)
    @JoinColumn(name = "CURRENCY_ID")
    private Currency currency;

    @Column (name ="LOCALE")
    private Locale locale;



    @ManyToOne(targetEntity = MerchantStore.class)
    @JoinColumn(name="MERCHANTID")
    private MerchantStore merchant;

    //@OneToMany(mappedBy = "order")
    //private Set<OrderAccount> orderAccounts = new HashSet<OrderAccount>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @org.hibernate.annotations.OrderBy(clause = "sort_order asc")
    private Set<OrderTotal> orderTotal = new LinkedHashSet<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @org.hibernate.annotations.OrderBy(clause = "ORDER_STATUS_HISTORY_ID asc")
    private Set<OrderStatusHistory> orderHistory = new LinkedHashSet<>();

    @Column (name ="CUSTOMER_EMAIL_ADDRESS", length=250, nullable=false)
    private String customerEmailAddress;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
