package org.orakzai.lab.shop.domain.business.catalog.product.model.price;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;
import org.orakzai.lab.shop.domain.utils.CloneUtils;

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
@Table(name = "PRODUCT_PRICE", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class ProductPrice extends SalesManagerEntity<Long, ProductPrice> {

    private static final long serialVersionUID = -9186473817468772165L;

    private final static String DEFAULT_PRICE_CODE="base";

    @Id
    @Column(name = "PRODUCT_PRICE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_PRICE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productPrice", cascade = CascadeType.ALL)
    private Set<ProductPriceDescription> descriptions = new HashSet<>();

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name = "PRODUCT_PRICE_CODE", nullable=false)
    private String code = DEFAULT_PRICE_CODE;

    @Column(name = "PRODUCT_PRICE_AMOUNT", nullable=false)
    private BigDecimal productPriceAmount = new BigDecimal(0);

    @Column(name = "PRODUCT_PRICE_TYPE", length=20)
    @Enumerated(value = EnumType.STRING)
    private ProductPriceType productPriceType = ProductPriceType.ONE_TIME;

    @Column(name = "DEFAULT_PRICE")
    private boolean defaultPrice = false;

    @Column(name = "PRODUCT_PRICE_SPECIAL_ST_DATE")
    private LocalDate productPriceSpecialStartDate;

    @Column(name = "PRODUCT_PRICE_SPECIAL_END_DATE")
    private LocalDate productPriceSpecialEndDate;

    @Column(name = "PRODUCT_PRICE_SPECIAL_AMOUNT")
    private BigDecimal productPriceSpecialAmount;


    @ManyToOne(targetEntity = ProductAvailability.class)
    @JoinColumn(name = "PRODUCT_AVAIL_ID", nullable = false)
    private ProductAvailability productAvailability;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setProductPriceSpecialStartDate(
            LocalDate productPriceSpecialStartDate) {
        this.productPriceSpecialStartDate = CloneUtils.clone(productPriceSpecialStartDate);
    }

    public LocalDate getProductPriceSpecialEndDate() {
        return CloneUtils.clone(productPriceSpecialEndDate);
    }
}
