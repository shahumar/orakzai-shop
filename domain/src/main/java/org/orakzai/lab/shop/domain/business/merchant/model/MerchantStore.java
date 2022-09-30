package org.orakzai.lab.shop.domain.business.merchant.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.reference.country.model.Country;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.zone.model.Zone;
import org.orakzai.lab.shop.domain.constants.MeasureUnit;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "MERCHANT_STORE", schema = SchemaConstant.SALESMANAGER_SCHEMA)
public class MerchantStore extends SalesManagerEntity<Integer, MerchantStore> {
    private static final long serialVersionUID = 7671103335743647655L;

    public final static String DEFAULT_STORE = "DEFAULT";

    @Id
    @Column(name = "MERCHANT_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "STORE_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Integer id;

    @NotEmpty
    @Column(name = "STORE_NAME", nullable=false, length=100)
    private String storename;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_]*$")
    @Column(name = "STORE_CODE", nullable=false, unique=true, length=100)
    private String code;

    @NotEmpty
    @Column(name = "STORE_PHONE", length=100)
    private String storephone;

    @Column(name = "STORE_ADDRESS")
    private String storeaddress;

    @NotEmpty
    @Column(name = "STORE_CITY", length=100)
    private String storecity;

    @NotEmpty
    @Column(name = "STORE_POSTAL_CODE", length=15)
    private String storepostalcode;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
    @JoinColumn(name="COUNTRY_ID", nullable=true, updatable=true)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Zone.class)
    @JoinColumn(name="ZONE_ID", nullable=true, updatable=true)
    private Zone zone;

    @Column(name = "STORE_STATE_PROV", length=100)
    private String storestateprovince;

    @Column(name = "WEIGHTUNITCODE", length=5)
    private String weightunitcode = MeasureUnit.LB.name();

    @Column(name = "SEIZEUNITCODE", length=5)
    private String seizeunitcode = MeasureUnit.IN.name();

    @Column(name = "IN_BUSINESS_SINCE")
    private LocalDate inBusinessSince = LocalDate.now();

    @Transient
    private String dateBusinessSince;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
    @JoinColumn(name = "LANGUAGE_ID", nullable=true)
    private Language defaultLanguage;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MERCHANT_LANGUAGE")
    private List<Language> languages = new ArrayList<>();

    @Column(name = "USE_CACHE")
    private boolean useCache = false;

    @Column(name="STORE_TEMPLATE", length=25)
    private String storeTemplate;

    @Column(name="INVOICE_TEMPLATE", length=25)
    private String invoiceTemplate;

    @Column(name="DOMAIN_NAME", length=80)
    private String domainName;

    @Column(name="CONTINUESHOPPINGURL", length=150)
    private String continueshoppingurl;

    @Email
    @NotEmpty
    @Column(name = "STORE_EMAIL", length=60, nullable=false)
    private String storeEmailAddress;

    @Column(name="STORE_LOGO", length=100)
    private String storeLogo;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Currency.class)
    @JoinColumn(name = "CURRENCY_ID", nullable=true)
    private Currency currency;

    @Column(name = "CURRENCY_FORMAT_NATIONAL")
    private boolean currencyFormatNational;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }
}
