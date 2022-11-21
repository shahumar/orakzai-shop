package org.orakzai.lab.shop.domain.business.catalog.product.model.relationship;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductRelationship.class)
public abstract class ProductRelationship_ {

	public static volatile SingularAttribute<ProductRelationship, Product> product;
	public static volatile SingularAttribute<ProductRelationship, String> code;
	public static volatile SingularAttribute<ProductRelationship, Product> relatedProduct;
	public static volatile SingularAttribute<ProductRelationship, Boolean> active;
	public static volatile SingularAttribute<ProductRelationship, Long> id;
	public static volatile SingularAttribute<ProductRelationship, MerchantStore> store;

	public static final String PRODUCT = "product";
	public static final String CODE = "code";
	public static final String RELATED_PRODUCT = "relatedProduct";
	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String STORE = "store";

}

