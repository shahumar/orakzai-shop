package org.orakzai.lab.shop.domain.business.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class PermissionList implements Serializable {

    private static final long serialVersionUID = -3122326940968441727L;
    private int totalCount;
    private List<Permission> permissions = new ArrayList<>();
}
