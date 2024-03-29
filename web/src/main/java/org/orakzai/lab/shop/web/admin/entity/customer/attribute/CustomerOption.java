package org.orakzai.lab.shop.web.admin.entity.customer.attribute;

import java.io.Serializable;
import java.util.List;

import org.orakzai.lab.shop.web.dto.ShopEntity;

public class CustomerOption extends ShopEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String name;
	
	private CustomerOptionValue defaultValue;
	
	private List<CustomerOptionValue> availableValues;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerOptionValue getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(CustomerOptionValue defaultValue) {
		this.defaultValue = defaultValue;
	}

	public List<CustomerOptionValue> getAvailableValues() {
		return availableValues;
	}

	public void setAvailableValues(List<CustomerOptionValue> availableValues) {
		this.availableValues = availableValues;
	}
	
	
	

}
