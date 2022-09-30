package org.orakzai.lab.shop.domain.business.reference.language.model;

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

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Cacheable
public class Language extends SalesManagerEntity<Integer, Language> implements Auditable {

    private static final long serialVersionUID = -7676627812941330669L;



    @Id
    @Column(name="LANGUAGE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "LANG_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Integer id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Column(name="CODE", nullable = false)
    private String code;

    @Column(name="SORT_ORDER")
    private Integer sortOrder;

    @SuppressWarnings("unused")
    @OneToMany(mappedBy = "defaultLanguage", targetEntity = MerchantStore.class)
    private List<MerchantStore> storesDefaultLanguage;

    @SuppressWarnings("unused")
    @ManyToMany(mappedBy = "languages", targetEntity = MerchantStore.class)
    private List<MerchantStore> stores = new ArrayList<>();

    public Language(String code) {
        this.setCode(code);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (!(obj instanceof Language)) {
            return false;
        } else {
            Language language = (Language) obj;
            return (this.id == language.getId());
        }
    }
}
