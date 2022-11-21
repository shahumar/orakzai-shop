package org.orakzai.lab.shop.domain.business.reference.country.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.reference.geozone.model.GeoZone;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Country.class)
public abstract class Country_ {

	public static volatile SingularAttribute<Country, String> isoCode;
	public static volatile SingularAttribute<Country, Integer> id;
	public static volatile ListAttribute<Country, Zone> zones;
	public static volatile ListAttribute<Country, CountryDescription> descriptions;
	public static volatile SingularAttribute<Country, GeoZone> geoZone;
	public static volatile SingularAttribute<Country, Boolean> supported;

	public static final String ISO_CODE = "isoCode";
	public static final String ID = "id";
	public static final String ZONES = "zones";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String GEO_ZONE = "geoZone";
	public static final String SUPPORTED = "supported";

}

