package org.orakzai.lab.shop.domain.business.customer.model.attribute;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name="CUSTOMER_OPTION_SET", schema= SchemaConstant.SALESMANAGER_SCHEMA,
        uniqueConstraints={
                @UniqueConstraint(columnNames={
                        "CUSTOMER_OPTION_ID", "CUSTOMER_OPTION_VALUE_ID"})})
public class CustomerOptionSet extends SalesManagerEntity<Long, CustomerOptionSet> {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CUSTOMER_OPTIONSET_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUST_OPTSET_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_OPTION_ID", nullable=false)
    private CustomerOption customerOption = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_OPTION_VALUE_ID", nullable=false)
    private CustomerOptionValue customerOptionValue = null;



    @Column(name="SORT_ORDER")
    private Integer sortOrder = Integer.valueOf(0);

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
