package org.orakzai.lab.shop.domain.business.catalog.product.model.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.common.model.Description;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.constants.SchemaConstant;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_REVIEW_DESCRIPTION", schema = SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints={
        @UniqueConstraint(columnNames={
                "PRODUCT_REVIEW_ID", "LANGUAGE_ID"})})
public class ProductReviewDescription extends Description {

    private static final long serialVersionUID = -1957502640742695406L;

    @ManyToOne(targetEntity = ProductReview.class)
    @JoinColumn(name="PRODUCT_REVIEW_ID")
    private ProductReview productReview;

    public ProductReviewDescription(Language language, String name) {
        this.setLanguage(language);
        this.setName(name);
    }
}
