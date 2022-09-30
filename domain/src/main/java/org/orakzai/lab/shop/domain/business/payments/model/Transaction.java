package org.orakzai.lab.shop.domain.business.payments.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.json.simple.JSONAware;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditListener;
import org.orakzai.lab.shop.domain.business.common.model.audit.AuditSection;
import org.orakzai.lab.shop.domain.business.common.model.audit.Auditable;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "SM_TRANSACTION", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class Transaction extends SalesManagerEntity<Long, Transaction> implements Serializable, Auditable, JSONAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TRANSACTION_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "TRANSACT_SEQ_NEXT_VAL")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID", nullable=true)
    private Order order;

    @Column(name="AMOUNT")
    private BigDecimal amount;

    @Column(name="TRANSACTION_DATE")
    private LocalDate transactionDate;

    @Column(name="TRANSACTION_TYPE")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name="PAYMENT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    @Column(name="DETAILS")
    private String details;

    @Transient
    private Map<String,String> transactionDetails= new HashMap<>();

    @Override
    public AuditSection getAuditSection() {
        return this.auditSection;
    }

    @Override
    public void setAuditSection(AuditSection audit) {
        this.auditSection = audit;

    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }


    @Override
    public String toJSONString() {

        if(this.getTransactionDetails()!=null && this.getTransactionDetails().size()>0) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(this.getTransactionDetails());
            } catch (Exception e) {
                LOGGER.error("Cannot parse transactions map",e);
            }
        }
        return null;
    }
}
