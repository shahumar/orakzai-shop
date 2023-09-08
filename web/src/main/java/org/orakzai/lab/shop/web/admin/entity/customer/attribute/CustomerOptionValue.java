package org.orakzai.lab.shop.web.admin.entity.customer.attribute;

import java.io.Serializable;

import org.orakzai.lab.shop.web.dto.ShopEntity;

public class CustomerOptionValue extends ShopEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
