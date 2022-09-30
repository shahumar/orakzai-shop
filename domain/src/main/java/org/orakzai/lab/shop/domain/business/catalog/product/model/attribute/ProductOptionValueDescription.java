package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;

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
@Table(name = "PRODUCT_OPTION_VALUE_DESCRIPTION", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
        @UniqueConstraint(columnNames={
                "PRODUCT_OPTION_VALUE_ID", "LANGUAGE_ID"})})
public class ProductOptionValueDescription extends Description {
    private static final long serialVersionUID = 7402155175956813576L;

    @ManyToOne(targetEntity = ProductOptionValue.class)
    @JoinColumn(name = "PRODUCT_OPTION_VALUE_ID")
    private ProductOptionValue productOptionValue;
}
