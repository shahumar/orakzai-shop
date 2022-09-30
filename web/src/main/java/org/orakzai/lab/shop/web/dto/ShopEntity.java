package org.orakzai.lab.shop.web.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ShopEntity extends Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String language;

}
