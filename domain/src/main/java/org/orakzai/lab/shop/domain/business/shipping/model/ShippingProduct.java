package org.orakzai.lab.shop.domain.business.shipping.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class ShippingProduct {
    private int quantity = 1;
    private Product product;

    public ShippingProduct(Product product) {
        this.product = product;

    }
}
