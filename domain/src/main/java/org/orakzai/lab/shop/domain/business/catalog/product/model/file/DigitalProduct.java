package org.orakzai.lab.shop.domain.business.catalog.product.model.file;

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
@Table(name = "PRODUCT_DIGITAL", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints= @UniqueConstraint(
        columnNames = {"PRODUCT_ID", "FILE_NAME"}))
public class DigitalProduct extends SalesManagerEntity<Long, DigitalProduct> {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "PRODUCT_DIGITAL_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_DGT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;


    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;


    @Column(name="FILE_NAME",nullable=false)
    private String productFileName;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
