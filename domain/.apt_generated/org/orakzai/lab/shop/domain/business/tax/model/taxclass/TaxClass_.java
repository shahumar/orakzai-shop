package org.orakzai.lab.shop.domain.business.tax.model.taxclass;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.model.taxrate.TaxRate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TaxClass.class)
public abstract class TaxClass_ {

	public static volatile SingularAttribute<TaxClass, String> code;
	public static volatile ListAttribute<TaxClass, TaxRate> taxRates;
	public static volatile SingularAttribute<TaxClass, Long> id;
	public static volatile SingularAttribute<TaxClass, String> title;
	public static volatile SingularAttribute<TaxClass, MerchantStore> merchantStore;
	public static volatile ListAttribute<TaxClass, Product> products;

	public static final String CODE = "code";
	public static final String TAX_RATES = "taxRates";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String PRODUCTS = "products";

}

