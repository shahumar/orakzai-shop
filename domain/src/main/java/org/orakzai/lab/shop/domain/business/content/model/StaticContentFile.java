package org.orakzai.lab.shop.domain.business.content.model;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class StaticContentFile extends ContentFile {
    private FileContentType fileContentType;
}
