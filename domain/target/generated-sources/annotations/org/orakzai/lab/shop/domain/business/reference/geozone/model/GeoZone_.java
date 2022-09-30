package org.orakzai.lab.shop.domain.business.reference.geozone.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GeoZone.class)
public abstract class GeoZone_ {

	public static volatile SingularAttribute<GeoZone, String> code;
	public static volatile SingularAttribute<GeoZone, String> name;
	public static volatile SingularAttribute<GeoZone, Long> id;
	public static volatile ListAttribute<GeoZone, Country> countries;
	public static volatile ListAttribute<GeoZone, GeoZoneDescription> descriptions;

	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String COUNTRIES = "countries";
	public static final String DESCRIPTIONS = "descriptions";

}

