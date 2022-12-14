package org.orakzai.lab.shop.web.dto.customer;

import java.io.Serializable;
import java.util.List;

import org.orakzai.lab.shop.web.dto.customer.attribute.PersistableCustomerAttribute;

public class PersistableCustomer extends CustomerEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<PersistableCustomerAttribute> attributes;
	public void setAttributes(List<PersistableCustomerAttribute> attributes) {
		this.attributes = attributes;
	}
	public List<PersistableCustomerAttribute> getAttributes() {
		return attributes;
	}
	
}
