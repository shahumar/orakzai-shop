package org.orakzai.lab.shop.web.admin.security;

import java.io.Serializable;

public class SecurityQuestion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String label;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	

}
