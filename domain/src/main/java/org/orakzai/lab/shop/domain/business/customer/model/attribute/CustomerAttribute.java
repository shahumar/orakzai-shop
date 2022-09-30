package org.orakzai.lab.shop.domain.business.customer.model.attribute;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="CUSTOMER_ATTRIBUTE", schema= SchemaConstant.SALESMANAGER_SCHEMA,
        uniqueConstraints={
                @UniqueConstraint(columnNames={
                        "OPTION_ID", "CUSTOMER_ID"})})
public class CustomerAttribute extends SalesManagerEntity<Long, CustomerAttribute> {

    private static final long serialVersionUID = -6537491946539803265L;

    @Id
    @Column(name = "CUSTOMER_ATTRIBUTE_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUST_ATTR_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OPTION_ID", nullable=false)
    private CustomerOption customerOption;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="OPTION_VALUE_ID", nullable=false)
    private CustomerOptionValue customerOptionValue;

    @Column(name="CUSTOMER_ATTR_TXT_VAL")
    private String textValue;


    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
