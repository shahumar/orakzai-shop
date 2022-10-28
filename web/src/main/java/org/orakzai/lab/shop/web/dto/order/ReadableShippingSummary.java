package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.orakzai.lab.shop.domain.business.shipping.model.ShippingOption;

import lombok.Data;

@Data
public class ReadableShippingSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private BigDecimal shipping;
	private BigDecimal handling;
	private String shippingModule;
	private String shippingOption;
	private boolean freeShipping;
	private boolean taxOnShipping;
	private String shippingText;
	private String handlingText;
	
	
	private ShippingOption selectedShippingOption = null;//Default selected option
	private List<ShippingOption> shippingOptions = null;

}
