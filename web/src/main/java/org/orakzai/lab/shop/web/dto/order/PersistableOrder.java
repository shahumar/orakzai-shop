package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;
import java.util.List;

import org.orakzai.lab.shop.web.dto.customer.PersistableCustomer;

public class PersistableOrder extends OrderEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PersistableCustomer customer;
	private List<PersistableOrderProduct> orderProductItems;
	private boolean shipToBillingAdress = true;

}
