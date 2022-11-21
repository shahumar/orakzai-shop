package org.orakzai.lab.shop.domain.business.catalog.product.model.file;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DigitalProduct.class)
public abstract class DigitalProduct_ {

	public static volatile SingularAttribute<DigitalProduct, Product> product;
	public static volatile SingularAttribute<DigitalProduct, String> productFileName;
	public static volatile SingularAttribute<DigitalProduct, Long> id;

	public static final String PRODUCT = "product";
	public static final String PRODUCT_FILE_NAME = "productFileName";
	public static final String ID = "id";

}

