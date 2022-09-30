package org.orakzai.lab.shop.domain.search.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.FinalPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.search.documents.ProductSearch;
import org.orakzai.lab.shop.domain.search.repository.ProductSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("productSearchService")
public class ProductSearchServiceImpl implements ProductSearchService {


	@Autowired
	private ProductSearchRepository searchRepository;
	
	@Autowired
	private PricingService pricingService;
	
	
	@Override
	public void index(MerchantStore store, Product product) throws ServiceException {
		FinalPrice price = pricingService.calculateProductPrice(product);
		
		Set<ProductDescription> descriptions = product.getDescriptions();
		var indexList = new ArrayList<ProductSearch>();
		for (ProductDescription description : descriptions) {
			ProductSearch index = new ProductSearch();
			index.setId(String.valueOf(product.getId()));
			index.setStore(store.getCode().toLowerCase());
			index.setLanguage(description.getLanguage().getCode());
			index.setAvailable(product.isAvailable());
			index.setDescriptions(description.getDescription());
			if (product.getManufacturer() != null) {
				index.setManufacturer(String.valueOf(product.getManufacturer().getId()));
			}
			if (price != null) {
				index.setPrice(price.getFinalPrice().doubleValue());
			}
			index.setHighlight(description.getProductHighlight());
			if (!StringUtils.isBlank(description.getMetatagKeywords())) {
				String[] tags = description.getMetatagKeywords().split(",");
				var tagList = new ArrayList<String>(Arrays.asList(tags));
				index.setTags(tagList);
			}
			
			Set<Category> categories = product.getCategories();
			if (!CollectionUtils.isEmpty(categories)) {
				var categoryList = new ArrayList<String>();
				for (Category category : categories) {
					categoryList.add(category.getCode());
				}
				index.setCategories(categoryList);
			}
			indexList.add(index);
		}
		if (indexList.size() > 0)
			searchRepository.saveAll(indexList);
				
	}
}
