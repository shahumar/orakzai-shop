package org.orakzai.lab.shop.domain.business.catalog.product.dao.image;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



public interface ProductImageRepository extends SalesManagerEntityDao<Long, ProductImage> {

//	ProductImage getProductImageById(Long id);

	void deleteAllByProduct(Product product);

}
