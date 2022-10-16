package org.orakzai.lab.shop.web.admin.controller.products;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.category.service.CategoryService;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductCriteria;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductList;
import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImageDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.Manufacturer;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPriceDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType;
import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.manufacturer.ManufacturerService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.type.ProductTypeService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.orakzai.lab.shop.domain.business.tax.service.TaxClassService;
import org.orakzai.lab.shop.domain.utils.CoreConfiguration;
import org.orakzai.lab.shop.domain.utils.ProductPriceUtils;
import org.orakzai.lab.shop.domain.utils.ajax.AjaxPageableResponse;
import org.orakzai.lab.shop.domain.utils.ajax.AjaxResponse;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.menu.Menu;
import org.orakzai.lab.shop.web.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ManufacturerService manufacturerService;

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private TaxClassService taxClassService;
	
	@Autowired
	private ProductPriceUtils priceUtil;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private CoreConfiguration configuration;
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@GetMapping("/admin/products/products.html")
	public String displayProducts(Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setMenu(model, req);
		Language language = (Language) req.getAttribute(Constants.LANGUAGE);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		
		List<Category> categories = categoryService.listByStore(store, language);
		
		model.addAttribute("categories", categories);
		
		return ControllerConstants.Templates.DASHBOARD_PRODUCTS_TPL;
	}
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@PostMapping(value="/admin/products/paging.html", produces="application/json")
	public @ResponseBody String pageProducts(HttpServletRequest req, HttpServletResponse resp) {
		String categoryId = req.getParameter("categoryId");
		String sku = req.getParameter("sku");
		String available = req.getParameter("available");
		String searchTerm = req.getParameter("searchTerm");
		String name = req.getParameter("name");
		
		AjaxPageableResponse response = new AjaxPageableResponse();
		try {
			int startRow = Integer.parseInt(req.getParameter("_startRow"));
			int endRow = Integer.parseInt(req.getParameter("_endRow"));
			
			ProductCriteria criteria = new ProductCriteria();
			Language lang = (Language) req.getAttribute(Constants.LANGUAGE);
			MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
			criteria.setStartIndex(startRow);
			criteria.setMaxCount(endRow);
			if (!StringUtils.isBlank(categoryId) && !categoryId.equals("-1")) {
				Long lcategoryId = 0L;
				try {
					lcategoryId = Long.parseLong(categoryId);
				} catch (Exception e) {
					log.error("Product page cannot parse categoryId " + categoryId);
					response.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
					String returnString = response.toJSONString();
					return returnString;
				}
				
				if (lcategoryId > 0) {
					Category category = categoryService.getById(lcategoryId);
					if (category == null || category.getMerchantStore().getId() != store.getId()) {
						response.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
						String returnString = response.toJSONString();
						return returnString;
						
					}
					
					
					StringBuilder lineage = new StringBuilder();
					lineage.append(category.getLineage()).append(category.getId()).append("/");
					List<Category> categories = categoryService.listByLineage(store, lineage.toString());
					var categoryIds = Optional.ofNullable(categories)
							.orElse(Collections.emptyList())
							.stream()
							.map(cat -> cat.getId())
							.collect(Collectors.toList());
					
					categoryIds.add(category.getId());
					criteria.setCategoryIds(categoryIds);
					
				}
			}
			
			if (!StringUtils.isBlank(sku)) {
				criteria.setCode(sku);
			}
			
			if (!StringUtils.isBlank(name)) {
				criteria.setProductName(name);
			}
			
			if (!StringUtils.isBlank(available)) {
				if (available.equals("true")) {
					criteria.setAvailable(Boolean.valueOf(true));
				} else {
					criteria.setAvailable(Boolean.valueOf(false));
				}
			}
			
			ProductList productList = productService.listByStore(store, lang, criteria);
			response.setEndRow(productList.getTotalCount());
			response.setStartRow(startRow);
			List<Product> plist = productList.getProducts();
			
			Optional.ofNullable(plist)
				.orElse(Collections.emptyList())
				.stream()
				.map(product -> {
					var description = product.getDescriptions().iterator().next();
					Map<String, String> map = Map.of(
							"productId", product.getId(), 
							"name", description.getName(),
							"sku", product.getSku(),
							"available", product.isAvailable());
					return map;
				})
				.forEach(entry -> response.addDataEntry(entry));
			
			if (plist != null) {
				for (Product product : plist) {
					var entry = new HashMap();
					entry.put("productId", product.getId());
					ProductDescription description = product.getDescriptions().iterator().next();
					entry.put("name", description.getName());
					entry.put("sku", product.getSku());
					entry.put("available", product.isAvailable());
					response.addDataEntry(entry);
				}
			}
			response.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
			
		} catch (Exception e) {
			log.error("Error while paging products", e);
			response.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			response.setErrorMessage(e);
		}
		String returnString  = response.toJSONString();
		
		return returnString;
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@GetMapping("/admin/products/createProduct.html")
	public String displayProductCreate(Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return displayProduct(null, model, req, resp);
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@GetMapping("/admin/products/editProduct.html")
	public String editProduct(@RequestParam("id") long productId, Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return displayProduct(productId, model, req, resp);
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@PostMapping("/admin/products/save.html")
	public String saveProduct(@Valid @ModelAttribute("product") org.orakzai.lab.shop.web.admin.dto.catalog.Product product, BindingResult result, Model model, HttpServletRequest req, Locale locale) throws Exception {
		Language lang = (Language) req.getAttribute(Constants.LANGUAGE);
		setMenu(model, req);
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		List<Manufacturer> manufacturers = manufacturerService.listByStore(store, lang);
		List<ProductType> productTypes = productTypeService.list();
		List<TaxClass> taxClasses = taxClassService.listByStore(store);
		List<Language> languages = store.getLanguages();
		model.addAttribute("manufacturers", manufacturers);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("taxClasses", taxClasses);
		
		BigDecimal submittedPrice = null;
		try {
			submittedPrice = priceUtil.getAmount(product.getProductPrice());
		} catch (Exception e) {
			ObjectError error = new ObjectError("productPrice", messages.getMessage("NotEmpty.product.productPrice", null, null, locale));
			result.addError(error);
		}
		
		LocalDate date = LocalDate.now();
		if(!StringUtils.isBlank(product.getDateAvailable())) {
			try {
				date = DateUtil.getDate(product.getDateAvailable());
				product.getAvailability().setProductDateAvailable(date);
				product.setDateAvailable(DateUtil.formatDate(date));
			} catch (Exception e) {
				ObjectError error = new ObjectError("dateAvailable", messages.getMessage("message.invalid.date", null, locale));
				result.addError(error);
			}
		}
		System.out.println(product.getImage());
		if (product.getImage() != null && !product.getImage().isEmpty()) {
			log.info("Image is not empty");
			try {
				String maxHeight = configuration.getProperty("PRODUCT_IMAGE_MAX_HEIGHT_SIZE");
				String maxWidth = configuration.getProperty("PRODUCT_IMAGE_MAX_WIDTH_SIZE");
				String maxSize = configuration.getProperty("PRODUCT_IMAGE_MAX_SIZE");
				BufferedImage image = ImageIO.read(product.getImage().getInputStream());
				if (!StringUtils.isBlank(maxHeight)) {
					int maxImageHeight = Integer.parseInt(maxHeight);
					if (image.getHeight() > maxImageHeight) {
						ObjectError error = new ObjectError("image", messages.getMessage("message.image.height", null, locale) + " {" +maxHeight+"}");
						result.addError(error);
					}
				}
				if (!StringUtils.isBlank(maxWidth)) {
					int maxImageWidth = Integer.parseInt(maxWidth);
					if (image.getWidth() > maxImageWidth) {
						ObjectError error = new ObjectError("image", messages.getMessage("message.image.width", null, locale) +" {"+maxWidth+"}");
						result.addError(error);
					}
				}
				
				if (!StringUtils.isAllBlank(maxSize)) {
					int maxImageSize = Integer.parseInt(maxSize);
					if (product.getImage().getSize() > maxImageSize) {
						ObjectError error = new ObjectError("image", messages.getMessage("message.image.size", null, locale) + " {"+maxSize+"}");
					}
				}
				
			} catch (Exception e) {
				log.error("Cannot validate product");
			}
		}
		if (result.hasErrors()) {
			return ControllerConstants.Templates.DASHBOARD_PRODUCTS_EDIT_TPL;
		}
		
		Product newProduct = product.getProduct();
		ProductAvailability newProductAvailability = null;
		ProductPrice newProductPrice = null;
		Set<ProductPriceDescription> productPriceDescriptions = null;
		var prices = new HashSet<ProductPrice>();
		var availabilities = new HashSet<ProductAvailability>();
		
		if (product.getProduct().getId() != null && product.getProduct().getId().longValue() > 0) {
			newProduct = productService.getById(product.getProduct().getId());
			if (newProduct != null && newProduct.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				return "redirect:/admin/products/products.html";
			}
			
			newProduct.setSku(product.getProduct().getSku());
			newProduct.setAvailable(product.getProduct().isAvailable());
			newProduct.setDateAvailable(date);
			newProduct.setManufacturer(product.getProduct().getManufacturer());
			newProduct.setType(product.getProduct().getType());
			newProduct.setProductHeight(product.getProduct().getProductHeight());
			newProduct.setProductLength(product.getProduct().getProductLength());
			newProduct.setProductWeight(product.getProduct().getProductWeight());
			newProduct.setProductWidth(product.getProduct().getProductWidth());
			newProduct.setProductVirtual(product.getProduct().isProductVirtual());
			newProduct.setProductShipeable(product.getProduct().isProductShipeable());
			newProduct.setTaxClass(product.getProduct().getTaxClass());
			
			var avails = newProduct.getAvailabilities();
			if (avails != null && avails.size() > 0) {
				for (ProductAvailability availability : avails) {
					if (availability.getRegion().equals(org.orakzai.lab.shop.domain.constants.Constants.ALL_REGIONS)) {
						newProductAvailability = availability;
						Set<ProductPrice> productPrices = availability.getPrices();
						for (ProductPrice price : productPrices) {
							if (price.isDefaultPrice()) {
								newProductPrice = price;
								newProductPrice.setProductPriceAmount(submittedPrice);
								productPriceDescriptions = price.getDescriptions();
							} else {
								prices.add(price);
							}
						}
					} else {
						availabilities.add(availability);
					}
					
				}
			}
			
			for (ProductImage image : newProduct.getImages()) {
				if (image.isDefaultImage()) {
					product.setProductImage(image);
				}
			}
		}
		
		if (newProductPrice == null) {
			newProductPrice = new ProductPrice();
			newProductPrice.setDefaultPrice(true);
			newProductPrice.setProductPriceAmount(submittedPrice);
		}
		
		if (product.getProductImage() != null && product.getProductImage().getId() == null) {
			product.setProductImage(null);
		}
		
		if (productPriceDescriptions == null) {
			productPriceDescriptions = new HashSet<ProductPriceDescription>();
			for (ProductDescription description : product.getDescriptions()) {
				ProductPriceDescription ppd = new ProductPriceDescription();
				ppd.setProductPrice(newProductPrice);
				ppd.setLanguage(description.getLanguage());
				ppd.setName(ProductPriceDescription.DEFAULT_PRICE_DESCRIPTION);
				productPriceDescriptions.add(ppd);
			}
			newProductPrice.setDescriptions(productPriceDescriptions);
		}
		
		newProduct.setMerchantStore(store);
		if (newProductAvailability == null) {
			newProductAvailability = new ProductAvailability();
			
		}
		newProductAvailability.setProductQuantity(product.getAvailability().getProductQuantity());
		newProductAvailability.setProductQuantityOrderMin(product.getAvailability().getProductQuantityOrderMin());
		newProductAvailability.setProductQuantityOrderMax(product.getAvailability().getProductQuantityOrderMax());
		newProductAvailability.setProduct(newProduct);
		newProductAvailability.setPrices(prices);
		availabilities.add(newProductAvailability);
		prices.add(newProductPrice);
		newProduct.setAvailabilities(availabilities);
		var descriptions = new HashSet<ProductDescription>();
		if (product.getDescriptions() != null && product.getDescriptions().size()>0) {
			for (ProductDescription description : product.getDescriptions()) {
				description.setProduct(newProduct);
				descriptions.add(description);
			}
		}
		newProduct.setDescriptions(descriptions);
		product.setDateAvailable(DateUtil.formatDate(date));
		productService.saveOrUpdate(newProduct);
		if (product.getImage() != null && !product.getImage().isEmpty()) {
			String imageName = product.getImage().getOriginalFilename();
			ProductImage productImage = new ProductImage();
			productImage.setDefaultImage(true);	
			productImage.setProductImage(imageName);
			productImage.setImage(product.getImage().getInputStream());
			var imageDescriptions = new ArrayList<ProductImageDescription>();
			for (Language l : languages) {
				ProductImageDescription imageDescription = new ProductImageDescription();
				imageDescription.setName(imageName);
				imageDescription.setLanguage(l);
				imageDescription.setProductImage(productImage);
				imageDescriptions.add(imageDescription);
			}
			productImage.setDescriptions(imageDescriptions);
			productImage.setProduct(newProduct);
			productService.saveProductImage(productImage);
			product.setProductImage(productImage);
			
		} 
		model.addAttribute("success", "success");
		
		return ControllerConstants.Templates.DASHBOARD_PRODUCTS_EDIT_TPL;
	
	}

	private String displayProduct(Long productId, Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setMenu(model, req);
		
		MerchantStore store = (MerchantStore) req.getAttribute(Constants.ADMIN_STORE);
		Language lang = (Language) req.getAttribute(Constants.LANGUAGE);
		
		List<Manufacturer> manufacturers = manufacturerService.listByStore(store, lang);
		List<ProductType> productTypes = productTypeService.list();
		List<TaxClass> taxClasses = taxClassService.listByStore(store);
		List<Language> languages = store.getLanguages();
		
		org.orakzai.lab.shop.web.admin.dto.catalog.Product product = new org.orakzai.lab.shop.web.admin.dto.catalog.Product();
		var descriptions = new ArrayList<ProductDescription>();
		if (productId != null && productId != 0) {
			Product dbProduct = productService.getById(productId);
			if (dbProduct == null || dbProduct.getMerchantStore().getId().intValue() != store.getId().intValue()) {
				return "redirect:/admin/products/products.html";
			}
			product.setProduct(dbProduct);
			Set<ProductDescription> productDescriptions = dbProduct.getDescriptions();
			for (Language l : languages ) {
				ProductDescription productDesc = null;
				for (ProductDescription desc : productDescriptions) {
					Language lan = desc.getLanguage();
					if (lan.getCode().equals(l.getCode())) {
						productDesc = desc;
					}
				}
				if (productDesc == null) {
					productDesc = new ProductDescription();
					productDesc.setLanguage(l);
				}
				descriptions.add(productDesc);
			}
			for (ProductImage image : dbProduct.getImages()) {
				if (image.isDefaultImage()) {
					product.setProductImage(image);
					break;
				}
			}
			
			ProductAvailability productAvailability = null;
			ProductPrice productPrice = null;
			Set<ProductAvailability> availabilities = dbProduct.getAvailabilities();
			if (availabilities != null && availabilities.size() > 0) {
				for (ProductAvailability availability : availabilities) {
					if (availability.getRegion().equals(org.orakzai.lab.shop.domain.constants.Constants.ALL_REGIONS)) {
						productAvailability = availability;
						Set<ProductPrice> prices = availability.getPrices();
						for (ProductPrice price : prices) {
							if (price.isDefaultPrice()) {
								productPrice = price;
								product.setProductPrice(priceUtil.getAdminFormatedAmount(store, productPrice.getProductPriceAmount()));
							}
						}
					}
					
				}
			}
			if (productAvailability == null) {
				productAvailability = new ProductAvailability();
			}
			if (productPrice == null) {
				productPrice = new ProductPrice();
			}
			product.setAvailability(productAvailability);
			product.setPrice(productPrice);
			product.setDescriptions(descriptions);
			product.setDateAvailable(DateUtil.formatDate(dbProduct.getDateAvailable()));
			
		} else {
			for (Language l : languages) {
				ProductDescription desc = new ProductDescription();
				desc.setLanguage(l);
				descriptions.add(desc);
			}
			
			Product prod = new Product();
			prod.setAvailable(true);
			ProductAvailability pAvailability = new ProductAvailability();
			ProductPrice pPrice = new ProductPrice();
			product.setPrice(pPrice);
			product.setAvailability(pAvailability);
			product.setProduct(prod);
			product.setDescriptions(descriptions);
			product.setDateAvailable(DateUtil.formatDate(LocalDate.now()));
		}
		model.addAttribute("product", product);
		model.addAttribute("manufacturers", manufacturers);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("taxClasses", taxClasses);
		return ControllerConstants.Templates.PRODUCT_FORM_TPL;
	}


	private void setMenu(Model model, HttpServletRequest req) {

		var activeMenus = new HashMap<String, String>();
		activeMenus.put("catalogue", "catalogue");
		activeMenus.put("catalogue-products", "catalogue-products");
		
		@SuppressWarnings("unchecked")
		var menus = (Map<String, Menu>) req.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu) menus.get("catalogue");
		
		model.addAttribute("currentMenu", currentMenu);
		model.addAttribute("activeMenus", activeMenus);
	}

}
