package org.orakzai.lab.shop.web.dto.catalog;

import java.io.Serializable;

import org.orakzai.lab.shop.web.dto.Entity;

import lombok.Data;


@Data
public class ReadableImage extends Entity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String imageName;
	private String imageUrl;

}
