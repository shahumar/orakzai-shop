package org.orakzai.lab.shop.domain.business.catalog.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.AttributeCriteria;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Criteria;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductCriteria extends Criteria {

    private String productName;
    private List<AttributeCriteria> attributeCriteria;


    private Boolean available = null;

    private List<Long> categoryIds;
    private List<String> availabilities;
    private List<Long> productIds;

    private Long manufacturerId = null;
}
