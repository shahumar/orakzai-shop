package org.orakzai.lab.shop.domain.business.customer.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.catalog.product.model.review.ProductReview;
import org.orakzai.lab.shop.domain.business.common.model.Billing;
import org.orakzai.lab.shop.domain.business.common.model.Delivery;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.user.model.Group;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Delivery> delivery;
	public static volatile SingularAttribute<Customer, CustomerGender> gender;
	public static volatile ListAttribute<Customer, Group> groups;
	public static volatile SingularAttribute<Customer, LocalDate> dateOfBirth;
	public static volatile SingularAttribute<Customer, Billing> billing;
	public static volatile SingularAttribute<Customer, String> nick;
	public static volatile SingularAttribute<Customer, String> emailAddress;
	public static volatile SingularAttribute<Customer, String> password;
	public static volatile SingularAttribute<Customer, Language> defaultLanguage;
	public static volatile ListAttribute<Customer, ProductReview> reviews;
	public static volatile SingularAttribute<Customer, Boolean> anonymous;
	public static volatile SetAttribute<Customer, CustomerAttribute> attributes;
	public static volatile SingularAttribute<Customer, String> company;
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, MerchantStore> merchantStore;

	public static final String DELIVERY = "delivery";
	public static final String GENDER = "gender";
	public static final String GROUPS = "groups";
	public static final String DATE_OF_BIRTH = "dateOfBirth";
	public static final String BILLING = "billing";
	public static final String NICK = "nick";
	public static final String EMAIL_ADDRESS = "emailAddress";
	public static final String PASSWORD = "password";
	public static final String DEFAULT_LANGUAGE = "defaultLanguage";
	public static final String REVIEWS = "reviews";
	public static final String ANONYMOUS = "anonymous";
	public static final String ATTRIBUTES = "attributes";
	public static final String COMPANY = "company";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";

}

