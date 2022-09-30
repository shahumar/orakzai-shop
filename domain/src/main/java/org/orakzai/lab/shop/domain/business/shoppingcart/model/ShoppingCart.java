package org.orakzai.lab.shop.domain.business.shoppingcart.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(
        indexes = {
                @Index(name="SHP_CART_CODE_IDX", columnList = "SHP_CART_CODE"),
                @Index(name="SHP_CART_CUSTOMER_IDX", columnList = "CUSTOMER_ID")
        }, name = "SHOPPING_CART", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class ShoppingCart extends SalesManagerEntity<Long, ShoppingCart> implements Auditable {

    private static final long serialVersionUID = 1L;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Id
    @Column(name = "SHP_CART_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHP_CRT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    /**
     * Will be used to fetch shopping cart model from the controller
     * this is a unique code that should be attributed from the client (UI)
     *
     */

    @Column(name = "SHP_CART_CODE", unique=true, nullable=false)
    private String shoppingCartCode;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "shoppingCart")
    private Set<ShoppingCartItem> lineItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;


    @Column(name = "CUSTOMER_ID", nullable = true)
    private Long customerId;

    @Transient
    private boolean obsolete = false;//when all items are obsolete

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;

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
