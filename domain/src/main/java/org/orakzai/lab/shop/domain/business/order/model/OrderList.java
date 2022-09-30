package org.orakzai.lab.shop.domain.business.order.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.EntityList;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderList extends EntityList {
    private static final long serialVersionUID = -6645927228659963628L;
    private List<Order> orders;
}
