package org.orakzai.lab.shop.domain.business.catalog.product.model.relationship;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_RELATIONSHIP", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class ProductRelationship extends SalesManagerEntity<Long, ProductRelationship> {

    private static final long serialVersionUID = -9045331138054246299L;

    @Id
    @Column(name = "PRODUCT_RELATIONSHIP_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_RELATION_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(targetEntity = MerchantStore.class)
    @JoinColumn(name="MERCHANT_ID",nullable=false)
    private MerchantStore store;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="PRODUCT_ID",updatable=false,nullable=true)
    private Product product = null;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name="RELATED_PRODUCT_ID",updatable=false,nullable=true)
    private Product relatedProduct = null;

    @Column(name="CODE")
    private String code;

    @Column(name="ACTIVE")
    private boolean active = true;
}
