package org.orakzai.lab.shop.web.dto.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("url")
	private String url;
	
	private String icon;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("order")
	private int order;
	
	@JsonProperty("menus")
	private List<Menu> menus = new ArrayList<>();
	
	

}
