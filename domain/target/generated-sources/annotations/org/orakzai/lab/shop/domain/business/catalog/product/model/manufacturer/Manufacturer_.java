package org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Manufacturer.class)
public abstract class Manufacturer_ {

	public static volatile SingularAttribute<Manufacturer, String> image;
	public static volatile SingularAttribute<Manufacturer, Long> id;
	public static volatile SingularAttribute<Manufacturer, MerchantStore> merchantStore;
	public static volatile SingularAttribute<Manufacturer, AuditSection> auditSection;
	public static volatile SetAttribute<Manufacturer, ManufacturerDescription> descriptions;
	public static volatile SingularAttribute<Manufacturer, Integer> order;

	public static final String IMAGE = "image";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String ORDER = "order";

}

