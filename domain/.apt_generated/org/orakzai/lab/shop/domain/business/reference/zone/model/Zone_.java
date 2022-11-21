package org.orakzai.lab.shop.domain.business.reference.zone.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Zone.class)
public abstract class Zone_ {

	public static volatile SingularAttribute<Zone, Country> country;
	public static volatile SingularAttribute<Zone, String> code;
	public static volatile SingularAttribute<Zone, Long> id;
	public static volatile ListAttribute<Zone, ZoneDescription> descriptions;

	public static final String COUNTRY = "country";
	public static final String CODE = "code";
	public static final String ID = "id";
	public static final String DESCRIPTIONS = "descriptions";

}

