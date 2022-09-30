package org.orakzai.lab.shop.domain.business.catalog.product.model.description;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_DESCRIPTION", schema="SALESMANAGER", uniqueConstraints={
        @UniqueConstraint(columnNames={"PRODUCT_ID", "LANGUAGE_ID"})})
public class ProductDescription extends Description {

    private static final long serialVersionUID = -7991123535661321865L;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "PRODUCT_HIGHLIGHT")
    private String productHighlight;

    @Column(name = "DOWNLOAD_LNK")
    private String productExternalDl;

    @Column(name = "SEF_URL")
    private String seUrl;

    @Column(name = "META_TITLE")
    private String metatagTitle;

    @Column(name = "META_KEYWORDS")
    private String metatagKeywords;

    @Column(name = "META_DESCRIPTION")
    private String metatagDescription;
    
    public String getSeUrl() {
    	if (seUrl != null && seUrl.length() > 0) {
    		return seUrl;
    	}
    	if (product == null) {
    		return null;
    	}
    	return getProduct().getSku();
    }
}
