package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;
import org.springframework.web.multipart.MultipartFile;

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
@Table(name="PRODUCT_OPTION_VALUE",
        schema= SchemaConstant.SALESMANAGER_SCHEMA,
        uniqueConstraints= @UniqueConstraint(columnNames = {"MERCHANT_ID", "PRODUCT_OPTION_VAL_CODE"}),
        indexes = {
            @Index(name="PRD_OPTION_VAL_CODE_IDX", columnList = "PRODUCT_OPTION_VAL_CODE")
        })
public class ProductOptionValue extends SalesManagerEntity<Long, ProductOptionValue> {

    @Id
    @Column(name="PRODUCT_OPTION_VALUE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_OPT_VAL_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name="PRODUCT_OPT_VAL_SORT_ORD")
    private Integer productOptionValueSortOrder;

    @Column(name="PRODUCT_OPT_VAL_IMAGE")
    private String productOptionValueImage;

    @Column(name="PRODUCT_OPT_FOR_DISP")
    private boolean productOptionDisplayOnly=false;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name="PRODUCT_OPTION_VAL_CODE")
    private String code;

    @Transient
    private MultipartFile image = null;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productOptionValue")
    private Set<ProductOptionValueDescription> descriptions = new HashSet<>();

    @Transient
    private List<ProductOptionValueDescription> descriptionsList = new ArrayList<>();

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

    public List<ProductOptionValueDescription> getDescriptionsSettoList() {
        if(descriptionsList==null || descriptionsList.size()==0) {
            descriptionsList = new ArrayList<ProductOptionValueDescription>(this.getDescriptions());
        }
        return descriptionsList;
    }
}
