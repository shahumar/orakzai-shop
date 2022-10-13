package org.orakzai.lab.shop.web.dto.customer.attribute;

import lombok.Data;

@Data
public class ReadableCustomerAttribute extends CustomerAttributeEntity {

	private static final long serialVersionUID = 1L;
	private ReadableCustomerOption customerOption;
	private ReadableCustomerOptionValue customerOptionValue;
}
