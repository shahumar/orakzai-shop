package org.orakzai.lab.shop.domain.business.customer.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Criteria;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class CustomerCriteria extends Criteria {

    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private String country;
}
