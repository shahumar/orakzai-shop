package org.orakzai.lab.shop.domain.business.search.model;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFacet {
    private String name;
    private String key;
    private long count;
}
