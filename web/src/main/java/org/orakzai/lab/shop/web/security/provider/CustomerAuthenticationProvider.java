package org.orakzai.lab.shop.web.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.Permission;
import org.orakzai.lab.shop.domain.business.user.service.PermissionService;
import org.orakzai.lab.shop.web.security.authentication.CustomerAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	private final CustomerService customerService;
	private final PermissionService permissionService;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		try {
			Customer customer = customerService.getByNick(auth.getName());
			if (customer == null) {
				return null;
			}
			var authorities = new ArrayList<GrantedAuthority>();
			var groupsId = new ArrayList<Integer>();
			List<Group> groups = customer.getGroups();
			for (Group group : groups) {
				groupsId.add(group.getId());
			}

			List<Permission> permissions = permissionService.getPermissions(groupsId);
			for (Permission permission : permissions) {
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + permission.getPermissionName());
				authorities.add(authority);
			}
			return CustomerAuthentication.authenticated(auth.getPrincipal(), auth.getCredentials(), authorities);

		} catch (Exception e) {
			log.error("Exception while quireing user", e);
			return null;
		}

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(CustomerAuthentication.class);
	}
}
