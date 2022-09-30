package org.orakzai.lab.shop.domain.business.catalog.product.dao.availability;

import org.orakzai.lab.shop.domain.business.catalog.product.model.availability.ProductAvailability;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productAvailabilityDao")
public interface ProductAvailabilityRepository extends SalesManagerEntityDao<Long, ProductAvailability> {

}
