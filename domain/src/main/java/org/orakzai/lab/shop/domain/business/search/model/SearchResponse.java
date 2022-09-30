package org.orakzai.lab.shop.domain.business.search.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class SearchResponse {

    private int totalCount = 0;//total number of entries
    private int entryCount = 0;//number of entries asked

    private List<SearchEntry> entries;
    private Map<String,List<SearchFacet>> facets;//facet key (example : category) & facet description (example : category code)
}
