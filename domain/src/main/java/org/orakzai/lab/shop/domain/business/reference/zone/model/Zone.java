package org.orakzai.lab.shop.domain.business.reference.zone.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ZONE", schema = SchemaConstant.SALESMANAGER_SCHEMA)
public class Zone extends SalesManagerEntity<Long, Zone> {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ZONE_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "ZONE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<ZoneDescription> descriptions = new ArrayList<>();

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="COUNTRY_ID", nullable = false)
    private Country country;

    @Transient
    private String name;



    @Column(name = "ZONE_CODE", unique=true, nullable = false)
    private String code;

    public Zone(Country country, String name, String code) {
        this.setCode(code);
        this.setCountry(country);
        this.setCode(name);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
