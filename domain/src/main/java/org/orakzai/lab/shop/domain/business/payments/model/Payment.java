package org.orakzai.lab.shop.domain.business.payments.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;

@Getter
@Setter
public class Payment {

    private PaymentType paymentType;
    private TransactionType transactionType = TransactionType.AUTHORIZECAPTURE;
    private String moduleName;
    private Currency currency;
}
