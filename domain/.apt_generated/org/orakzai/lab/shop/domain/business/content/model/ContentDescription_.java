package org.orakzai.lab.shop.domain.business.content.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContentDescription.class)
public abstract class ContentDescription_ extends org.orakzai.lab.shop.domain.business.common.model.Description_ {

	public static volatile SingularAttribute<ContentDescription, String> metatagDescription;
	public static volatile SingularAttribute<ContentDescription, Content> content;
	public static volatile SingularAttribute<ContentDescription, String> seUrl;
	public static volatile SingularAttribute<ContentDescription, String> metatagKeywords;
	public static volatile SingularAttribute<ContentDescription, String> metatagTitle;

	public static final String METATAG_DESCRIPTION = "metatagDescription";
	public static final String CONTENT = "content";
	public static final String SE_URL = "seUrl";
	public static final String METATAG_KEYWORDS = "metatagKeywords";
	public static final String METATAG_TITLE = "metatagTitle";

}

