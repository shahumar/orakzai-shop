package org.orakzai.lab.shop.domain.business.system.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
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
@Table(name = "MERCHANT_LOG", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class MerchantLog extends SalesManagerEntity<Long, MerchantLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MERCHANT_LOG_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "MOD_CONF_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore store;

    @Column(name="MODULE", length=25, nullable=true)
    private String module;


    @Column(name="LOG")
    private String log;

    public MerchantLog(MerchantStore store, String log) {
        this.store = store;
        this.log = log;
    }

    public MerchantLog(MerchantStore store, String module, String log) {
        this.store = store;
        this.module = module;
        this.log = log;
    }

}
