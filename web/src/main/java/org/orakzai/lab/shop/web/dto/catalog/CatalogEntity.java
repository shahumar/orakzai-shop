package org.orakzai.lab.shop.web.dto.catalog;

import java.io.Serializable;

import org.orakzai.lab.shop.web.dto.ShopEntity;

import lombok.Data;

@Data
public class CatalogEntity extends ShopEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String friendlyUrl;
	private String keyWords;
	private String highlights;
	private String metaDescription;
	private String title;
	
}
