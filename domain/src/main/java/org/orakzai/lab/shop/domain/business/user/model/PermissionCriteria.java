package org.orakzai.lab.shop.domain.business.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Criteria;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PermissionCriteria extends Criteria {

    private String permissionName;


    private Boolean available = null;

    private Set<Integer> groupIds;

    private List<String> availabilities;
}
