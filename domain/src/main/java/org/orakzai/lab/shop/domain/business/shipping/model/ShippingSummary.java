package org.orakzai.lab.shop.domain.business.shipping.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class ShippingSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal shipping;
    private BigDecimal handling;
    private String shippingModule;
    private String shippingOption;
    private boolean freeShipping;
    private boolean taxOnShipping;
}
