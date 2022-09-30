package org.orakzai.lab.shop.domain.business.system.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
public class MerchantConfig implements Serializable, JSONAware {

    private static final long serialVersionUID = 1L;
    private boolean displayCustomerSection =false;
    private boolean displayContactUs =false;
    private boolean displayStoreAddress = false;
    private boolean displayAddToCartOnFeaturedItems = false;

    /** Store default search json config **/
    private Map<String,Boolean> useDefaultSearchConfig= new HashMap<>();//language code | true or false
    private Map<String,String> defaultSearchConfigPath= new HashMap<>();//language code | file path

    @SuppressWarnings("unchecked")
    @Override
    public String toJSONString() {
        JSONObject data = new JSONObject();
        data.put("displayCustomerSection", this.isDisplayCustomerSection());
        data.put("displayContactUs", this.isDisplayContactUs());
        data.put("displayStoreAddress", this.isDisplayStoreAddress());
        data.put("displayAddToCartOnFeaturedItems", this.isDisplayAddToCartOnFeaturedItems());

        if(useDefaultSearchConfig!=null) {
            JSONObject obj = new JSONObject();
            for(String key : useDefaultSearchConfig.keySet()) {
                Boolean val = (Boolean)useDefaultSearchConfig.get(key);
                if(val!=null) {
                    obj.put(key,val);
                }
            }
            data.put("useDefaultSearchConfig", obj);
        }

        if(defaultSearchConfigPath!=null) {
            JSONObject obj = new JSONObject();
            for(String key : defaultSearchConfigPath.keySet()) {
                String val = (String)defaultSearchConfigPath.get(key);
                if(!StringUtils.isBlank(val)) {
                    obj.put(key, val);
                }
            }
            data.put("defaultSearchConfigPath", obj);
        }


        return data.toJSONString();
    }
}
