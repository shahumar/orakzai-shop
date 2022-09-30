package org.orakzai.lab.shop.domain.business.common.model;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
    private int startIndex = 0;
    private int maxCount = 0;
    private String code;


    private CriteriaOrderBy orderBy = CriteriaOrderBy.DESC;
}
