package org.orakzai.lab.shop.domain.business.catalog.product.model.availability;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="PRODUCT_AVAILABILITY", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class ProductAvailability extends SalesManagerEntity<Long, ProductAvailability> {

    @Id
    @Column(name = "PRODUCT_AVAIL_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_AVAIL_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;


    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @NotNull
    @Column(name="QUANTITY")
    private Integer productQuantity = 0;

    @Column(name="DATE_AVAILABLE")
    private LocalDate productDateAvailable;

    @Column(name="REGION")
    private String region = Constants.ALL_REGIONS;

    @Column(name="REGION_VARIANT")
    private String regionVariant;

    @Column(name="STATUS")
    private boolean productStatus = true;

    @Column(name="FREE_SHIPPING")
    private boolean productIsAlwaysFreeShipping;

    @Column(name="QUANTITY_ORD_MIN")
    private Integer productQuantityOrderMin = 0;

    @Column(name="QUANTITY_ORD_MAX")
    private Integer productQuantityOrderMax = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="productAvailability", cascade = CascadeType.REMOVE)
    private Set<ProductPrice> prices = new HashSet<>();

    @Transient
    public ProductPrice defaultPrice() {

        for(ProductPrice price : prices) {
            if(price.isDefaultPrice()) {
                return price;
            }
        }
        return new ProductPrice();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }
}
