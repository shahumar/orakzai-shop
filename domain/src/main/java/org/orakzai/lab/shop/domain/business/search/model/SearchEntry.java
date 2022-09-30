package org.orakzai.lab.shop.domain.business.search.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class SearchEntry {

    private IndexProduct indexProduct;//product as saved in the index
    private List<String> highlights;
}
