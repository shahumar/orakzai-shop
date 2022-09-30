package org.orakzai.lab.shop.domain.business.tax.model.taxrate;

import lombok.Data;
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
@Table(name = "TAX_RATE_DESCRIPTION" , schema= SchemaConstant.SALESMANAGER_SCHEMA ,uniqueConstraints={
        @UniqueConstraint(columnNames={
                "TAX_RATE_ID",
                "LANGUAGE_ID"})})
public class TaxRateDescription extends Description {

    private static final long serialVersionUID = -4752794805878361822L;

    @ManyToOne(targetEntity = TaxRate.class)
    @JoinColumn(name = "TAX_RATE_ID")
    private TaxRate taxRate;
}
