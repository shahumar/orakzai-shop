package org.orakzai.lab.shop.domain.business.catalog.product.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.Join;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product_;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription_;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {

	public static Specification<Product> getByCategoryIds(List<Long> categoryIds) {
		return (root, query, builder) -> {
			return builder.in(root.get(Product_.CATEGORIES)).value(categoryIds);
		};
	}
	
	public static Specification<Product> getByIds(List<Long> ids) {
		return (root, query, builder) -> {
			return builder.in(root.get(Product_.ID)).value(ids);
		};
	}
	
	public static Specification<Product> getByStore(MerchantStore store) {
		return (root, query, builder) -> {
			return builder.equal(root.get(Product_.MERCHANT_STORE), store);
		};
	}
	
	public static Specification<Product> getByManufacturer(Long id) {
		return (root, query, builder) -> {
			return builder.equal(root.get(Product_.MANUFACTURER), id);
		};
	}
	
	public static Specification<Product> getBySku(String sku) {
		return (root, query, builder) -> {
			return builder.equal(root.get(Product_.SKU), sku);
		};
	}
	
	public static Specification<Product> getProductDescriptions(Language lang, String name) {
		return (root, query, builder) -> {
			Join<ProductDescription, Product> productDescriptions = root.join("descriptions");
			return builder.and(
					builder.equal(productDescriptions.get(ProductDescription_.LANGUAGE), lang.getId()),
					builder.like(productDescriptions.get(ProductDescription_.NAME), "%"+name+"%"));
		};
	}
	
	public static Specification<Product> getProductByAvailability(boolean value) {
		return (root, query, builder) -> {
			LocalDate dt = LocalDate.now();
		
			if (value) {
				return builder.and(
						builder.equal(root.get(Product_.AVAILABLE), value), 
						builder.lessThan(root.<LocalDate>get(Product_.DATE_AVAILABLE), dt));
			}
			return builder.or(
					builder.equal(root.get(Product_.AVAILABLE), value), 
					builder.greaterThan(root.<LocalDate>get(Product_.DATE_AVAILABLE), dt));
		};
	}
	
	
	
}
