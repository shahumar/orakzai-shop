package org.orakzai.lab.shop.domain.business.system.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
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
@EntityListeners(value = AuditListener.class)
@Table(name = "MERCHANT_CONFIGURATION", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints= @UniqueConstraint(
        columnNames = {"MERCHANT_ID", "CONFIG_KEY"}))
public class MerchantConfiguration extends SalesManagerEntity<Long, MerchantConfiguration> implements Serializable, Auditable {

    private static final long serialVersionUID = 4246917986731953459L;

    @Id
    @Column(name = "MERCHANT_CONFIG_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "MERCH_CONF_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=true)
    private MerchantStore merchantStore;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Column(name="CONFIG_KEY")
    private String key;


    @Column(name="VALUE")
    private String value;

    @Column(name="TYPE")
    @Enumerated(value = EnumType.STRING)
    private MerchantConfigurationType merchantConfigurationType = MerchantConfigurationType.INTEGRATION;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
