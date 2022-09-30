package org.orakzai.lab.shop.domain.business.catalog.product.model.image;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import javax.persistence.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_IMAGE", schema="SALESMANAGER")
public class ProductImage extends SalesManagerEntity<Long, ProductImage> {

    private static final long serialVersionUID = 247514890386076337L;

    @Id
    @Column(name = "PRODUCT_IMAGE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_IMG_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productImage", cascade = CascadeType.ALL)
    private List<ProductImageDescription> descriptions = new ArrayList<>();


    @Column(name = "PRODUCT_IMAGE")
    private String productImage;

    @Column(name = "DEFAULT_IMAGE")
    private boolean defaultImage = true;

    @Column(name = "IMAGE_TYPE")
    private int imageType;

    @Column(name = "IMAGE_CROP")
    private boolean imageCrop;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Transient
    private InputStream image = null;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
