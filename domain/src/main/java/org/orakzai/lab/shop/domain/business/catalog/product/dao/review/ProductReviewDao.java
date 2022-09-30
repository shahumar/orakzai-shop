package org.orakzai.lab.shop.domain.business.catalog.product.dao.review;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.review.ProductReview;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;

@Repository("productReviewDao")
public interface ProductReviewDao extends SalesManagerEntityDao<Long, ProductReview> {

//	List<ProductReview> getByCustomer(Customer customer);
//	List<ProductReview> getByProduct(Product product);
//	List<ProductReview> getByProduct(Product product, Language language);
//	ProductReview getByProductAndCustomer(Long productId, Long customerId);
//
//
//


}
