package org.orakzai.lab.shop.domain.business.catalog.product.model.image;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="PRODUCT_IMAGE_DESCRIPTION", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
        @UniqueConstraint(columnNames={
                "PRODUCT_IMAGE_ID", "LANGUAGE_ID"})})
public class ProductImageDescription extends Description {

    private static final long serialVersionUID = 247514890386076337L;

    @ManyToOne(targetEntity = ProductImage.class)
    @JoinColumn(name = "PRODUCT_IMAGE_ID", nullable = false)
    private ProductImage productImage;

    @Column(name="ALT_TAG", length=100)
    private String altTag;
}
