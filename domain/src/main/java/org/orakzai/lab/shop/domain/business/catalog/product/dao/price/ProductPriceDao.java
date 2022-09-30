package org.orakzai.lab.shop.domain.business.catalog.product.dao.price;

import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.springframework.stereotype.Repository;


@Repository("productPriceDao")
public interface ProductPriceDao extends SalesManagerEntityDao<Long, ProductPrice> {

}
