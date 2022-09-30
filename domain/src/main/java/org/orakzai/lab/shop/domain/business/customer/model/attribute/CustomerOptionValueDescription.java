package org.orakzai.lab.shop.domain.business.customer.model.attribute;

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
@Table(name = "CUSTOMER_OPT_VAL_DESCRIPTION", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
        @UniqueConstraint(columnNames={
                "CUSTOMER_OPT_VAL_ID",
                "LANGUAGE_ID"})})
public class CustomerOptionValueDescription extends Description {

    private static final long serialVersionUID = 7402155175956813576L;

    @ManyToOne(targetEntity = CustomerOptionValue.class)
    @JoinColumn(name = "CUSTOMER_OPT_VAL_ID")
    private CustomerOptionValue customerOptionValue;
}
