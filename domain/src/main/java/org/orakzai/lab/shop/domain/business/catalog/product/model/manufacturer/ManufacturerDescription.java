package org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MANUFACTURER_DESCRIPTION", schema="SALESMANAGER", uniqueConstraints={
        @UniqueConstraint(columnNames={
                "MANUFACTURER_ID",
                "LANGUAGE_ID"})})
public class ManufacturerDescription extends Description {

    private static final long serialVersionUID = -2164581613773995282L;

    @ManyToOne(targetEntity = Manufacturer.class)
    @JoinColumn(name = "MANUFACTURER_ID", nullable = false)
    private Manufacturer manufacturer;

    @Column(name = "MANUFACTURERS_URL")
    private String url;

    @Column(name = "URL_CLICKED")
    private Integer urlClicked;

    @Column(name = "DATE_LAST_CLICK")
    private LocalDate dateLastClick;
}
