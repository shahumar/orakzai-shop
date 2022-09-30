package org.orakzai.lab.shop.domain.business.shipping.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class ShippingConfiguration implements JSONAware {

    private ShippingType shippingType = ShippingType.NATIONAL;
    private ShippingBasisType shippingBasisType = ShippingBasisType.SHIPPING;
    private ShippingOptionPriceType shippingOptionPriceType = ShippingOptionPriceType.ALL;
    private ShippingPackageType shippingPackageType = ShippingPackageType.ITEM;
    private ShippingDescription shippingDescription = ShippingDescription.SHORT_DESCRIPTION;
    private ShippingType freeShippingType = null;

    private int boxWidth = 0;
    private int boxHeight = 0;
    private int boxLength = 0;
    private double boxWeight = 0;
    private double maxWeight = 0;

    //free shipping
    private boolean freeShippingEnabled = false;
    private BigDecimal orderTotalFreeShipping = null;


    private BigDecimal handlingFees = null;
    private boolean taxOnShipping = false;


    //JSON bindings
    private String shipType;
    private String shipBaseType;
    private String shipOptionPriceType = ShippingOptionPriceType.ALL.name();
    private String shipPackageType;
    private String shipDescription;
    private String shipFreeType;

    //Transient
    private String orderTotalFreeShippingText = null;
    private String handlingFeesText = null;


    public void setShippingOptionPriceType(ShippingOptionPriceType shippingOptionPriceType) {
        this.shippingOptionPriceType = shippingOptionPriceType;
        this.shipOptionPriceType = this.shippingOptionPriceType.name();
    }

    public void setShippingBasisType(ShippingBasisType shippingBasisType) {
        this.shippingBasisType = shippingBasisType;
        this.shipBaseType = this.shippingBasisType.name();
    }

    public void setShippingType(ShippingType shippingType) {
        this.shippingType = shippingType;
        this.shipType = this.shippingType.name();
    }

    public void setShippingPackageType(ShippingPackageType shippingPackageType) {
        this.shippingPackageType = shippingPackageType;
        this.shipPackageType = shippingPackageType.name();
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
        ShippingType sType = ShippingType.NATIONAL;
        if(shipType.equals(ShippingType.INTERNATIONAL.name())) {
            sType = ShippingType.INTERNATIONAL;
        }
        setShippingType(sType);
    }


    public void setShipOptionPriceType(String shipOptionPriceType) {
        this.shipOptionPriceType = shipOptionPriceType;
        ShippingOptionPriceType sType = ShippingOptionPriceType.ALL;
        if(shipOptionPriceType.equals(ShippingOptionPriceType.HIGHEST.name())) {
            sType = ShippingOptionPriceType.HIGHEST;
        }
        if(shipOptionPriceType.equals(ShippingOptionPriceType.LEAST.name())) {
            sType = ShippingOptionPriceType.LEAST;
        }
        setShippingOptionPriceType(sType);
    }


    public void setShipBaseType(String shipBaseType) {
        this.shipBaseType = shipBaseType;
        ShippingBasisType sType = ShippingBasisType.SHIPPING;
        if(shipBaseType.equals(ShippingBasisType.BILLING.name())) {
            sType = ShippingBasisType.BILLING;
        }
        setShippingBasisType(sType);
    }



    public void setShipPackageType(String shipPackageType) {
        this.shipPackageType = shipPackageType;
        ShippingPackageType sType = ShippingPackageType.ITEM;
        if(shipPackageType.equals(ShippingPackageType.BOX.name())) {
            sType = ShippingPackageType.BOX;
        }
        this.setShippingPackageType(sType);
    }

    public void setShipDescription(String shipDescription) {
        this.shipDescription = shipDescription;
        ShippingDescription sType = ShippingDescription.SHORT_DESCRIPTION;
        if(shipDescription.equals(ShippingDescription.LONG_DESCRIPTION.name())) {
            sType = ShippingDescription.LONG_DESCRIPTION;
        }
        this.setShippingDescription(sType);
    }

    public void setShipFreeType(String shipFreeType) {
        this.shipFreeType = shipFreeType;
        ShippingType sType = ShippingType.NATIONAL;
        if(shipFreeType.equals(ShippingType.INTERNATIONAL.name())) {
            sType = ShippingType.INTERNATIONAL;
        }
        setFreeShippingType(sType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toJSONString() {
        JSONObject data = new JSONObject();
        data.put("shipBaseType", this.getShippingBasisType().name());
        data.put("shipOptionPriceType", this.getShippingOptionPriceType().name());
        data.put("shipType", this.getShippingType().name());
        data.put("shipPackageType", this.getShippingPackageType().name());
        if(shipFreeType!=null) {
            data.put("shipFreeType", this.getFreeShippingType().name());
        }
        data.put("shipDescription", this.getShippingDescription().name());


        data.put("boxWidth", this.getBoxWidth());
        data.put("boxHeight", this.getBoxHeight());
        data.put("boxLength", this.getBoxLength());
        data.put("boxWeight", this.getBoxWeight());
        data.put("maxWeight", this.getMaxWeight());
        data.put("freeShippingEnabled", this.freeShippingEnabled);
        data.put("orderTotalFreeShipping", this.orderTotalFreeShipping);
        data.put("handlingFees", this.handlingFees);
        data.put("taxOnShipping", this.taxOnShipping);


        return data.toJSONString();
    }

}
