package org.orakzai.lab.shop.domain.business.shipping.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@Getter
@Setter
public class ShippingOption implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BigDecimal optionPrice;

    private String optionName;
    private String optionCode;
    private String optionDeliveryDate;
    private String optionShippingDate;
    private String optionPriceText;
    private String optionId;
    private String description;

    private String estimatedNumberOfDays;



    public BigDecimal getOptionPrice() {

        if(!StringUtils.isBlank(this.getOptionPriceText())) {
            try {
                this.optionPrice = new BigDecimal(this.getOptionPriceText());
            } catch(Exception e) {
                log.error("Can't convert price text " + this.getOptionPriceText() + " to big decimal");;
            }
        }

        return optionPrice;
    }
}
