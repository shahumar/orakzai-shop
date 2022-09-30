package org.orakzai.lab.shop.domain.business.shoppingcart.dao;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.springframework.stereotype.Repository;

@Repository("shoppingCartItemRepository")
public interface ShoppingCartItemRepository extends SalesManagerEntityDao<Long, ShoppingCartItem> {


	
}
