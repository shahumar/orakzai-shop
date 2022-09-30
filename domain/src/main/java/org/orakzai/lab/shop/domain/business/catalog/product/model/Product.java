package org.orakzai.lab.shop.domain.business.catalog.product.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.Manufacturer;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationship;
import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "PRODUCT", schema = SchemaConstant.SALESMANAGER_SCHEMA)
public class Product extends SalesManagerEntity<Long, Product> implements Auditable {

    private static final long serialVersionUID = -6228066416290007047L;

    @Id
    @Column(name = "PRODUCT_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductDescription> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy="product", orphanRemoval = true)
    private Set<ProductAvailability> availabilities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private Set<ProductAttribute> attributes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private Set<ProductImage> images = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "product")
    private Set<ProductRelationship> relationships = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;


    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "PRODUCT_CATEGORY", schema=SchemaConstant.SALESMANAGER_SCHEMA, joinColumns = {
            @JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false) }
            ,
            inverseJoinColumns = { @JoinColumn(name = "CATEGORY_ID",
                    nullable = false, updatable = false) }
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private Set<Category> categories = new HashSet<>();

    @Column(name="DATE_AVAILABLE")
    private LocalDate dateAvailable = LocalDate.now();


    @Column(name="AVAILABLE")
    private boolean available = true;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name="MANUFACTURER_ID", nullable=true)
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name="PRODUCT_TYPE_ID", nullable=true)
    private ProductType type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name="TAX_CLASS_ID", nullable=true)
    private TaxClass taxClass;

    @Column(name = "PRODUCT_VIRTUAL")
    private boolean productVirtual = false;

    @Column(name = "PRODUCT_SHIP")
    private boolean productShipeable = false;


    @Column(name = "PRODUCT_FREE")
    private boolean productIsFree;

    @Column(name = "PRODUCT_LENGTH")
    private BigDecimal productLength;

    @Column(name = "PRODUCT_WIDTH")
    private BigDecimal productWidth;

    @Column(name = "PRODUCT_HEIGHT")
    private BigDecimal productHeight;

    @Column(name = "PRODUCT_WEIGHT")
    private BigDecimal productWeight;

    @Column(name = "REVIEW_AVG")
    private BigDecimal productReviewAvg;

    @Column(name = "REVIEW_COUNT")
    private Integer productReviewCount;

    @Column(name = "QUANTITY_ORDERED")
    private Integer productOrdered;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder = Integer.valueOf(0);

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name = "SKU")
    private String sku;

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

    public ProductDescription getProductDescription() {
        if(this.getDescriptions()!=null && this.getDescriptions().size()>0) {
            return this.getDescriptions().iterator().next();
        }
        return null;
    }

    public ProductImage getProductImage() {
        ProductImage productImage = null;
        if(this.getImages()!=null && this.getImages().size()>0) {
            for(ProductImage image : this.getImages()) {
                productImage = image;
                if(productImage.isDefaultImage()) {
                    break;
                }
            }
        }
        return productImage;
    }

}
