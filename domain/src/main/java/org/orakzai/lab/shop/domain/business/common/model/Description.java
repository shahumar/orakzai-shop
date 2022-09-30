package org.orakzai.lab.shop.domain.business.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(value = AuditListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Description implements Auditable, Serializable {

    private static final long serialVersionUID = -4335863941736710046L;

    @Id
    @Column(name = "DESCRIPTION_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "DESCRIPTION_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @ManyToOne(optional = false)
    @JoinColumn(name = "LANGUAGE_ID")
    private Language language;

    @NotEmpty
    @Column(name="NAME", nullable = false, length=120)
    public String name;

    @Column(name="TITLE", length=100)
    private String title;

    @Lob
    @Column(name="DESCRIPTION")
    private String description;

    public Description(Language language, String name) {
        this.setLanguage(language);
        this.setName(name);
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
