package org.orakzai.lab.shop.web.dto.customer;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CustomerEntity extends Customer implements Serializable {

	private static final long serialVersionUID = 1L;


	@Email(message="{messages.invalid.email}")
    @NotEmpty(message="{NotEmpty.customer.emailAddress}")
	private String emailAddress;
	@Valid
	private Address billing;
	private Address delivery;
	private String gender;

	private String language;
	private String firstName;
	private String lastName;
	
	private String encodedPassword = null;
	private String clearPassword = null;
	
	private String storeCode;
	
	@NotEmpty(message="{NotEmpty.customer.userName}")
	private String userName;
}
