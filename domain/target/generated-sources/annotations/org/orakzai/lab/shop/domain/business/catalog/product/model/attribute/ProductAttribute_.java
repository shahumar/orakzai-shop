package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductAttribute.class)
public abstract class ProductAttribute_ {

	public static volatile SingularAttribute<ProductAttribute, BigDecimal> productAttributeWeight;
	public static volatile SingularAttribute<ProductAttribute, ProductOption> productOption;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeDisplayOnly;
	public static volatile SingularAttribute<ProductAttribute, Product> product;
	public static volatile SingularAttribute<ProductAttribute, ProductOptionValue> productOptionValue;
	public static volatile SingularAttribute<ProductAttribute, Integer> productOptionSortOrder;
	public static volatile SingularAttribute<ProductAttribute, Boolean> productAttributeIsFree;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeDiscounted;
	public static volatile SingularAttribute<ProductAttribute, BigDecimal> productAttributePrice;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeRequired;
	public static volatile SingularAttribute<ProductAttribute, Long> id;
	public static volatile SingularAttribute<ProductAttribute, Boolean> attributeDefault;

	public static final String PRODUCT_ATTRIBUTE_WEIGHT = "productAttributeWeight";
	public static final String PRODUCT_OPTION = "productOption";
	public static final String ATTRIBUTE_DISPLAY_ONLY = "attributeDisplayOnly";
	public static final String PRODUCT = "product";
	public static final String PRODUCT_OPTION_VALUE = "productOptionValue";
	public static final String PRODUCT_OPTION_SORT_ORDER = "productOptionSortOrder";
	public static final String PRODUCT_ATTRIBUTE_IS_FREE = "productAttributeIsFree";
	public static final String ATTRIBUTE_DISCOUNTED = "attributeDiscounted";
	public static final String PRODUCT_ATTRIBUTE_PRICE = "productAttributePrice";
	public static final String ATTRIBUTE_REQUIRED = "attributeRequired";
	public static final String ID = "id";
	public static final String ATTRIBUTE_DEFAULT = "attributeDefault";

}

