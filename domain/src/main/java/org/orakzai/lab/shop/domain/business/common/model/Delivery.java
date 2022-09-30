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

@Getter
@Setter
@Embeddable
public class Delivery {
    @Column(name ="DELIVERY_LAST_NAME", length=64)
    private String lastName;

    @Column (name ="DELIVERY_FIRST_NAME", length=64)
    private String firstName;

    @Column (name ="DELIVERY_COMPANY", length=100)
    private String company;

    @Column (name ="DELIVERY_STREET_ADDRESS", length=256)
    private String address;

    @Column (name ="DELIVERY_CITY", length=100)
    private String city;

    @Column (name ="DELIVERY_POSTCODE", length=20)
    private String postalCode;

    @Column (name ="DELIVERY_STATE", length=100)
    private String state;

    @Column(name="DELIVERY_TELEPHONE", length=32)
    private String telephone;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name="DELIVERY_COUNTRY_ID", nullable=true)
    private Country country;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Zone.class)
    @JoinColumn(name="DELIVERY_ZONE_ID", nullable=true)
    private Zone zone;

}
