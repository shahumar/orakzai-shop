package org.orakzai.lab.shop.web.dto.customer.attribute;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerOptionEntity extends CustomerOption implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int order;
	private String code;
	private String type;

}
