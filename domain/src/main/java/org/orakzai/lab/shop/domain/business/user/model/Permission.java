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
@EntityListeners(value = AuditListener.class)
@Table(name = "PERMISSION", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class Permission extends SalesManagerEntity<Integer, Permission> implements Auditable {

    private static final long serialVersionUID = 813468140197420748L;

    @Id
    @Column(name = "PERMISSION_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PERMISSION_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Integer id;

    @NotEmpty
    @Column(name="PERMISSION_NAME", unique=true)
    private String permissionName;

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "PERMISSION_GROUP", schema=SchemaConstant.SALESMANAGER_SCHEMA, joinColumns = {
            @JoinColumn(name = "PERMISSION_ID", nullable = false, updatable = false) }
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

    @Embedded
    private AuditSection auditSection = new AuditSection();


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;

    }

    @Override
    public AuditSection getAuditSection() {
        return this.auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;

    }

    public Permission(String permissionName) {
        this.permissionName = permissionName;
    }
}
