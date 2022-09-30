package org.orakzai.lab.shop.domain.business.catalog.product.model.image;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductImage.class)
public abstract class ProductImage_ {

	public static volatile SingularAttribute<ProductImage, Product> product;
	public static volatile SingularAttribute<ProductImage, String> productImage;
	public static volatile SingularAttribute<ProductImage, Long> id;
	public static volatile SingularAttribute<ProductImage, Boolean> imageCrop;
	public static volatile ListAttribute<ProductImage, ProductImageDescription> descriptions;
	public static volatile SingularAttribute<ProductImage, Integer> imageType;
	public static volatile SingularAttribute<ProductImage, Boolean> defaultImage;

	public static final String PRODUCT = "product";
	public static final String PRODUCT_IMAGE = "productImage";
	public static final String ID = "id";
	public static final String IMAGE_CROP = "imageCrop";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String IMAGE_TYPE = "imageType";
	public static final String DEFAULT_IMAGE = "defaultImage";

}

