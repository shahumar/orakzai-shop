package org.orakzai.lab.shop.domain.business.catalog.product.dao.file;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.DigitalProduct;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.springframework.stereotype.Repository;

@Repository("digitalProductDao")
public interface DigitalProductDao extends SalesManagerEntityDao<Long, DigitalProduct> {

//	DigitalProduct getByProduct(MerchantStore store, Product product);


}
