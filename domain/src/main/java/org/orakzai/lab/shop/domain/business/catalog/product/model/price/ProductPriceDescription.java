package org.orakzai.lab.shop.domain.business.catalog.product.model.price;


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
@Table(name="PRODUCT_PRICE_DESCRIPTION", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
        @UniqueConstraint(columnNames={"PRODUCT_PRICE_ID", "LANGUAGE_ID"})})
public class ProductPriceDescription extends Description {
    private static final long serialVersionUID = 270521409645392808L;

    public final static String DEFAULT_PRICE_DESCRIPTION = "DEFAULT";

    @ManyToOne(targetEntity = ProductPrice.class)
    @JoinColumn(name = "PRODUCT_PRICE_ID", nullable = false)
    private ProductPrice productPrice;
}
