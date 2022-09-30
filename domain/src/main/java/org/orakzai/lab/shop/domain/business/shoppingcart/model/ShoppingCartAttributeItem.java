package org.orakzai.lab.shop.domain.business.shoppingcart.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SHOPPING_CART_ATTR_ITEM", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class ShoppingCartAttributeItem extends SalesManagerEntity<Long, ShoppingCartAttributeItem> implements Auditable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SHP_CART_ATTR_ITEM_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHP_CRT_ATTR_ITM_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();



    @Column(name="PRODUCT_ATTR_ID", nullable=false)
    private Long productAttributeId;

    @Transient
    private ProductAttribute productAttribute;



    @ManyToOne(targetEntity = ShoppingCartItem.class)
    @JoinColumn(name = "SHP_CART_ITEM_ID", nullable = false)
    private ShoppingCartItem shoppingCartItem;

    public ShoppingCartAttributeItem(ShoppingCartItem shoppingCartItem, ProductAttribute productAttribute) {
        this.shoppingCartItem = shoppingCartItem;
        this.productAttribute = productAttribute;
        this.productAttributeId = productAttribute.getId();
    }

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
