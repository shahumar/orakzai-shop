package org.orakzai.lab.shop.domain.business.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingSummary;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    private ShippingSummary shippingSummary;
    private List<ShoppingCartItem> products = new ArrayList<>();
}
