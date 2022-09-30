package org.orakzai.lab.shop.domain.business.catalog.product.model.review;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT_REVIEW", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class ProductReview extends SalesManagerEntity<Long, ProductReview> implements Auditable {

    private static final long serialVersionUID = -7509351278087554383L;

    @Id
    @Column(name = "PRODUCT_REVIEW_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "PRODUCT_REVIEW_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection audit = new AuditSection();

    @Column(name = "REVIEWS_RATING")
    private Double reviewRating;

    @Column(name = "REVIEWS_READ")
    private Long reviewRead;

    @Column(name = "REVIEW_DATE")
    private LocalDate reviewDate;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne
    @JoinColumn(name="CUSTOMERS_ID")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productReview")
    private Set<ProductReviewDescription> descriptions = new HashSet<>();

    @Override
    public AuditSection getAuditSection() {
        return audit;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        this.audit = audit;
    }
}
