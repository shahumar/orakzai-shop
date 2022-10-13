package org.orakzai.lab.shop.web.dto.customer.attribute;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReadableCustomerOptionValue extends CustomerOptionValueEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private CustomerOptionValueDescription description;
}
