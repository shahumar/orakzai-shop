package org.orakzai.lab.shop.domain.business.tax.model.taxrate;


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
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "TAX_RATE" , schema = SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints={
        @UniqueConstraint(columnNames={
                "TAX_CODE",
                "MERCHANT_ID"})})
public class TaxRate extends SalesManagerEntity<Long, TaxRate> implements Auditable {

    private static final long serialVersionUID = 3356827741612925066L;

    @Id
    @Column(name = "TAX_RATE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "TAX_RATE_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Column(name = "TAX_PRIORITY")
    private Integer taxPriority = 0;

    @Column(name = "TAX_RATE" , nullable= false , precision=7, scale=4)
    private BigDecimal taxRate;

    @NotEmpty
    @Column(name = "TAX_CODE")
    private String code;


    @Column(name = "PIGGYBACK")
    private boolean piggyback;

    @ManyToOne
    @JoinColumn(name = "TAX_CLASS_ID" , nullable=false)
    private TaxClass taxClass;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;

    @Valid
    @OneToMany(mappedBy = "taxRate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaxRateDescription> descriptions = new ArrayList<TaxRateDescription>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
    @JoinColumn(name="COUNTRY_ID", nullable=false, updatable=true)
    private Country country;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ZONE_ID", nullable=true, updatable=true)
    private Zone zone;

    @Column(name = "STORE_STATE_PROV", length=100)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private TaxRate parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaxRate> taxRates = new ArrayList<>();

    @Transient
    private String rateText;

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }

}
