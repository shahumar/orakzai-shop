package org.orakzai.lab.shop.domain.business.reference.currency.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY", schema = SchemaConstant.SALESMANAGER_SCHEMA)
@Cacheable
public class Currency extends SalesManagerEntity<Long, Currency> implements Serializable {

    private static final long serialVersionUID = -999926410367685145L;

    @Id
    @Column(name = "CURRENCY_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CURRENCY_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name = "CURRENCY_CURRENCY_CODE" ,nullable = false, unique = true)
    private java.util.Currency currency;

    @Column(name = "CURRENCY_SUPPORTED")
    private Boolean supported = true;

    @Column(name = "CURRENCY_CODE", unique = true)
    private String code;

    @Column(name = "CURRENCY_NAME", unique = true)
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
