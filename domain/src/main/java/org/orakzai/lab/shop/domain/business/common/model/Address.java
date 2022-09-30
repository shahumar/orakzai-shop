package org.orakzai.lab.shop.domain.business.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    private String city;
    private String postalCode;
    private String stateProvince;
    private String zone;//code
    private String country;//code
}
