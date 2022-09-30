package org.orakzai.lab.shop.domain.business.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Embeddable
public class Billing {

    @NotEmpty
    @Column(name ="BILLING_LAST_NAME", length=64, nullable=false)
    private String lastName;

    @NotEmpty
    @Column (name ="BILLING_FIRST_NAME", length=64, nullable=false)
    private String firstName;



    @Column (name ="BILLING_COMPANY", length=100)
    private String company;

    @Column (name ="BILLING_STREET_ADDRESS", length=256)
    private String address;


    @Column (name ="BILLING_CITY", length=100)
    private String city;

    @Column (name ="BILLING_POSTCODE", length=20)
    private String postalCode;

    @Column(name="BILLING_TELEPHONE", length=32)
    private String telephone;

    @Column (name ="BILLING_STATE", length=100)
    private String state;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name="BILLING_COUNTRY_ID", nullable=false)
    private Country country;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zone.class)
    @JoinColumn(name="BILLING_ZONE_ID", nullable=true)
    private Zone zone;
}
