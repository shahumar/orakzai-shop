package org.orakzai.lab.shop.web.dto.customer;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.orakzai.lab.shop.web.utils.FieldMatch;

import lombok.Data;


@Data
@FieldMatch.List({
	@FieldMatch(first="password", second="checkPassword", message="password.notequal")
})
public class SecuredCustomer extends PersistableCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Size(min=6, message="{registration.password.not.empty}")
	private String password;
	
	@Size(min=6, message="{registration.password.not.empty}")
	private String checkPassword;
}
