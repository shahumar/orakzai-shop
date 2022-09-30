package org.orakzai.lab.shop.domain.business.customer.model.attribute;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
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
@Table(name="CUSTOMER_OPTION_DESC", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
        @UniqueConstraint(columnNames={
                "CUSTOMER_OPTION_ID", "LANGUAGE_ID"})})
public class CustomerOptionDescription extends Description {

    private static final long serialVersionUID = -3158504904707188465L;

    @ManyToOne(targetEntity = CustomerOption.class)
    @JoinColumn(name = "CUSTOMER_OPTION_ID", nullable = false)
    private CustomerOption customerOption;

    @Column(name="CUSTOMER_OPTION_COMMENT")
    private String customerOptionComment;
}
