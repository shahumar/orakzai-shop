package org.orakzai.lab.shop.domain.business.order.model.orderproduct;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ORDER_PRODUCT_DOWNLOAD", schema= SchemaConstant.SALESMANAGER_SCHEMA)
public class OrderProductDownload extends SalesManagerEntity<Long, OrderProductDownload> implements Serializable {

    private static final long serialVersionUID = -8935511990745477240L;

    public final static int DEFAULT_DOWNLOAD_MAX_DAYS = 31;

    @Id
    @Column(name="ORDER_PRODUCT_DOWNLOAD_ID")
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_PRODUCT_DL_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_PRODUCT_ID", nullable = false)
    private OrderProduct orderProduct;

    @Column(name = "ORDER_PRODUCT_FILENAME", nullable = false)
    private String orderProductFilename;

    @Column(name = "DOWNLOAD_MAXDAYS", nullable = false)
    private Integer maxdays = DEFAULT_DOWNLOAD_MAX_DAYS;

    @Column(name = "DOWNLOAD_COUNT", nullable = false)
    private Integer downloadCount;
}
