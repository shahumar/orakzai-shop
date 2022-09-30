package org.orakzai.lab.shop.domain.business.customer.model.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CustomerAttribute.class)
public abstract class CustomerAttribute_ {

	public static volatile SingularAttribute<CustomerAttribute, String> textValue;
	public static volatile SingularAttribute<CustomerAttribute, CustomerOptionValue> customerOptionValue;
	public static volatile SingularAttribute<CustomerAttribute, Long> id;
	public static volatile SingularAttribute<CustomerAttribute, CustomerOption> customerOption;
	public static volatile SingularAttribute<CustomerAttribute, Customer> customer;

	public static final String TEXT_VALUE = "textValue";
	public static final String CUSTOMER_OPTION_VALUE = "customerOptionValue";
	public static final String ID = "id";
	public static final String CUSTOMER_OPTION = "customerOption";
	public static final String CUSTOMER = "customer";

}

