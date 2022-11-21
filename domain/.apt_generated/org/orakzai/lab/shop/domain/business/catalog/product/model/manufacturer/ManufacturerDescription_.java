package org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ManufacturerDescription.class)
public abstract class ManufacturerDescription_ extends org.orakzai.lab.shop.domain.business.common.model.Description_ {

	public static volatile SingularAttribute<ManufacturerDescription, Integer> urlClicked;
	public static volatile SingularAttribute<ManufacturerDescription, LocalDate> dateLastClick;
	public static volatile SingularAttribute<ManufacturerDescription, String> url;
	public static volatile SingularAttribute<ManufacturerDescription, Manufacturer> manufacturer;

	public static final String URL_CLICKED = "urlClicked";
	public static final String DATE_LAST_CLICK = "dateLastClick";
	public static final String URL = "url";
	public static final String MANUFACTURER = "manufacturer";

}

