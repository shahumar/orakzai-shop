package org.orakzai.lab.shop.domain.business.customer.model.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerOption.class)
public abstract class CustomerOption_ {

	public static volatile SingularAttribute<CustomerOption, String> code;
	public static volatile SingularAttribute<CustomerOption, Boolean> publicOption;
	public static volatile SingularAttribute<CustomerOption, Integer> sortOrder;
	public static volatile SingularAttribute<CustomerOption, Boolean> active;
	public static volatile SingularAttribute<CustomerOption, Long> id;
	public static volatile SingularAttribute<CustomerOption, MerchantStore> merchantStore;
	public static volatile SetAttribute<CustomerOption, CustomerOptionDescription> descriptions;
	public static volatile SingularAttribute<CustomerOption, String> customerOptionType;

	public static final String CODE = "code";
	public static final String PUBLIC_OPTION = "publicOption";
	public static final String SORT_ORDER = "sortOrder";
	public static final String ACTIVE = "active";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String CUSTOMER_OPTION_TYPE = "customerOptionType";

}

