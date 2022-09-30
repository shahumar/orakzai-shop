package org.orakzai.lab.shop.domain.business.shipping.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Getter
@Setter
public class ShippingQuote implements Serializable {

    private static final long serialVersionUID = 1L;
    public final static String NO_SHIPPING_TO_SELECTED_COUNTRY = "NO_SHIPPING_TO_SELECTED_COUNTRY";
    public final static String NO_SHIPPING_MODULE_CONFIGURED= "NO_SHIPPING_MODULE_CONFIGURED";
    public final static String ERROR= "ERROR";

    private String shippingModuleCode;
    private List<ShippingOption> shippingOptions = null;
    /** if an error occurs, this field will be populated from constants defined above **/
    private String shippingReturnCode = null;
    /** indicates if this quote is configured with free shipping **/
    private boolean freeShipping;
    /** the threshold amount for being free shipping **/
    private BigDecimal freeShippingAmount;
    /** handling fees to be added on top of shipping fees **/
    private BigDecimal handlingFees;
    /** apply tax on shipping **/
    private boolean applyTaxOnShipping;

    private ShippingOption selectedShippingOption = null;

    private String quoteError = null;
}
