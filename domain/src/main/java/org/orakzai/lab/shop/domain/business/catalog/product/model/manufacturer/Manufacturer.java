package org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "MANUFACTURER", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class Manufacturer extends SalesManagerEntity<Long, Manufacturer> implements Auditable {

    private static final long serialVersionUID = 80693964563570099L;

    @Id
    @Column(name = "MANUFACTURER_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "MANUFACT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<ManufacturerDescription> descriptions = new HashSet<>();

    @Column(name = "MANUFACTURER_IMAGE")
    private String image;

    @Column(name="SORT_ORDER")
    private Integer order = Integer.valueOf(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }
}
