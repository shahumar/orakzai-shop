package org.orakzai.lab.shop.web.dto.customer;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="{NotEmpty.customer.firstName}")
	private String firstName;
	
	@NotEmpty(message="{NotEmpty.customer.lastName}")
	private String lastName;
	private String bilstateOther;

	private String company;

	private String phone;
	private String address;
	private String city;
	

	private String postalCode;
	

	private String stateProvince;
	private boolean billingAddress;
	
	private String zone;//code
	
	@NotEmpty(message="{NotEmpty.customer.billing.country}")
	private String country;//code
}
