package org.orakzai.lab.shop.domain.business.common.model.audit;

public interface Auditable {

    AuditSection getAuditSection();
    void setAuditSection(AuditSection audit);
}
