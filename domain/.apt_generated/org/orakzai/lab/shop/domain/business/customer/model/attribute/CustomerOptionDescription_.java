package org.orakzai.lab.shop.domain.business.customer.model.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerOptionDescription.class)
public abstract class CustomerOptionDescription_ extends org.orakzai.lab.shop.domain.business.common.model.Description_ {

	public static volatile SingularAttribute<CustomerOptionDescription, String> customerOptionComment;
	public static volatile SingularAttribute<CustomerOptionDescription, CustomerOption> customerOption;

	public static final String CUSTOMER_OPTION_COMMENT = "customerOptionComment";
	public static final String CUSTOMER_OPTION = "customerOption";

}

