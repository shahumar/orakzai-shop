package org.orakzai.lab.shop.domain.business.catalog.product.model.review;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductReview.class)
public abstract class ProductReview_ {

	public static volatile SingularAttribute<ProductReview, Product> product;
	public static volatile SingularAttribute<ProductReview, Long> reviewRead;
	public static volatile SingularAttribute<ProductReview, LocalDate> reviewDate;
	public static volatile SingularAttribute<ProductReview, AuditSection> audit;
	public static volatile SingularAttribute<ProductReview, Long> id;
	public static volatile SingularAttribute<ProductReview, Double> reviewRating;
	public static volatile SetAttribute<ProductReview, ProductReviewDescription> descriptions;
	public static volatile SingularAttribute<ProductReview, Integer> status;
	public static volatile SingularAttribute<ProductReview, Customer> customer;

	public static final String PRODUCT = "product";
	public static final String REVIEW_READ = "reviewRead";
	public static final String REVIEW_DATE = "reviewDate";
	public static final String AUDIT = "audit";
	public static final String ID = "id";
	public static final String REVIEW_RATING = "reviewRating";
	public static final String DESCRIPTIONS = "descriptions";
	public static final String STATUS = "status";
	public static final String CUSTOMER = "customer";

}

