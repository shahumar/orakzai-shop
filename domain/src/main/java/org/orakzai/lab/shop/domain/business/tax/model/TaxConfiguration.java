package org.orakzai.lab.shop.domain.business.tax.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class TaxConfiguration implements JSONAware {

    private TaxBasisCalculation taxBasisCalculation = TaxBasisCalculation.SHIPPINGADDRESS;

    private boolean collectTaxIfDifferentProvinceOfStoreCountry = true;
    private boolean collectTaxIfDifferentCountryOfStoreCountry = false;

    @SuppressWarnings("unchecked")
    @Override
    public String toJSONString() {
        JSONObject data = new JSONObject();
        data.put("taxBasisCalculation", this.getTaxBasisCalculation().name());

        return data.toJSONString();
    }
}
