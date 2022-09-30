package org.orakzai.lab.shop.domain.business.common.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Delivery.class)
public abstract class Delivery_ {

	public static volatile SingularAttribute<Delivery, String> lastName;
	public static volatile SingularAttribute<Delivery, String> firstName;
	public static volatile SingularAttribute<Delivery, Country> country;
	public static volatile SingularAttribute<Delivery, String> address;
	public static volatile SingularAttribute<Delivery, String> city;
	public static volatile SingularAttribute<Delivery, Zone> zone;
	public static volatile SingularAttribute<Delivery, String> postalCode;
	public static volatile SingularAttribute<Delivery, String> company;
	public static volatile SingularAttribute<Delivery, String> telephone;
	public static volatile SingularAttribute<Delivery, String> state;

	public static final String LAST_NAME = "lastName";
	public static final String FIRST_NAME = "firstName";
	public static final String COUNTRY = "country";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String ZONE = "zone";
	public static final String POSTAL_CODE = "postalCode";
	public static final String COMPANY = "company";
	public static final String TELEPHONE = "telephone";
	public static final String STATE = "state";

}

