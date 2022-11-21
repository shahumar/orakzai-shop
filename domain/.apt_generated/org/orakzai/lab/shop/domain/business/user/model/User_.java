package org.orakzai.lab.shop.domain.business.user.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> question3;
	public static volatile SingularAttribute<User, String> answer3;
	public static volatile ListAttribute<User, Group> groups;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, String> question2;
	public static volatile SingularAttribute<User, String> answer2;
	public static volatile SingularAttribute<User, LocalDate> lastAccess;
	public static volatile SingularAttribute<User, AuditSection> auditSection;
	public static volatile SingularAttribute<User, String> question1;
	public static volatile SingularAttribute<User, String> answer1;
	public static volatile SingularAttribute<User, String> adminName;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, Language> defaultLanguage;
	public static volatile SingularAttribute<User, LocalDate> loginTime;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, MerchantStore> merchantStore;
	public static volatile SingularAttribute<User, String> adminEmail;
	public static volatile SingularAttribute<User, String> adminPassword;

	public static final String LAST_NAME = "lastName";
	public static final String QUESTION3 = "question3";
	public static final String ANSWER3 = "answer3";
	public static final String GROUPS = "groups";
	public static final String ACTIVE = "active";
	public static final String QUESTION2 = "question2";
	public static final String ANSWER2 = "answer2";
	public static final String LAST_ACCESS = "lastAccess";
	public static final String AUDIT_SECTION = "auditSection";
	public static final String QUESTION1 = "question1";
	public static final String ANSWER1 = "answer1";
	public static final String ADMIN_NAME = "adminName";
	public static final String FIRST_NAME = "firstName";
	public static final String DEFAULT_LANGUAGE = "defaultLanguage";
	public static final String LOGIN_TIME = "loginTime";
	public static final String ID = "id";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String ADMIN_EMAIL = "adminEmail";
	public static final String ADMIN_PASSWORD = "adminPassword";

}

