package org.orakzai.lab.shop.domain.business.catalog.category.model;

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
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "CATEGORY", schema = SchemaConstant.SALESMANAGER_SCHEMA,
        uniqueConstraints = @UniqueConstraint( columnNames = {"MERCHANT_ID", "CODE"}))
public class Category extends SalesManagerEntity<Long, Category> implements Auditable {

    private static final long serialVersionUID = -846291242449186747L;

    @Id
    @Column(name = "CATEGORY_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CATEGORY_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CategoryDescription> descriptions = new ArrayList<CategoryDescription>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MERCHANT_ID", nullable = false)
    private MerchantStore merchantStore;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    @Column(name = "CATEGORY_IMAGE", length=100)
    private String categoryImage;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder = 0;

    @Column(name = "CATEGORY_STATUS")
    private boolean categoryStatus;

    @Column(name = "VISIBLE")
    private boolean visible;

    @Column(name = "DEPTH")
    private Integer depth;

    @Column(name = "LINEAGE")
    private String lineage;

    @NotEmpty
    @Column(name="CODE", length=100, nullable=false)
    private String code;

    public Category(MerchantStore store) {
        this.merchantStore = store;
        this.id = 0L;
    }

    @Override
    public Long getId() {
        return this.id;
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

    public CategoryDescription getDescription() {
        if(descriptions!=null && descriptions.size()>0) {
            return descriptions.iterator().next();
        }

        return null;
    }

}
