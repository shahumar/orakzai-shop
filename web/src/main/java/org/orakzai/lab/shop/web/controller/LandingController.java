package org.orakzai.lab.shop.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationship;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationshipType;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.relationship.ProductRelationshipService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.catalog.product.ReadableProduct;
import org.orakzai.lab.shop.web.mapper.catalog.ReadableProductPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LandingController {
	
	
	@Autowired
	private ProductRelationshipService productRelationshipService;
	
	@Autowired
	private PricingService pricingService;

	@GetMapping(value = {"/", Constants.SHOP_URI, Constants.SHOP_URI + "/", Constants.SHOP_URI + "/home.html"}, name="shop")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response, Locale local) throws Exception {
		
		Language language = (Language) request.getAttribute(Constants.LANGUAGE);
		MerchantStore store = (MerchantStore) request.getAttribute(Constants.MERCHANT_STORE);
		// Get Product Populator
		ReadableProductPopulator populator = new ReadableProductPopulator(pricingService);
		
		// Get Featured Products
		
		List<ProductRelationship> relationships = productRelationshipService.getByType(store, ProductRelationshipType.FEATURED_ITEM, language);
		var featuredItems = new ArrayList<ReadableProduct>();
		for (ProductRelationship relationship : relationships) {
			Product product = relationship.getRelatedProduct();
			ReadableProduct proxyProduct = populator.populate(product, new ReadableProduct(), store, language);
			featuredItems.add(proxyProduct);
		}
		
		model.addAttribute("featuredItems", featuredItems);
		
		StringBuilder template = new StringBuilder()
				.append("landing.")
				.append(store.getStoreTemplate())
				.append(".html");
		return template.toString();
		
	}
	

}
