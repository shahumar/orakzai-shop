package org.orakzai.lab.shop.domain.business.content.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "CONTENT", schema= SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints= @UniqueConstraint(
        columnNames = {"MERCHANT_ID", "CODE"}) )
public class Content extends SalesManagerEntity<Long, Content> implements Serializable {

    private static final long serialVersionUID = 1772757159185494620L;

    @Id
    @Column(name = "CONTENT_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CONTENT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Valid
    @OneToMany(mappedBy="content", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContentDescription> descriptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;

    @NotEmpty
    @Column(name="CODE", length=100, nullable=false)
    private String code;

    @Column(name = "VISIBLE")
    private boolean visible;

    @Column(name = "CONTENT_POSITION", length=10, nullable=true)
    @Enumerated(value = EnumType.STRING)
    private ContentPosition contentPosition;

    //Used for grouping
    //BOX, SECTION, PAGE
    @Column(name = "CONTENT_TYPE", length=10, nullable=true)
    @Enumerated(value = EnumType.STRING)
    private ContentType contentType;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder = 0;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }

    public ContentDescription getDescription() {

        if(this.getDescriptions()!=null && this.getDescriptions().size()>0) {
            return this.getDescriptions().get(0);
        }

        return null;

    }
}
