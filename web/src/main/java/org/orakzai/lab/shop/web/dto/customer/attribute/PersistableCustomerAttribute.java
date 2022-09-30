package org.orakzai.lab.shop.web.dto.customer.attribute;

import lombok.Data;

@Data
public class PersistableCustomerAttribute extends CustomerAttributeEntity {

	private static final long serialVersionUID = 1L;
	private CustomerOption customerOption;
	private CustomerOptionValue customerOptionValue;
}
