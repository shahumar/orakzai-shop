package org.orakzai.lab.shop.domain.business.catalog.category.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryDescription.class)
public abstract class CategoryDescription_ extends org.orakzai.lab.shop.domain.business.common.model.Description_ {

	public static volatile SingularAttribute<CategoryDescription, String> categoryHighlight;
	public static volatile SingularAttribute<CategoryDescription, Category> category;
	public static volatile SingularAttribute<CategoryDescription, String> metatagDescription;
	public static volatile SingularAttribute<CategoryDescription, String> seUrl;
	public static volatile SingularAttribute<CategoryDescription, String> metatagKeywords;
	public static volatile SingularAttribute<CategoryDescription, String> metatagTitle;

	public static final String CATEGORY_HIGHLIGHT = "categoryHighlight";
	public static final String CATEGORY = "category";
	public static final String METATAG_DESCRIPTION = "metatagDescription";
	public static final String SE_URL = "seUrl";
	public static final String METATAG_KEYWORDS = "metatagKeywords";
	public static final String METATAG_TITLE = "metatagTitle";

}

