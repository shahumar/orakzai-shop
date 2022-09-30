package org.orakzai.lab.shop.domain.business.catalog.product.model.price;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class FinalPrice implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal discountedPrice = null;//final price if a discount is applied
    private BigDecimal originalPrice = null;//original price
    private BigDecimal finalPrice = null;//final price discount or not
    private boolean discounted = false;
    private int discountPercent = 0;

    private LocalDate discountEndDate = null;

    private boolean defaultPrice;
    private ProductPrice productPrice;
    List<FinalPrice> additionalPrices;
}
