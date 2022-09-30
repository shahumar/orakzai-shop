package org.orakzai.lab.shop.domain.business.catalog.product.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.EntityList;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductList extends EntityList {
    private static final long serialVersionUID = 7267292601646149482L;
    private List<Product> products = new ArrayList<>();
}
