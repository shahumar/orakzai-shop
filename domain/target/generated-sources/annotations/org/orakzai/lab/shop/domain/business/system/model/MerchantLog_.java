package org.orakzai.lab.shop.domain.business.system.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MerchantLog.class)
public abstract class MerchantLog_ {

	public static volatile SingularAttribute<MerchantLog, String> log;
	public static volatile SingularAttribute<MerchantLog, String> module;
	public static volatile SingularAttribute<MerchantLog, Long> id;
	public static volatile SingularAttribute<MerchantLog, MerchantStore> store;

	public static final String LOG = "log";
	public static final String MODULE = "module";
	public static final String ID = "id";
	public static final String STORE = "store";

}

