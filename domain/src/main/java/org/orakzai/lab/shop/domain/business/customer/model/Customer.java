package org.orakzai.lab.shop.domain.business.customer.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.review.ProductReview;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerAttribute;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class Customer extends SalesManagerEntity<Long, Customer> {

    private static final long serialVersionUID = -6966934116557219193L;

    @Id
    @Column(name = "CUSTOMER_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "CUSTOMER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "customer")
    private Set<CustomerAttribute> attributes = new HashSet<>();

    @Column(name="CUSTOMER_GENDER", length=1, nullable=true)
    @Enumerated(value = EnumType.STRING)
    private CustomerGender gender;


    @Column(name="CUSTOMER_DOB")
    private LocalDate dateOfBirth;

    @Email
    @NotEmpty
    @Column(name="CUSTOMER_EMAIL_ADDRESS", length=96, nullable=false)
    private String emailAddress;

    @Column(name="CUSTOMER_NICK", length=96)
    private String nick;

    @Column(name="CUSTOMER_COMPANY", length=100)
    private String company;


    @Lob
    @Column(name="CUSTOMER_PASSWORD", length=250)
    private String password;


    @Column(name="CUSTOMER_ANONYMOUS")
    private boolean anonymous;



    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
    @JoinColumn(name = "LANGUAGE_ID", nullable=false)
    private Language defaultLanguage;



    @OneToMany(mappedBy = "customer", targetEntity = ProductReview.class)
    private List<ProductReview> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;


    @Embedded
    private org.orakzai.lab.shop.domain.business.common.model.Delivery delivery = null;

    @Embedded
    private org.orakzai.lab.shop.domain.business.common.model.Billing billing = null;


    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "CUSTOMER_GROUP", schema=SchemaConstant.SALESMANAGER_SCHEMA, joinColumns = {
            @JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false) }
            ,
            inverseJoinColumns = { @JoinColumn(name = "GROUP_ID",
                    nullable = false, updatable = false) }
    )
    @Cascade({
            org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.LOCK,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.REPLICATE

    })
    private List<Group> groups = new ArrayList<Group>();

    @Transient
    private String showCustomerStateList;

    @Transient
    private String showBillingStateList;

    @Transient
    private String showDeliveryStateList;
}
