package org.orakzai.lab.shop.domain.business.catalog.product.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.Manufacturer;
import org.orakzai.lab.shop.domain.business.catalog.product.model.relationship.ProductRelationship;
import org.orakzai.lab.shop.domain.business.catalog.product.model.type.ProductType;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Boolean> available;
	public static volatile SingularAttribute<Product, Integer> productReviewCount;
	public static volatile SingularAttribute<Product, ProductType> type;
	public static volatile SingularAttribute<Product, AuditSection> auditSection;
	public static volatile SetAttribute<Product, ProductDescription> descriptions;
	public static volatile SingularAttribute<Product, Manufacturer> manufacturer;
	public static volatile SetAttribute<Product, ProductRelationship> relationships;
	public static volatile SingularAttribute<Product, Boolean> productVirtual;
	public static volatile SingularAttribute<Product, BigDecimal> productWidth;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SetAttribute<Product, Category> categories;
	public static volatile SingularAttribute<Product, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Product, BigDecimal> productLength;
	public static volatile SingularAttribute<Product, String> sku;
	public static volatile SingularAttribute<Product, TaxClass> taxClass;
	public static volatile SetAttribute<Product, ProductImage> images;
	public static volatile SingularAttribute<Product, BigDecimal> productHeight;
	public static volatile SetAttribute<Product, ProductAvailability> availabilities;
	public static volatile SingularAttribute<Product, Boolean> productShipeable;
	public static volatile SingularAttribute<Product, BigDecimal> productWeight;
	public static volatile SingularAttribute<Product, Integer> productOrdered;
	public static volatile SingularAttribute<Product, BigDecimal> productReviewAvg;
	public static volatile SingularAttribute<Product, LocalDate> dateAvailable;
	public static volatile SingularAttribute<Product, Integer> sortOrder;
	public static volatile SingularAttribute<Product, Boolean> productIsFree;
	public static volatile SetAttribute<Product, ProductAttribute> attributes;

	public static final String AVAILABLE = "available";
	public static final String PRODUCT_REVIEW_COUNT = "productReviewCount";
	public static final String TYPE = "type";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String MANUFACTURER = "manufacturer";
	public static final String RELATIONSHIPS = "relationships";
	public static final String PRODUCT_VIRTUAL = "productVirtual";
	public static final String PRODUCT_WIDTH = "productWidth";
	public static final String ID = "id";
	public static final String CATEGORIES = "categories";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String PRODUCT_LENGTH = "productLength";
	public static final String SKU = "sku";
	public static final String TAX_CLASS = "taxClass";
	public static final String IMAGES = "images";
	public static final String PRODUCT_HEIGHT = "productHeight";
	public static final String AVAILABILITIES = "availabilities";
	public static final String PRODUCT_SHIPEABLE = "productShipeable";
	public static final String PRODUCT_WEIGHT = "productWeight";
	public static final String PRODUCT_ORDERED = "productOrdered";
	public static final String PRODUCT_REVIEW_AVG = "productReviewAvg";
	public static final String DATE_AVAILABLE = "dateAvailable";
	public static final String SORT_ORDER = "sortOrder";
	public static final String PRODUCT_IS_FREE = "productIsFree";
	public static final String ATTRIBUTES = "attributes";

}

