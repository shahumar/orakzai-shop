package org.orakzai.lab.shop.domain.business.system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class IntegrationConfiguration implements JSONAware {

    public final static String TEST_ENVIRONMENT = "TEST";
    public final static String PRODUCTION_ENVIRONMENT = "PRODUCTION";

    private String moduleCode;
    private boolean active;
    private boolean defaultSelected;
    //private boolean customModule;
    private Map<String,String> integrationKeys= new HashMap<>();
    private Map<String,List<String>> integrationOptions= new HashMap<>();
    private String environment;

    @JsonProperty("moduleCode")
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @JsonProperty("active")
    public void setActive(boolean active) {
        this.active = active;
    }

    @JsonProperty("integrationKeys")
    public void setIntegrationKeys(Map<String, String> integrationKeys) {
        this.integrationKeys = integrationKeys;
    }

    protected String getJsonInfo() {

        StringBuilder returnString = new StringBuilder();
        returnString.append("{");
        returnString.append("\"moduleCode\"").append(":\"").append(this.getModuleCode()).append("\"");
        returnString.append(",");
        returnString.append("\"active\"").append(":").append(this.isActive());
        returnString.append(",");
        returnString.append("\"defaultSelected\"").append(":").append(this.isDefaultSelected());
        returnString.append(",");
        //returnString.append("\"customModule\"").append(":").append(this.isCustomModule());
        //returnString.append(",");
        returnString.append("\"environment\"").append(":\"").append(this.getEnvironment()).append("\"");
        //returnString.append("}");
        return returnString.toString();

    }


    @SuppressWarnings("unchecked")
    @Override
    public String toJSONString() {


        StringBuilder returnString = new StringBuilder();
        returnString.append(getJsonInfo());

        if(this.getIntegrationKeys().size()>0) {

            JSONObject data = new JSONObject();
            Set<String> keys = this.getIntegrationKeys().keySet();
            for(String key : keys) {
                data.put(key, this.getIntegrationKeys().get(key));
            }
            String dataField = data.toJSONString();

            returnString.append(",").append("\"integrationKeys\"").append(":");
            returnString.append(dataField.toString());


        }


        if(this.getIntegrationOptions()!=null && this.getIntegrationOptions().size()>0) {

            //JSONObject data = new JSONObject();
            StringBuilder optionDataEntries = new StringBuilder();
            Set<String> keys = this.getIntegrationOptions().keySet();
            int countOptions = 0;
            for(String key : keys) {

                List<String> values = this.getIntegrationOptions().get(key);
                StringBuilder optionsEntries = new StringBuilder();
                StringBuilder dataEntries = new StringBuilder();

                int count = 0;
                for(String value : values) {

                    dataEntries.append("\"").append(value).append("\"");
                    if(count<values.size()-1) {
                        dataEntries.append(",");
                    }
                    count++;
                }

                optionsEntries.append("[").append(dataEntries.toString()).append("]");

                optionDataEntries.append("\"").append(key).append("\":").append(optionsEntries.toString());

                if(countOptions<keys.size()-1) {
                    optionDataEntries.append(",");
                }
                countOptions ++;

            }
            String dataField = optionDataEntries.toString();

            returnString.append(",").append("\"integrationOptions\"").append(":{");
            returnString.append(dataField.toString());
            returnString.append("}");

        }


        returnString.append("}");


        return returnString.toString();

    }
}
