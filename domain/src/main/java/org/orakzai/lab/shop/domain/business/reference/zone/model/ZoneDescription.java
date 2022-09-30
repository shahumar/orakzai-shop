package org.orakzai.lab.shop.domain.business.reference.zone.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(
		name = "ZONE_DESCRIPTION", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"ZONE_ID", "LANGUAGE_ID"}),
		schema = SchemaConstant.SALESMANAGER_SCHEMA)
public class ZoneDescription extends Description {

    private static final long serialVersionUID = 6448836326562270923L;

    @ManyToOne(targetEntity = Zone.class)
    @JoinColumn(name = "ZONE_ID", nullable = false)
    private Zone zone;

    public ZoneDescription(Zone zone, Language language, String name) {
        setZone(zone);
        setLanguage(language);
        setName(name);
    }
}
