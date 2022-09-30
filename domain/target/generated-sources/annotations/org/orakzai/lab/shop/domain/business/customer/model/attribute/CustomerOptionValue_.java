package org.orakzai.lab.shop.domain.business.customer.model.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerOptionValue.class)
public abstract class CustomerOptionValue_ {

	public static volatile SingularAttribute<CustomerOptionValue, String> customerOptionValueImage;
	public static volatile SingularAttribute<CustomerOptionValue, String> code;
	public static volatile SingularAttribute<CustomerOptionValue, Integer> sortOrder;
	public static volatile SingularAttribute<CustomerOptionValue, Long> id;
	public static volatile SingularAttribute<CustomerOptionValue, MerchantStore> merchantStore;
	public static volatile SetAttribute<CustomerOptionValue, CustomerOptionValueDescription> descriptions;

	public static final String CUSTOMER_OPTION_VALUE_IMAGE = "customerOptionValueImage";
	public static final String CODE = "code";
	public static final String SORT_ORDER = "sortOrder";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String DESCRIPTIONS = "descriptions";

}

