package org.orakzai.lab.shop.web.dto.customer.attribute;

import java.io.Serializable;

import org.orakzai.lab.shop.web.dto.Entity;

public class CustomerOptionValue extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name;
	
	public String getName() {return name;}
	
	public void setName(String name) {
		this.name = name;
	}
}
