package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="PRODUCT_ATTRIBUTE", schema= SchemaConstant.SALESMANAGER_SCHEMA,
        uniqueConstraints={
                @UniqueConstraint(columnNames={
                        "OPTION_ID",
                        "OPTION_VALUE_ID",
                        "PRODUCT_ID"})})
public class ProductAttribute extends SalesManagerEntity<Long, ProductAttribute> {

    @Id
    @Column(name = "PRODUCT_ATTRIBUTE_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_ATTR_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;


    @Column(name="PRODUCT_ATRIBUTE_PRICE")
    private BigDecimal productAttributePrice;


    @Column(name="PRODUCT_ATTRIBUTE_SORT_ORD")
    private Integer productOptionSortOrder;

    @Column(name="PRODUCT_ATTRIBUTE_FREE")
    private boolean productAttributeIsFree;


    @Column(name="PRODUCT_ATTRIBUTE_WEIGHT")
    private BigDecimal productAttributeWeight;

    @Column(name="PRODUCT_ATTRIBUTE_DEFAULT")
    private boolean attributeDefault=false;

    @Column(name="PRODUCT_ATTRIBUTE_REQUIRED")
    private boolean attributeRequired=false;

    /**
     * a read only attribute is considered as a core attribute addition
     */
    @Column(name="PRODUCT_ATTRIBUTE_FOR_DISP")
    private boolean attributeDisplayOnly=false;


    @Column(name="PRODUCT_ATTRIBUTE_DISCOUNTED")
    private boolean attributeDiscounted=false;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OPTION_ID", nullable=false)
    private ProductOption productOption;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OPTION_VALUE_ID", nullable=false)
    private ProductOptionValue productOptionValue;


    /**
     * This transient object property
     * is a utility used only to submit from a free text
     */
    @Transient
    private String attributePrice = "0";


    /**
     * This transient object property
     * is a utility used only to submit from a free text
     */
    @Transient
    private String attributeSortOrder = "0";



    /**
     * This transient object property
     * is a utility used only to submit from a free text
     */
    @Transient
    private String attributeAdditionalWeight = "0";

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
