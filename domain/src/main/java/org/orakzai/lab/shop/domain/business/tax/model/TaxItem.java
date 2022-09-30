package org.orakzai.lab.shop.domain.business.tax.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.OrderTotalItem;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.model.taxrate.TaxRate;

@Getter
@Setter
public class TaxItem extends OrderTotalItem {

    private static final long serialVersionUID = 1L;
    private String label;
    private TaxRate taxRate=null;
}
