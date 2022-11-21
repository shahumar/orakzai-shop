package org.orakzai.lab.shop.domain.business.catalog.product.model.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductOption.class)
public abstract class ProductOption_ {

	public static volatile SingularAttribute<ProductOption, String> code;
	public static volatile SingularAttribute<ProductOption, Integer> productOptionSortOrder;
	public static volatile SingularAttribute<ProductOption, Boolean> readOnly;
	public static volatile SingularAttribute<ProductOption, Long> id;
	public static volatile SingularAttribute<ProductOption, String> productOptionType;
	public static volatile SingularAttribute<ProductOption, MerchantStore> merchantStore;
	public static volatile SetAttribute<ProductOption, ProductOptionDescription> descriptions;

	public static final String CODE = "code";
	public static final String PRODUCT_OPTION_SORT_ORDER = "productOptionSortOrder";
	public static final String READ_ONLY = "readOnly";
	public static final String ID = "id";
	public static final String PRODUCT_OPTION_TYPE = "productOptionType";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String DESCRIPTIONS = "descriptions";

}

