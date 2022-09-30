package org.orakzai.lab.shop.domain.business.order.model.orderproduct;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ORDER_PRODUCT_PRICE" , schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class OrderProductPrice implements Serializable {

    private static final long serialVersionUID = 3734737890163564311L;

    @Id
    @Column(name="ORDER_PRODUCT_PRICE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "ORDER_PRD_PRICE_ID_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID", nullable = false)
    private OrderProduct orderProduct;


    @Column(name = "PRODUCT_PRICE_CODE", nullable = false , length=64 )
    private String productPriceCode;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "PRODUCT_PRICE_SPECIAL")
    private BigDecimal productPriceSpecial;

    @Column (name="PRD_PRICE_SPECIAL_ST_DT" , length=0)
    private LocalDate productPriceSpecialStartDate;

    @Column (name="PRD_PRICE_SPECIAL_END_DT" , length=0)
    private LocalDate productPriceSpecialEndDate;


    @Column(name = "DEFAULT_PRICE", nullable = false)
    private Boolean defaultPrice;


    @Column(name = "PRODUCT_PRICE_NAME", nullable = true)
    private String productPriceName;
}
