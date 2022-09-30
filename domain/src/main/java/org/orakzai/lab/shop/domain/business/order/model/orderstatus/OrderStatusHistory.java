package org.orakzai.lab.shop.domain.business.order.model.orderstatus;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ORDER_STATUS_HISTORY" , schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class OrderStatusHistory implements Serializable {

    private static final long serialVersionUID = 3438730310126102187L;

    @Id
    @Column( name="ORDER_STATUS_HISTORY_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "STATUS_HIST_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Column(name = "DATE_ADDED", nullable = false)
    private LocalDate dateAdded;

    @Column(name = "CUSTOMER_NOTIFIED")
    private java.lang.Integer customerNotified;

    @Column(name = "COMMENTS")
    private String comments;
}
