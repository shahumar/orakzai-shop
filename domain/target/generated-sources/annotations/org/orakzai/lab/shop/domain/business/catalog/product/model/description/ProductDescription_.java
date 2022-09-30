package org.orakzai.lab.shop.domain.business.catalog.product.model.description;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductDescription.class)
public abstract class ProductDescription_ extends org.orakzai.lab.shop.domain.business.common.model.Description_ {

	public static volatile SingularAttribute<ProductDescription, String> productExternalDl;
	public static volatile SingularAttribute<ProductDescription, Product> product;
	public static volatile SingularAttribute<ProductDescription, String> productHighlight;
	public static volatile SingularAttribute<ProductDescription, String> metatagDescription;
	public static volatile SingularAttribute<ProductDescription, String> seUrl;
	public static volatile SingularAttribute<ProductDescription, String> metatagKeywords;
	public static volatile SingularAttribute<ProductDescription, String> metatagTitle;

	public static final String PRODUCT_EXTERNAL_DL = "productExternalDl";
	public static final String PRODUCT = "product";
	public static final String PRODUCT_HIGHLIGHT = "productHighlight";
	public static final String METATAG_DESCRIPTION = "metatagDescription";
	public static final String SE_URL = "seUrl";
	public static final String METATAG_KEYWORDS = "metatagKeywords";
	public static final String METATAG_TITLE = "metatagTitle";

}

