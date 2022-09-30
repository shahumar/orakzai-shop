package org.orakzai.lab.shop.domain.business.reference.currency.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Currency.class)
public abstract class Currency_ {

	public static volatile SingularAttribute<Currency, String> code;
	public static volatile SingularAttribute<Currency, String> name;
	public static volatile SingularAttribute<Currency, java.util.Currency> currency;
	public static volatile SingularAttribute<Currency, Long> id;
	public static volatile SingularAttribute<Currency, Boolean> supported;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String CURRENCY = "currency";
	public static final String ID = "id";
	public static final String SUPPORTED = "supported";

}

