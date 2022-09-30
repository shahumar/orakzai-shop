package org.orakzai.lab.shop.domain.business.order.model.filehistory;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="FILE_HISTORY", schema= SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
        @UniqueConstraint(
                columnNames={"MERCHANT_ID", "FILE_ID"})})
public class FileHistory implements Serializable {

    private static final long serialVersionUID = 1321251632883237664L;

    @Id
    @Column(name = "FILE_HISTORY_ID", unique = true, nullable = false)
    @TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT",
            pkColumnValue = "FILE_HISTORY_ID_NEXT_VALUE")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    private Long id;

    @ManyToOne(targetEntity = MerchantStore.class)
    @JoinColumn(name = "MERCHANT_ID", nullable = false)
    private MerchantStore store;

    @Column(name = "FILE_ID")
    private Long fileId;

    @Column ( name="FILESIZE", nullable=false )
    private Integer filesize;

    @Column ( name="DATE_ADDED", length=0, nullable=false )
    private LocalDate dateAdded;

    @Column ( name="DATE_DELETED", length=0 )
    private LocalDate dateDeleted;

    @Column ( name="ACCOUNTED_DATE", length=0 )
    private LocalDate accountedDate;

    @Column ( name="DOWNLOAD_COUNT", nullable=false )
    private Integer downloadCount;
}
