package org.orakzai.lab.shop.domain.business.order.model;

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
public class OrderTotalSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal subTotal;//one time price for items
    private BigDecimal total;//final price
    private BigDecimal taxTotal;//total of taxes

    private List<OrderTotal> totals;//all other fees (tax, shipping ....)
}
