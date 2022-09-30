package org.orakzai.lab.shop.domain.business.reference.geozone.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="GEOZONE_DESCRIPTION", schema="SALESMANAGER", uniqueConstraints={
        @UniqueConstraint(columnNames={"GEOZONE_ID", "LANGUAGE_ID"})})
public class GeoZoneDescription extends Description {

    private static final long serialVersionUID = 7759498146450786218L;

    @ManyToOne(targetEntity = GeoZone.class)
    @JoinColumn(name = "GEOZONE_ID")
    private GeoZone geoZone;
}
