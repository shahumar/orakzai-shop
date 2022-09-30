package org.orakzai.lab.shop.web.utils;

import javax.validation.Valid;

import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.web.constants.Constants;
import org.springframework.stereotype.Component;

@Component("imageFilePathUtils")
public class ImageFilePathUtils {

	public static String buildProductImageFilePath(MerchantStore store, String sku, String imageName) {
		return new StringBuilder()
				.append(Constants.STATIC_URI)
				.append("/")
				.append(store.getCode())
				.append("/")
				.append(FileContentType.PRODUCT.name())
				.append("/")
				.append(sku)
				.append("/")
				.append(imageName).toString();
	}
	
	public static final String A = "kdkdkdk";

	public static String buildStoreLogoFilePath(@Valid MerchantStore store) {
		return new StringBuilder()
				.append(Constants.STATIC_URI)
				.append("/")
				.append(store.getCode())
				.append("/")
				.append(FileContentType.LOGO)
				.append("/")
				.append(store.getStoreLogo()).toString();
	}

}
