package org.orakzai.lab.shop.domain.business.order.model.payment;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.orakzai.lab.shop.domain.business.payments.model.CreditCardType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditCard.class)
public abstract class CreditCard_ {

	public static volatile SingularAttribute<CreditCard, String> ccNumber;
	public static volatile SingularAttribute<CreditCard, String> ccCvv;
	public static volatile SingularAttribute<CreditCard, CreditCardType> cardType;
	public static volatile SingularAttribute<CreditCard, String> ccOwner;
	public static volatile SingularAttribute<CreditCard, String> ccExpires;

	public static final String CC_NUMBER = "ccNumber";
	public static final String CC_CVV = "ccCvv";
	public static final String CARD_TYPE = "cardType";
	public static final String CC_OWNER = "ccOwner";
	public static final String CC_EXPIRES = "ccExpires";

}

