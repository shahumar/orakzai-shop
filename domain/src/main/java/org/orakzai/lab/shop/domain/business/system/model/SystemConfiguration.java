package org.orakzai.lab.shop.domain.business.system.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "SYSTEM_CONFIGURATION", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class SystemConfiguration extends SalesManagerEntity<Long, SystemConfiguration> implements Serializable, Auditable {

    private static final long serialVersionUID = 6831573162350751684L;

    @Id
    @Column(name = "SYSTEM_CONFIG_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SYST_CONF_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name="CONFIG_KEY")
    private String key;

    @Column(name="VALUE")
    private String value;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
