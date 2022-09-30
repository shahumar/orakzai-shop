package org.orakzai.lab.shop.domain.business.order.model.orderaccount;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDER_ACCOUNT", schema = SchemaConstant.SALESMANAGER_SCHEMA)
public class OrderAccount extends SalesManagerEntity<Long, OrderAccount> {

    private static final long serialVersionUID = -2429388347536330540L;

    @Id
    @Column(name = "ORDER_ACCOUNT_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_ACCOUNT_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column(name = "ORDER_ACCOUNT_START_DATE", nullable = false, length = 0)
    private LocalDate orderAccountStartDate;

    @Column(name = "ORDER_ACCOUNT_END_DATE", length = 0)
    private LocalDate orderAccountEndDate;

    @Column(name = "ORDER_ACCOUNT_BILL_DAY", nullable = false)
    private Integer orderAccountBillDay;

    @OneToMany(mappedBy = "orderAccount", cascade = CascadeType.ALL)
    private Set<OrderAccountProduct> orderAccountProducts = new HashSet<>();
}
