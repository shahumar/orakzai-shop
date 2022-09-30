package org.orakzai.lab.shop.domain.business.shipping.model;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageDetails {
    private double shippingWeight;
    private double shippingMaxWeight;
    private double shippingLength;
    private double shippingHeight;
    private double shippingWidth;
    private int shippingQuantity;
    private int treshold;

    private String itemName = "";
}
