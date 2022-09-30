package org.orakzai.lab.shop.domain.business.reference.country.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY_DESCRIPTION", schema="SALESMANAGER", uniqueConstraints={
        @UniqueConstraint(columnNames={"COUNTRY_ID", "LANGUAGE_ID"})})
public class CountryDescription extends Description {

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;

    public CountryDescription(Language language, String name) {
        this.setLanguage(language);
        this.setName(name);
    }

}
