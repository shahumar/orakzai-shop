package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;
import java.util.List;

import org.orakzai.lab.shop.domain.business.common.model.Address;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
import org.orakzai.lab.shop.web.dto.customer.ReadableCustomer;

import lombok.Data;

@Data
public class ReadableOrder extends OrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private ReadableCustomer customer;
	private List<ReadableOrderProduct> products;
	private Currency currencyModel;
	
	private Address billing;
	private Address delivery;
}
