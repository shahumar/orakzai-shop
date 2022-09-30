package org.orakzai.lab.shop.domain.business.catalog.product.model.price;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductPrice.class)
public abstract class ProductPrice_ {

	public static volatile SingularAttribute<ProductPrice, ProductAvailability> productAvailability;
	public static volatile SingularAttribute<ProductPrice, Boolean> defaultPrice;
	public static volatile SingularAttribute<ProductPrice, BigDecimal> productPriceSpecialAmount;
	public static volatile SingularAttribute<ProductPrice, ProductPriceType> productPriceType;
	public static volatile SingularAttribute<ProductPrice, String> code;
	public static volatile SingularAttribute<ProductPrice, LocalDate> productPriceSpecialEndDate;
	public static volatile SingularAttribute<ProductPrice, BigDecimal> productPriceAmount;
	public static volatile SingularAttribute<ProductPrice, LocalDate> productPriceSpecialStartDate;
	public static volatile SingularAttribute<ProductPrice, Long> id;
	public static volatile SetAttribute<ProductPrice, ProductPriceDescription> descriptions;

	public static final String PRODUCT_AVAILABILITY = "productAvailability";
	public static final String DEFAULT_PRICE = "defaultPrice";
	public static final String PRODUCT_PRICE_SPECIAL_AMOUNT = "productPriceSpecialAmount";
	public static final String PRODUCT_PRICE_TYPE = "productPriceType";
	public static final String CODE = "code";
	public static final String PRODUCT_PRICE_SPECIAL_END_DATE = "productPriceSpecialEndDate";
	public static final String PRODUCT_PRICE_AMOUNT = "productPriceAmount";
	public static final String PRODUCT_PRICE_SPECIAL_START_DATE = "productPriceSpecialStartDate";
	public static final String ID = "id";
	public static final String DESCRIPTIONS = "descriptions";

}

