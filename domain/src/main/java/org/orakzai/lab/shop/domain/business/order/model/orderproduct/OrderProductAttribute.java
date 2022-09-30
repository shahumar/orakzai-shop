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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ORDER_PRODUCT_ATTRIBUTE" , schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class OrderProductAttribute implements Serializable {

    private static final long serialVersionUID = 6037571119918073015L;

    @Id
    @Column(name="ORDER_PRODUCT_ATTRIBUTE_ID", nullable=false , unique=true )
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_PRODUCT_ATTR_ID_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column ( name= "PRODUCT_ATTRIBUTE_PRICE" , nullable=false , precision=15 , scale=4 )
    private BigDecimal productAttributePrice;

    @Column ( name= "PRODUCT_ATTRIBUTE_IS_FREE" , nullable=false )
    private boolean productAttributeIsFree;

    @Column ( name= "PRODUCT_ATTRIBUTE_WEIGHT" , precision=15 , scale=4 )
    private java.math.BigDecimal productAttributeWeight;

    @ManyToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID", nullable = false)
    private OrderProduct orderProduct;

    @Column(name = "PRODUCT_OPTION_ID", nullable = false)
    private Long productOptionId;


    @Column(name = "PRODUCT_OPTION_VALUE_ID", nullable = false)
    private Long productOptionValueId;

    @Column ( name= "PRODUCT_ATTRIBUTE_NAME")
    private String productAttributeName;

    @Column ( name= "PRODUCT_ATTRIBUTE_VAL_NAME")
    private String productAttributeValueName;
}
