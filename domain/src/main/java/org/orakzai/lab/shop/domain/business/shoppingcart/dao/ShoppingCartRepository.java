package org.orakzai.lab.shop.domain.business.shoppingcart.dao;

import java.util.Optional;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Repository;

@Repository("shoppingCartDao")
public interface ShoppingCartRepository extends SalesManagerEntityDao<Long, ShoppingCart> {

	Optional<ShoppingCart> findByIdAndMerchantStore(Long id, MerchantStore store);
	Optional<ShoppingCart> findByShoppingCartCodeAndMerchantStore(String code, MerchantStore store);

	Optional<ShoppingCart> findByCustomerId(long customer);
//
//	ShoppingCart getByCode(String code, MerchantStore store);
//
//	
//	ShoppingCart getShoppingCartById(Long id);
//
//	
//	ShoppingCart getShoppingCartByCode(String code);
//
//	public void removeShoppingCart(ShoppingCart cart);



}
