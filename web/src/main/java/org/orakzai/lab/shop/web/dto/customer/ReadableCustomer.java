package org.orakzai.lab.shop.web.dto.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.orakzai.lab.shop.web.dto.customer.attribute.ReadableCustomerAttribute;

import lombok.Data;

@Data
public class ReadableCustomer extends CustomerEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ReadableCustomerAttribute> attributes = new ArrayList<>();
}
