package org.orakzai.lab.shop.domain.business.catalog.category.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> lineage;
	public static volatile SingularAttribute<Category, Category> parent;
	public static volatile SingularAttribute<Category, Boolean> visible;
	public static volatile SingularAttribute<Category, String> code;
	public static volatile SingularAttribute<Category, String> categoryImage;
	public static volatile SingularAttribute<Category, AuditSection> auditSection;
	public static volatile ListAttribute<Category, CategoryDescription> descriptions;
	public static volatile SingularAttribute<Category, Integer> depth;
	public static volatile SingularAttribute<Category, Integer> sortOrder;
	public static volatile SingularAttribute<Category, Boolean> categoryStatus;
	public static volatile SingularAttribute<Category, Long> id;
	public static volatile ListAttribute<Category, Category> categories;
	public static volatile SingularAttribute<Category, MerchantStore> merchantStore;

	public static final String LINEAGE = "lineage";
	public static final String PARENT = "parent";
	public static final String VISIBLE = "visible";
	public static final String CODE = "code";
	public static final String CATEGORY_IMAGE = "categoryImage";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String DEPTH = "depth";
	public static final String SORT_ORDER = "sortOrder";
	public static final String CATEGORY_STATUS = "categoryStatus";
	public static final String ID = "id";
	public static final String CATEGORIES = "categories";
	public static final String MERCHANT_STORE = "merchantStore";

}

