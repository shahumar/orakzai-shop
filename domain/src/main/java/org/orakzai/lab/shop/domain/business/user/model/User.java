package org.orakzai.lab.shop.domain.business.user.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cascade;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "USER", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class User extends SalesManagerEntity<Long, User> implements Auditable {

    private static final long serialVersionUID = 5401059537544058710L;

    @Id
    @Column(name = "USER_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USER_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @NotEmpty
    @Column(name="ADMIN_NAME", length=250, unique=true)
    private String adminName;

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "USER_GROUP", schema=SchemaConstant.SALESMANAGER_SCHEMA, joinColumns = {
            @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }
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
    private List<Group> groups = new ArrayList<>();

    @NotEmpty
    @Email
    @Column(name="ADMIN_EMAIL")
    private String adminEmail;

    @NotEmpty
    @Column(name="ADMIN_PASSWORD", length=250)
    private String adminPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=true)
    private MerchantStore merchantStore;


    @Column(name="ADMIN_FIRST_NAME")
    private String firstName;

    @Column(name="ACTIVE")
    private boolean active = true;


    @Column(name="ADMIN_LAST_NAME")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
    @JoinColumn(name = "LANGUAGE_ID")
    private Language defaultLanguage;


    @Column(name="ADMIN_Q1")
    private String question1;

    @Column(name="ADMIN_Q2")
    private String question2;

    @Column(name="ADMIN_Q3")
    private String question3;

    @Column(name="ADMIN_A1")
    private String answer1;

    @Column(name="ADMIN_A2")
    private String answer2;

    @Column(name="ADMIN_A3")
    private String answer3;


    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Column(name = "LAST_ACCESS")
    private LocalDate lastAccess;

    @Column(name = "LOGIN_ACCESS")
    private LocalDate loginTime;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public AuditSection getAuditSection() {
        return auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        auditSection = audit;

    }


    public User(String userName,String password, String email) {

        this.adminName = userName;
        this.adminPassword = password;
        this.adminEmail = email;
    }
}
