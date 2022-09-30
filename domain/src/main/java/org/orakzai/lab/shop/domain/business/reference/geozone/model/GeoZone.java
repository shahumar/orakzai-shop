package org.orakzai.lab.shop.domain.business.reference.geozone.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "GEOZONE", schema="SALESMANAGER")
public class GeoZone extends SalesManagerEntity<Long, GeoZone> {

    private static final long serialVersionUID = -5992008645857938825L;

    @Id
    @Column(name = "GEOZONE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "GEOZONE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @OneToMany(mappedBy = "geoZone", cascade = CascadeType.ALL)
    private List<GeoZoneDescription> descriptions = new ArrayList<GeoZoneDescription>();

    @OneToMany(mappedBy = "geoZone", targetEntity = Country.class)
    private List<Country> countries = new ArrayList<>();



    @Column(name = "GEOZONE_NAME")
    private String name;

    @Column(name = "GEOZONE_CODE")
    private String code;
}
