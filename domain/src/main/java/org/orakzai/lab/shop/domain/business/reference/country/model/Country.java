package org.orakzai.lab.shop.domain.business.reference.country.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.geozone.model.GeoZone;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "COUNTRY", schema= SchemaConstant.SALESMANAGER_SCHEMA)
@Cacheable
public class Country extends SalesManagerEntity<Integer, Country> {

    private static final long serialVersionUID = -7388011537255588035L;

    @Id
    @Column(name="COUNTRY_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "COUNTRY_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Integer id;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<CountryDescription> descriptions = new ArrayList<CountryDescription>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Zone> zones = new ArrayList<>();

    @ManyToOne(targetEntity = GeoZone.class)
    @JoinColumn(name = "GEOZONE_ID")
    private GeoZone geoZone;

    @Column(name = "COUNTRY_SUPPORTED")
    private boolean supported = true;

    @Column(name = "COUNTRY_ISOCODE", unique=true, nullable = false)
    private String isoCode;

    @Transient
    private String name;

    public Country(String isoCode) {
        this.setIsoCode(isoCode);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
