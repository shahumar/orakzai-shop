package org.orakzai.lab.shop.web.controller.images;

import java.io.IOException;

import org.orakzai.lab.shop.domain.business.catalog.product.model.file.ProductImageSize;
import org.orakzai.lab.shop.domain.business.catalog.product.service.image.ProductImageService;
import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.content.service.ContentService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ImagesController {
	
	@Autowired
	ContentService contentService;
	
	@Autowired
	ProductImageService imageService;
	
	@ResponseBody
	@RequestMapping("/static/{storeCode}/{imageType}/{imageName}.{extension}")
	public byte[] printImage(@PathVariable final String storeCode, @PathVariable final String imageType, @PathVariable final String imageName, @PathVariable final String extension) throws IOException, ServiceException {
		FileContentType type = null;
		if (FileContentType.LOGO.name().equals(imageType)) {
			type = FileContentType.LOGO;
		}
		if (FileContentType.IMAGE.name().equals(imageType)) {
			type = FileContentType.IMAGE;
		}
		if(FileContentType.PROPERTY.name().equals(imageType)) {
			type = FileContentType.PROPERTY;
		}
		OutputContentFile image = contentService.getContentFile(storeCode, type, new StringBuilder().append(imageName).append(".").append(extension).toString());
		if (image != null) {
			return image.getFile().toByteArray();
		} else {
			return null;
		}
	}
	
	@ResponseBody
	@RequestMapping("/static/{storeCode}/{imageType}/{productCode}/{imageName}.{extension}")
	public byte[] printImage(@PathVariable final String storeCode, @PathVariable final String imageType, @PathVariable final String productCode, @PathVariable final String imageName, @PathVariable final String extension) throws IOException, ServiceException {
		log.info("Images controller here");
		ProductImageSize size = ProductImageSize.SMALL;
		
		if (imageType.equals(FileContentType.PRODUCTLG.name())) {
			size = ProductImageSize.LARGE;
		}
		
		OutputContentFile image = null;
		
		try {
			image = imageService.getProductImage(storeCode, productCode, new StringBuilder().append(imageName).append(".").append(extension).toString(), size);
		} catch ( ServiceException e) {
			log.error("Cannot retrieve image" + imageName, e);
			
		}
		if (image != null) {
			return image.getFile().toByteArray();
		}
		return null;
	
	}
	
	

}
