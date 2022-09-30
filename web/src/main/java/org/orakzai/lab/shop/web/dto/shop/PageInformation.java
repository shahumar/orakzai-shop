package org.orakzai.lab.shop.web.dto.shop;

import java.io.Serializable;

import lombok.Data;


@Data
public class PageInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String pageTitle;
	private String pageDescription;
	private String pageKeywords;
	private String pageUrl;

}
