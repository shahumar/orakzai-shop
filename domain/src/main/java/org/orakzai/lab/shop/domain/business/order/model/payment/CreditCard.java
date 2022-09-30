package org.orakzai.lab.shop.domain.business.order.model.payment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.payments.model.CreditCardType;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Embeddable
public class CreditCard {

    @Column(name ="CARD_TYPE")
    @Enumerated(value = EnumType.STRING)
    private CreditCardType cardType;

    @Column (name ="CC_OWNER")
    private String ccOwner;

    @Column (name ="CC_NUMBER")
    private String ccNumber;

    @Column (name ="CC_EXPIRES")
    private String ccExpires;

    @Column (name ="CC_CVV")
    private String ccCvv;
}
