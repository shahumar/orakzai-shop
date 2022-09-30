package org.orakzai.lab.shop.domain.business.order.model.orderaccount;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ORDER_ACCOUNT_PRODUCT", schema= SchemaConstant.SALESMANAGER_SCHEMA )
public class OrderAccountProduct implements Serializable {

    private static final long serialVersionUID = -7437197293537758668L;

    @Id
    @Column(name="ORDER_ACCOUNT_PRODUCT_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "ORDERACCOUNTPRODUCT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long orderAccountProductId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ACCOUNT_ID" , nullable=false)
    private OrderAccount orderAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_PRODUCT_ID" , nullable=false)
    private OrderProduct orderProduct;

    @Column (name="ORDER_ACCOUNT_PRODUCT_ST_DT" , length=0 , nullable=false)
    private LocalDate orderAccountProductStartDate;

    @Column (name="ORDER_ACCOUNT_PRODUCT_END_DT", length=0)
    private LocalDate orderAccountProductEndDate;

    @Column (name="ORDER_ACCOUNT_PRODUCT_EOT"  , length=0 )
    private LocalDate orderAccountProductEot;

    @Column (name="ORDER_ACCOUNT_PRODUCT_ACCNT_DT"  , length=0 )
    private LocalDate orderAccountProductAccountedDate;

    @Column (name="ORDER_ACCOUNT_PRODUCT_L_ST_DT"  , length=0 )
    private LocalDate orderAccountProductLastStatusDate;

    @Column (name="ORDER_ACCOUNT_PRODUCT_L_TRX_ST" , nullable=false )
    private Integer orderAccountProductLastTransactionStatus;

    @Column (name="ORDER_ACCOUNT_PRODUCT_PM_FR_TY" , nullable=false )
    private Integer orderAccountProductPaymentFrequencyType;

    @Column (name="ORDER_ACCOUNT_PRODUCT_STATUS" , nullable=false )
    private Integer orderAccountProductStatus;
}
