package org.orakzai.lab.shop.domain.business.catalog.product.model.availability;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductAvailability.class)
public abstract class ProductAvailability_ {

	public static volatile SingularAttribute<ProductAvailability, Product> product;
	public static volatile SingularAttribute<ProductAvailability, Integer> productQuantity;
	public static volatile SingularAttribute<ProductAvailability, LocalDate> productDateAvailable;
	public static volatile SingularAttribute<ProductAvailability, String> regionVariant;
	public static volatile SingularAttribute<ProductAvailability, Integer> productQuantityOrderMin;
	public static volatile SingularAttribute<ProductAvailability, Boolean> productStatus;
	public static volatile SingularAttribute<ProductAvailability, Integer> productQuantityOrderMax;
	public static volatile SingularAttribute<ProductAvailability, Long> id;
	public static volatile SingularAttribute<ProductAvailability, String> region;
	public static volatile SetAttribute<ProductAvailability, ProductPrice> prices;
	public static volatile SingularAttribute<ProductAvailability, Boolean> productIsAlwaysFreeShipping;

	public static final String PRODUCT = "product";
	public static final String PRODUCT_QUANTITY = "productQuantity";
	public static final String PRODUCT_DATE_AVAILABLE = "productDateAvailable";
	public static final String REGION_VARIANT = "regionVariant";
	public static final String PRODUCT_QUANTITY_ORDER_MIN = "productQuantityOrderMin";
	public static final String PRODUCT_STATUS = "productStatus";
	public static final String PRODUCT_QUANTITY_ORDER_MAX = "productQuantityOrderMax";
	public static final String ID = "id";
	public static final String REGION = "region";
	public static final String PRICES = "prices";
	public static final String PRODUCT_IS_ALWAYS_FREE_SHIPPING = "productIsAlwaysFreeShipping";

}

