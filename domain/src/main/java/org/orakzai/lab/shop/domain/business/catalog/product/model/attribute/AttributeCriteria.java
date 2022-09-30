package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class AttributeCriteria implements Serializable {

    private static final long serialVersionUID = 1L;
    private String attributeCode;
    private String attributeValue;
}
