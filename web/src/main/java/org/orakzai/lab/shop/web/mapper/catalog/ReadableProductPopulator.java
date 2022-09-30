package org.orakzai.lab.shop.web.mapper.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.FinalPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.orakzai.lab.shop.domain.utils.AbstractDataPopulator;
import org.orakzai.lab.shop.web.dto.catalog.ReadableImage;
import org.orakzai.lab.shop.web.dto.catalog.product.ReadableProduct;
import org.orakzai.lab.shop.web.utils.ImageFilePathUtils;

import lombok.Data;

@Data
public class ReadableProductPopulator extends AbstractDataPopulator<Product, ReadableProduct>{
	
	private PricingService pricingService;
	
	public ReadableProductPopulator(PricingService pricingService) {
		this.pricingService = pricingService;
	}
	
	@Override
	public ReadableProduct populate(Product source, ReadableProduct target, MerchantStore store, Language language)
			throws ConversionException {
		Validate.notNull(pricingService, "Requires to set pricing service");
		try {
			ProductDescription description = source.getProductDescription();
			target.setId(source.getId());
			target.setAvailable(source.isAvailable());
			if (source.getProductReviewAvg() != null) {
				double avg = source.getProductReviewAvg().doubleValue();
				double rating = Math.round(avg * 2) / 2.0f;
				target.setRating(rating);
			}
			target.setProductVirtual(source.isProductVirtual());
			if (source.getProductReviewCount() != null) {
				target.setRatingCount(source.getProductReviewCount().intValue());
			}
			if (description != null) {
				org.orakzai.lab.shop.web.dto.catalog.product.ProductDescription targetDescription = new org.orakzai.lab.shop.web.dto.catalog.product.ProductDescription();
				targetDescription.setFriendlyUrl(description.getSeUrl());
				targetDescription.setName(description.getName());
				if(!StringUtils.isBlank(description.getMetatagTitle())) {
					targetDescription.setTitle(description.getMetatagTitle());
				} else {
					targetDescription.setTitle(description.getName());
					
				}
				targetDescription.setDescription(description.getDescription());
				targetDescription.setMetaDescription(description.getMetatagDescription());
				targetDescription.setHighlights(description.getProductHighlight());
				target.setDescription(targetDescription);
				
			}
			ProductImage image = source.getProductImage();
			if (image != null) {
				ReadableImage rimg = new ReadableImage();
				rimg.setImageName(image.getProductImage());
				String imagePath = ImageFilePathUtils.buildProductImageFilePath(store, source.getSku(), image.getProductImage());
				rimg.setImageUrl(imagePath);
				rimg.setId(image.getId());
				target.setImage(rimg);
				
				Set<ProductImage> images = source.getImages();
				if (images != null && images.size() > 0) {
					var imageList = new ArrayList<ReadableImage>();
					for (ProductImage img : images) {
						ReadableImage prdImg = new ReadableImage();
						prdImg.setImageName(img.getProductImage());
						String path = ImageFilePathUtils.buildProductImageFilePath(store, source.getSku(), img.getProductImage());
						prdImg.setImageUrl(path);
						imageList.add(prdImg);
					}
					target.setImages(imageList);
				}
			}
			
			FinalPrice price = pricingService.calculateProductPrice(source);
			target.setFinalPrice(pricingService.getDisplayAmount(price.getFinalPrice(), store));
			target.setPrice(price.getFinalPrice());
			if(price.isDiscounted()) {
				target.setDiscounted(true);
				target.setOriginalPrice(pricingService.getDisplayAmount(price.getOriginalPrice(), store));
			}
			
			for (ProductAvailability availability : source.getAvailabilities()) {
				if( availability.getRegion().equals(Constants.ALL_REGIONS)) {
					target.setQuantity(availability.getProductQuantity());
					target.setQuantityOrderMaximum(availability.getProductQuantityOrderMax());
					target.setQuantityOrderMinimum(availability.getProductQuantityOrderMin());
				}
			}
			return target;
			
			
		} catch (Exception e) {
			throw new ConversionException(e);
		}
		
	}
	
	@Override
	protected ReadableProduct createTarget() {
		return null;
	}

}
