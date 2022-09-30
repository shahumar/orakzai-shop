package org.orakzai.lab.shop.domain.business.order.model.orderproduct;

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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ORDER_PRODUCT" , schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class OrderProduct extends SalesManagerEntity<Long, OrderProduct> {

    private static final long serialVersionUID = 176131742783954627L;

    @Id
    @Column(name="ORDER_PRODUCT_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_PRODUCT_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column (name="PRODUCT_SKU")
    private String sku;

    @Column (name="PRODUCT_NAME" , length=64 , nullable=false)
    private String productName;

    @Column (name="PRODUCT_QUANTITY")
    private int productQuantity;

    @Column (name="ONETIME_CHARGE" , nullable=false )
    private BigDecimal oneTimeCharge;


    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Set<OrderProductAttribute> orderAttributes = new HashSet<>();

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Set<OrderProductPrice> prices = new HashSet<OrderProductPrice>();

    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL)
    private Set<OrderProductDownload> downloads = new HashSet<OrderProductDownload>();

}
