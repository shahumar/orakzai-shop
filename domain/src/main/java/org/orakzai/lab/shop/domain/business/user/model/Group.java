package org.orakzai.lab.shop.domain.business.user.model;

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
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SM_GROUP", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class Group extends SalesManagerEntity<Integer, Group> implements Auditable {

    private static final long serialVersionUID = 3786127652573709701L;
    @Id
    @Column(name = "GROUP_ID", unique=true, nullable=false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "GROUP_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Integer id;

    @Column (name ="GROUP_TYPE")
    @Enumerated(value = EnumType.STRING)
    private GroupType groupType;

    @NotEmpty
    @Column(name="GROUP_NAME", unique=true)
    private String groupName;

    @ManyToMany(mappedBy = "groups")
    private Set<Permission> permissions = new HashSet<>();

    @Embedded
    private AuditSection auditSection = new AuditSection();


    @Override
    public AuditSection getAuditSection() {
        return this.auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }
}
