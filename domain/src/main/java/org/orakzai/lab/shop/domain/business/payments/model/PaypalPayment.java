package org.orakzai.lab.shop.domain.business.payments.model;

import java.util.Set;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaypalPayment extends Payment{
    private String payerId;
    private String paymentToken;

    public PaypalPayment() {
        super.setPaymentType(PaymentType.PAYPAL);
    }
}
