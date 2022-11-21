package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductOptionValue.class)
public abstract class ProductOptionValue_ {

	public static volatile SingularAttribute<ProductOptionValue, String> code;
	public static volatile SingularAttribute<ProductOptionValue, String> productOptionValueImage;
	public static volatile SingularAttribute<ProductOptionValue, Long> id;
	public static volatile SingularAttribute<ProductOptionValue, Integer> productOptionValueSortOrder;
	public static volatile SingularAttribute<ProductOptionValue, Boolean> productOptionDisplayOnly;
	public static volatile SingularAttribute<ProductOptionValue, MerchantStore> merchantStore;
	public static volatile SetAttribute<ProductOptionValue, ProductOptionValueDescription> descriptions;

	public static final String CODE = "code";
	public static final String PRODUCT_OPTION_VALUE_IMAGE = "productOptionValueImage";
	public static final String ID = "id";
	public static final String PRODUCT_OPTION_VALUE_SORT_ORDER = "productOptionValueSortOrder";
	public static final String PRODUCT_OPTION_DISPLAY_ONLY = "productOptionDisplayOnly";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String DESCRIPTIONS = "descriptions";

}

