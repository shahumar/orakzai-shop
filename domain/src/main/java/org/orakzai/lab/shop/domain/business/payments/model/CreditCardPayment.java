package org.orakzai.lab.shop.domain.business.payments.model;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardPayment extends Payment{

    private String creditCardNumber;
    private String credidCardValidationNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cardOwner;
    private CreditCardType creditCard;
}
