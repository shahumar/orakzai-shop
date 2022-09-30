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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "MODULE_CONFIGURATION", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class IntegrationModule extends SalesManagerEntity<Long, IntegrationModule> implements Serializable, Auditable {

    private static final long serialVersionUID = -357523134800965997L;

    @Id
    @Column(name = "MODULE_CONF_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "MOD_CONF_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;



    @Column(name="MODULE")
    private String module;

    @Column(name="CODE", nullable=false)
    private String code;

    @Column(name="REGIONS")
    private String regions;

    @Lob
    @Column(name="CONFIGURATION")
    private String configuration;

    @Lob
    @Column(name="DETAILS")
    private String configDetails;

    @Column(name="TYPE")
    private String type;


    @Column(name="IMAGE")
    private String image;

    @Column(name="CUSTOM_IND")
    private boolean customModule = false;

    @Transient
    private Set<String> regionsSet = new HashSet<>();

    /**
     * Contains a map of module config by environment (DEV,PROD)
     */
    @Transient
    private Map<String,ModuleConfig> moduleConfigs = new HashMap<String,ModuleConfig>();


    @Transient
    private Map<String,String> details = new HashMap<>();

    @Embedded
    private AuditSection auditSection = new AuditSection();



    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }


    @Override
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
