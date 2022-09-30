package org.orakzai.lab.shop.domain.business.common.model.audit;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class AuditSection implements Serializable {

    public static final long serialVerionUID = -1934446958975060889L;

    @Column(name = "DATE_CREATED")
    private LocalDateTime dateCreated;

    @Column(name = "DATE_MODIFIED")
    private LocalDateTime dateModified;

    @Column(name = "UPDT_ID", length=20)
    private String modifiedBy;


}
