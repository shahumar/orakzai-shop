package org.orakzai.lab.shop.web.security.authentication;

import java.security.Principal;
import java.util.Collection;

import javax.security.auth.Subject;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class CustomerAuthentication implements Authentication {
	
	private final boolean authentication;
	
	private final Object principal;
	
	private Object credentials;
	
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomerAuthentication(Object principal, Object credentials) {
		this.principal = principal;
		this.credentials = credentials;
		this.authentication = false;
	}
	
	public CustomerAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		this.principal = principal;
		this.credentials = credentials;
		this.authorities = authorities;
		this.authentication = true;
	}
		
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getName() {
		if (this.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) this.getPrincipal()).getUsername();
		}
		if (this.getPrincipal() instanceof AuthenticatedPrincipal) {
			return ((AuthenticatedPrincipal) this.getPrincipal()).getName();
		}
		if (this.getPrincipal() instanceof Principal) {
			return ((Principal) this.getPrincipal()).getName();
		}
		return (this.getPrincipal() == null) ? "" : this.getPrincipal().toString();
	}
	
	@Override
	public Object getPrincipal() {
		return this.principal;
	}
	 @Override
	public boolean isAuthenticated() {
		return authentication;
	}
	 
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
	}
	
	@Override
	public boolean implies(Subject subject) {
		return Authentication.super.implies(subject);
	}

	public static CustomerAuthentication unauthenticated(Object username, Object password) {
		
		return new CustomerAuthentication(username, password);
	}


	public static Authentication authenticated(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		
		return new CustomerAuthentication(principal, credentials, authorities);
	}
	
	

}
