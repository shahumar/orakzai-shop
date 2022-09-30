package org.orakzai.lab.shop.domain.business.shoppingcart.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.FinalPrice;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SHOPPING_CART_ITEM", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class ShoppingCartItem extends SalesManagerEntity<Long, ShoppingCartItem> implements Auditable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SHP_CART_ITEM_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHP_CRT_ITM_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(targetEntity = ShoppingCart.class)
    @JoinColumn(name = "SHP_CART_ID", nullable = false)
    private ShoppingCart shoppingCart;

    @Column(name="QUANTITY")
    private Integer quantity = new Integer(1);


    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Column(name="PRODUCT_ID", nullable=false)
    private Long productId;

    @Transient
    private boolean productVirtual;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "shoppingCartItem")
    private Set<ShoppingCartAttributeItem> attributes = new HashSet<>();

    @Transient
    private BigDecimal itemPrice;//item final price including all rebates

    @Transient
    private BigDecimal subTotal;//item final price * quantity

    @Transient
    private FinalPrice finalPrice;//contains price details (raw prices)


    @Transient
    private Product product;

    @Transient
    private boolean obsolete = false;




    public ShoppingCartItem(ShoppingCart shoppingCart, Product product) {
        this.product = product;
        this.productId = product.getId();
        this.quantity = 1;
        this.shoppingCart = shoppingCart;

    }

    public ShoppingCartItem(Product product) {
        this.product = product;
        this.productId = product.getId();
        this.quantity = 1;

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



    public void setAttributes(Set<ShoppingCartAttributeItem> attributes) {
        this.attributes.clear();
        this.attributes.addAll( attributes );
        //this.attributes = attributes;
    }

	public void addAttributes(ShoppingCartAttributeItem aItem) {
		this.attributes.add(aItem);
		
	}
}
