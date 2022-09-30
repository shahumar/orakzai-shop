package org.orakzai.lab.shop.domain.business.payments.model;

import lombok.Data;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;

import java.io.Serializable;

@Data
public class PaymentMethod implements Serializable {
    private static final long serialVersionUID = 1L;
    private String paymentMethodCode;
    private PaymentType paymentType;
    private boolean defaultSelected;
    private IntegrationModule module;
    private IntegrationConfiguration informations;
}
