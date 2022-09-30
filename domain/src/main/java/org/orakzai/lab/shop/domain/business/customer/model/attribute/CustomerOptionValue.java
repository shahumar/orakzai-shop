package org.orakzai.lab.shop.domain.business.customer.model.attribute;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
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
        indexes = {   @Index(name="CUST_OPT_VAL_CODE_IDX", columnList = "CUSTOMER_OPT_VAL_CODE")},
        name="CUSTOMER_OPTION_VALUE",
        schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints= @UniqueConstraint(
        columnNames = {"MERCHANT_ID", "CUSTOMER_OPT_VAL_CODE"}))
public class CustomerOptionValue extends SalesManagerEntity<Long, CustomerOptionValue> {

    private static final long serialVersionUID = 3736085877929910891L;

    @Id
    @Column(name="CUSTOMER_OPTION_VALUE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CUSTOMER_OPT_VAL_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name="SORT_ORDER")
    private Integer sortOrder = 0;

    @Column(name="CUSTOMER_OPT_VAL_IMAGE")
    private String customerOptionValueImage;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name="CUSTOMER_OPT_VAL_CODE")
    private String code;

    @Transient
    private MultipartFile image = null;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerOptionValue")
    private Set<CustomerOptionValueDescription> descriptions = new HashSet<>();

    @Transient
    private List<CustomerOptionValueDescription> descriptionsList = new ArrayList<>();

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

    public List<CustomerOptionValueDescription> getDescriptionsSettoList() {
        if(descriptionsList==null || descriptionsList.size()==0) {
            descriptionsList = new ArrayList<CustomerOptionValueDescription>(this.getDescriptions());
        }
        return descriptionsList;
    }

}

