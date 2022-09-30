package org.orakzai.lab.shop.domain.business.system.model;


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
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SYSTEM_NOTIFICATION", schema= SchemaConstant.SALESMANAGER_SCHEMA,uniqueConstraints= @UniqueConstraint(
        columnNames = {"MERCHANT_ID", "CONFIG_KEY"}) )
public class SystemNotification extends SalesManagerEntity<Long, SystemNotification> implements Serializable, Auditable {

    private static final long serialVersionUID = -6269172313628887000L;

    @Id
    @Column(name = "SYSTEM_NOTIF_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SYST_NOTIF_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Column(name="CONFIG_KEY")
    private String key;

    @Column(name="VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=true)
    private MerchantStore merchantStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable=true)
    private User user;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Embedded
    private AuditSection auditSection = new AuditSection();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
