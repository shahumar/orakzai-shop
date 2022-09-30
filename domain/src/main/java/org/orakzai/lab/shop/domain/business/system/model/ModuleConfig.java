package org.orakzai.lab.shop.domain.business.system.model;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleConfig {

    private String scheme;
    private String host;
    private String port;
    private String uri;
    private String env;
    private String config1;
    private String config2;
}
