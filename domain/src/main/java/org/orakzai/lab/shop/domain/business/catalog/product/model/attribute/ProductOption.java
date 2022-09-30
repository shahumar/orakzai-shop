package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        indexes = {@Index(name="PRD_OPTION_CODE_IDX", columnList = "PRODUCT_OPTION_CODE")},
        name="PRODUCT_OPTION",
        schema= SchemaConstant.SALESMANAGER_SCHEMA,
        uniqueConstraints= @UniqueConstraint(columnNames = {"MERCHANT_ID", "PRODUCT_OPTION_CODE"}))
public class ProductOption extends SalesManagerEntity<Long, ProductOption> {

    @Id
    @Column(name="PRODUCT_OPTION_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_OPTION_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name="PRODUCT_OPTION_SORT_ORD")
    private Integer productOptionSortOrder;

    @Column(name="PRODUCT_OPTION_TYPE", length=10)
    private String productOptionType;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOption")
    private Set<ProductOptionDescription> descriptions = new HashSet<>();

    @Transient
    private List<ProductOptionDescription> descriptionsList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;

    @Column(name="PRODUCT_OPTION_READ")
    private boolean readOnly;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name="PRODUCT_OPTION_CODE")

    private String code;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductOptionDescription> getDescriptionsSettoList() {
        if(descriptionsList==null || descriptionsList.size()==0) {
            descriptionsList = new ArrayList<ProductOptionDescription>(this.getDescriptions());
        }
        return descriptionsList;

    }
}
