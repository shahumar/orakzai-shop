package org.orakzai.lab.shop.web.dto.customer.attribute;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerOptionValueEntity extends CustomerOptionValue implements Serializable {

	private static final long serialVersionUID = 1L;
	private int order;
	private String code;
}
