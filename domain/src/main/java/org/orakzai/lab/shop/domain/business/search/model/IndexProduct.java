package org.orakzai.lab.shop.domain.business.search.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class IndexProduct implements JSONAware {

    private String name;
    private Double price;
    private List<String> categories;//category code
    private String manufacturer;//id of the manufacturer
    private boolean available;
    private String description;
    private List<String> tags;//keywords ?
    private String highlight;
    private String store;
    private String lang;
    private String id;//required by the search framework


    @SuppressWarnings("unchecked")
    @Override
    public String toJSONString() {

        JSONObject obj = new JSONObject();
        obj.put("name", this.getName());
        obj.put("price", this.getPrice());
        obj.put("description", this.getDescription());
        obj.put("highlight", this.getHighlight());
        obj.put("store", this.getStore());
        obj.put("manufacturer", this.getManufacturer());
        obj.put("lang", this.getLang());
        obj.put("id", this.getId());
        if(categories!=null) {
            JSONArray categoriesArray = new JSONArray();
            for(String category : categories) {
                categoriesArray.add(category);
            }
            obj.put("categories", categoriesArray);
        }

        if(tags!=null) {
            JSONArray tagsArray = new JSONArray();
            for(String tag : tags) {
                tagsArray.add(tag);
            }
            obj.put("tags", tagsArray);
        }

        return obj.toJSONString();

    }
}
