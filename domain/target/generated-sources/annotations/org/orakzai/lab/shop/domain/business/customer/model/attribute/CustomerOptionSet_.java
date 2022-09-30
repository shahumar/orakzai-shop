package org.orakzai.lab.shop.domain.business.customer.model.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerOptionSet.class)
public abstract class CustomerOptionSet_ {

	public static volatile SingularAttribute<CustomerOptionSet, CustomerOptionValue> customerOptionValue;
	public static volatile SingularAttribute<CustomerOptionSet, Integer> sortOrder;
	public static volatile SingularAttribute<CustomerOptionSet, Long> id;
	public static volatile SingularAttribute<CustomerOptionSet, CustomerOption> customerOption;

	public static final String CUSTOMER_OPTION_VALUE = "customerOptionValue";
	public static final String SORT_ORDER = "sortOrder";
	public static final String ID = "id";
	public static final String CUSTOMER_OPTION = "customerOption";

}

