package org.orakzai.lab.shop.domain.business.catalog.category.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="CATEGORY_DESCRIPTION", schema="SALESMANAGER",uniqueConstraints={
        @UniqueConstraint(columnNames={"CATEGORY_ID", "LANGUAGE_ID"})})
public class CategoryDescription extends Description {

    private static final long serialVersionUID = -3248423008455359301L;


    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Column(name="SEF_URL", length=120)
    private String seUrl;

    @Column(name = "CATEGORY_HIGHLIGHT")
    private String categoryHighlight;

    @Column(name="META_TITLE", length=120)
    private String metatagTitle;

    @Column(name="META_KEYWORDS")
    private String metatagKeywords;

    @Column(name="META_DESCRIPTION")
    private String metatagDescription;

    public CategoryDescription(String name, Language language) {
        this.setName(name);
        this.setLanguage(language);
        super.setId(0L);
    }
}
