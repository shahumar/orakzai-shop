package org.orakzai.lab.shop.domain.business.content.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="CONTENT_DESCRIPTION", schema="SALESMANAGER",uniqueConstraints={
        @UniqueConstraint(columnNames={
                "CONTENT_ID", "LANGUAGE_ID"})})
public class ContentDescription extends Description implements Serializable {

    private static final long serialVersionUID = -1252756716545768599L;

    @ManyToOne(targetEntity = Content.class)
    @JoinColumn(name = "CONTENT_ID", nullable = false)
    private Content content;

    @Column(name="SEF_URL", length=120)
    private String seUrl;


    @Column(name="META_KEYWORDS")
    private String metatagKeywords;

    @Column(name="META_TITLE")
    private String metatagTitle;

    @Column(name="META_DESCRIPTION")
    private String metatagDescription;

    public ContentDescription(String name, Language language) {
        this.setName(name);
        this.setLanguage(language);
        super.setId(0L);
    }
}
