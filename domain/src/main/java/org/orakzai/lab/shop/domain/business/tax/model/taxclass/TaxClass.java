package org.orakzai.lab.shop.domain.business.tax.model.taxclass;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.model.taxrate.TaxRate;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TAX_CLASS", schema = SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints= @UniqueConstraint(
        columnNames = {"MERCHANT_ID", "TAX_CLASS_CODE"}) )
public class TaxClass extends SalesManagerEntity<Long, TaxClass> {

    private static final long serialVersionUID = -325750148480212355L;

    public final static String DEFAULT_TAX_CLASS = "DEFAULT";

    public TaxClass(String code) {
        this.code = code;
        this.title = code;
    }

    @Id
    @Column(name = "TAX_CLASS_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "TX_CLASS_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @NotEmpty
    @Column(name="TAX_CLASS_CODE", nullable=false, length=10)
    private String code;

    @NotEmpty
    @Column(name = "TAX_CLASS_TITLE" , nullable=false , length=32 )
    private String title;



    @OneToMany(mappedBy = "taxClass", targetEntity = Product.class)
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=true)
    private MerchantStore merchantStore;


    @OneToMany(mappedBy = "taxClass")
    private List<TaxRate> taxRates = new ArrayList<TaxRate>();

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
