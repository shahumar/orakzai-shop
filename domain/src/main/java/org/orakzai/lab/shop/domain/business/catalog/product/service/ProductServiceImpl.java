package org.orakzai.lab.shop.domain.business.catalog.product.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.category.service.CategoryService;
import org.orakzai.lab.shop.domain.business.catalog.common.CatalogServiceHelper;
import org.orakzai.lab.shop.domain.business.catalog.product.dao.ProductRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.dao.ProductSpecs;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductCriteria;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductList;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationship;
import org.orakzai.lab.shop.domain.business.catalog.product.service.attribute.ProductAttributeService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.availability.ProductAvailabilityService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.image.ProductImageService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.price.ProductPriceService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.relationship.ProductRelationshipService;
import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.ImageContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.search.service.ProductSearchService;
import org.orakzai.lab.shop.domain.utils.CoreConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Slf4j
@Service("productService")
public class ProductServiceImpl extends SalesManagerEntityServiceImpl<Long, Product> implements ProductService {

	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductAvailabilityService productAvailabilityService;

	@Autowired
	ProductPriceService productPriceService;

	@Autowired
	ProductAttributeService productAttributeService;

	@Autowired
	ProductRelationshipService productRelationshipService;

	@Autowired
	ProductSearchService searchService;

	@Autowired
	ProductImageService productImageService;

	@Autowired
	CoreConfiguration configuration;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		super(productRepository);
		this.productRepository = productRepository;
	}

	@Override
	public void addProductDescription(Product product, ProductDescription description) throws ServiceException {

		if (product.getDescriptions() == null) {
			product.setDescriptions(new HashSet<>());
		}

		product.getDescriptions().add(description);
		description.setProduct(product);
		update(product);
//        searchService.index(product.getMerchantStore(), product);
	}

	@Override
	public List<Product> getProducts(List<Long> categoryIds) throws ServiceException {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set ids = new HashSet(categoryIds);
//        return productDao.getProductsListByCategories(ids);
		return null;

	}

	@Override
	public List<Product> getProducts(List<Long> categoryIds, Language language) throws ServiceException {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set<Long> ids = new HashSet(categoryIds);
//        return productDao.getProductsListByCategories(ids, language);
		return null;

	}

	@Override
	public ProductDescription getProductDescription(Product product, Language language) {
		for (ProductDescription description : product.getDescriptions()) {
			if (description.getLanguage().equals(language)) {
				return description;
			}
		}
		return null;
	}

	@Override
	public Product getBySeUrl(MerchantStore store, String seUrl, Locale locale) {
//        return productDao.getBySeUrl(store, seUrl, locale);
		return null;
	}

	@Override
	public Product getProductForLocale(long productId, Language language, Locale locale) throws ServiceException {
//        Product product =  productDao.getProductForLocale(productId, language, locale);
//
//
//        CatalogServiceHelper.setToAvailability(product, locale);
//        CatalogServiceHelper.setToLanguage(product, language.getId());
//        return product;
		return null;
	}

	@Override
	public List<Product> getProductsForLocale(Category category, Language language, Locale locale)
			throws ServiceException {

		if (category == null) {
			throw new ServiceException("The category is null");
		}

		// Get the category list
		StringBuilder lineage = new StringBuilder().append(category.getLineage()).append(category.getId()).append("/");
		List<Category> categories = categoryService.listByLineage(category.getMerchantStore(), lineage.toString());
		Set<Long> categoryIds = new HashSet<Long>();
		for (Category c : categories) {

			categoryIds.add(c.getId());

		}

		categoryIds.add(category.getId());

		// Get products
//        List<Product> products = productDao.getProductsForLocale(category.getMerchantStore(), categoryIds, language, locale);

		// Filter availability

//        return products;
		return null;
	}

	@Transactional
	@Override
	public ProductList listByStore(MerchantStore store, Language language, ProductCriteria criteria) {
		Specification<Product> specs = ProductSpecs.getByStore(store);
		if (!CollectionUtils.isEmpty(criteria.getCategoryIds())) {
			specs = specs.and(ProductSpecs.getByCategoryIds(criteria.getCategoryIds()));
		}
		if (!CollectionUtils.isEmpty(criteria.getProductIds())) {
			specs = specs.and(ProductSpecs.getByIds(criteria.getProductIds()));
		}
		if (criteria.getManufacturerId() != null) {
			specs = specs.and(ProductSpecs.getByManufacturer(criteria.getManufacturerId()));
		}
		if (criteria.getAvailable() != null) {
			specs = specs.and(ProductSpecs.getProductByAvailability(criteria.getAvailable()));
		}
		if (!StringUtils.isBlank(criteria.getCode())) {
			specs = specs.and(ProductSpecs.getBySku(criteria.getCode()));
		}
		if (!StringUtils.isBlank(criteria.getProductName())) {
			specs = specs.and(ProductSpecs.getProductDescriptions(language, criteria.getProductName()));
		}

		Pageable pageable = PageRequest.of(criteria.getStartIndex(), 20);

		Page<Product> products = productRepository.findAll(specs, pageable);
		ProductList list = new ProductList();
		list.setProducts(products.toList());
		return list;
	}

	@Override
	public List<Product> listByStore(MerchantStore store) {

		return productRepository.findByMerchantStore(store);
	}

	@Override
	public List<Product> listByTaxClass(TaxClass taxClass) {
//        return productDao.listByTaxClass(taxClass);
		return null;
	}

	@Override
	public Product getByCode(String productCode, Language language) {
//        return productDao.getByCode(productCode, language);
		return null;
	}

	@Override
	public void delete(Product product) throws ServiceException {
		log.debug("Deleting product");
		Validate.notNull(product, "Product cannot be null");
		Validate.notNull(product.getMerchantStore(), "MerchantStore cannot be null in product");
		product = this.getById(product.getId());// Prevents detached entity error
		product.setCategories(null);

		Set<ProductImage> images = product.getImages();

		for (ProductImage image : images) {
//            productImageService.removeProductImage(image);
		}

		product.setImages(null);

		// related - featured
		List<ProductRelationship> relationships = productRelationshipService.listByProduct(product);
		for (ProductRelationship relationship : relationships) {
			productRelationshipService.delete(relationship);
		}

		super.delete(product);
//        searchService.deleteIndex(product.getMerchantStore(), product);

	}

	@Override
	public void create(Product product) throws ServiceException {
		super.create(product);
//        searchService.index(product.getMerchantStore(), product);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Product product) throws ServiceException {

		log.debug("Save or update product ");
		Validate.notNull(product, "product cannot be null");
		Validate.notNull(product.getAvailabilities(), "product must have at least one availability");
		Validate.notEmpty(product.getAvailabilities(), "product must have at least one availability");

		// List of original availabilities
		Set<ProductAvailability> originalAvailabilities = null;

		// List of original attributes
		Set<ProductAttribute> originalAttributes = null;

		// List of original reviews
		Set<ProductRelationship> originalRelationships = null;

		if (product.getId() != null && product.getId() > 0) {
			log.debug("Update product", product.getId());
			// get original product
			Product originalProduct = this.getById(product.getId());
			originalAvailabilities = originalProduct.getAvailabilities();
			originalAttributes = originalProduct.getAttributes();
			originalRelationships = originalProduct.getRelationships();
		} else {

			Set<ProductDescription> productDescriptions = product.getDescriptions();
			product.setDescriptions(null);

			super.create(product);
			for (ProductDescription productDescription : productDescriptions) {
				addProductDescription(product, productDescription);
			}
		}

		log.debug("Creating availabilities");

		// get availabilities
		Set<ProductAvailability> availabilities = product.getAvailabilities();
		List<Long> newAvailabilityIds = new ArrayList<>();
		if (availabilities != null && availabilities.size() > 0) {
			for (ProductAvailability availability : availabilities) {
				availability.setProduct(product);
				productAvailabilityService.saveOrUpdate(availability);
				newAvailabilityIds.add(availability.getId());
				// check prices
				Set<ProductPrice> prices = availability.getPrices();
				if (prices != null && prices.size() > 0) {

					for (ProductPrice price : prices) {
						price.setProductAvailability(availability);
						productPriceService.saveOrUpdate(price);
					}
				}
			}
		}

		// cleanup old availability
		if (originalAvailabilities != null) {
			for (ProductAvailability availability : originalAvailabilities) {
				if (!newAvailabilityIds.contains(availability.getId())) {
					productAvailabilityService.delete(availability);
				}
			}
		}

		log.debug("Creating attributes");
		List<Long> newAttributesIds = new ArrayList<Long>();
		if (product.getAttributes() != null && product.getAttributes().size() > 0) {
			Set<ProductAttribute> attributes = product.getAttributes();
			for (ProductAttribute attribute : attributes) {
				attribute.setProduct(product);
				productAttributeService.saveOrUpdate(attribute);
				newAttributesIds.add(attribute.getId());
			}
		}

		// cleanup old attributes
		if (originalAttributes != null) {
			for (ProductAttribute attribute : originalAttributes) {
				if (!newAttributesIds.contains(attribute.getId())) {
					productAttributeService.delete(attribute);
				}
			}
		}

		log.debug("Creating relationships");
		List<Long> newRelationshipIds = new ArrayList<Long>();
		if (product.getRelationships() != null && product.getRelationships().size() > 0) {
			Set<ProductRelationship> relationships = product.getRelationships();
			for (ProductRelationship relationship : relationships) {
				relationship.setProduct(product);
				productRelationshipService.saveOrUpdate(relationship);
				newRelationshipIds.add(relationship.getId());
			}
		}
		// cleanup old relationships
		if (originalRelationships != null) {
			for (ProductRelationship relationship : originalRelationships) {
				if (!newRelationshipIds.contains(relationship.getId())) {
					productRelationshipService.delete(relationship);
				}
			}
		}

		log.debug("Creating images");
		log.info("Creating images");

		// get images

		if (product.getId() != null && product.getId() > 0) {
			super.update(product);
		}

		searchService.index(product.getMerchantStore(), product);

	}

	@Override
	public void saveProductImage(ProductImage image) throws ServiceException {
		Set<ProductImage> originalProductImages = null;
		Set<ProductImage> images = image.getProduct().getImages();
		List<Long> newImageIds = new ArrayList<Long>();
		if (image != null) {
			InputStream inputStream = image.getImage();
			ImageContentFile cmsContentImage = new ImageContentFile();
			cmsContentImage.setFileName(image.getProductImage());
			cmsContentImage.setFile(inputStream);
			cmsContentImage.setFileContentType(FileContentType.PRODUCT);
			productImageService.addProductImage(image.getProduct(), image, cmsContentImage);
			newImageIds.add(image.getId());
			originalProductImages = image.getProduct().getImages();
		}

		if (originalProductImages != null) {
			for (ProductImage img : originalProductImages) {
				if (!newImageIds.contains(image.getId())) {
					productImageService.delete(image);
				}
			}
		}
	}
}
