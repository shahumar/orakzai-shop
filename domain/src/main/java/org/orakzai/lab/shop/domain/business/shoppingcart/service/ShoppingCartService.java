package org.orakzai.lab.shop.domain.business.shoppingcart.service;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingProduct;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;


public interface ShoppingCartService extends SalesManagerEntityService<Long, ShoppingCart> {

	ShoppingCart getShoppingCart(Customer customer) throws ServiceException;

	void saveOrUpdate(ShoppingCart shoppingCart) throws ServiceException;

	ShoppingCart getById(Long id, MerchantStore store) throws ServiceException;

	ShoppingCart getByCode(String code, MerchantStore store)
			throws ServiceException;

	ShoppingCart getByCustomer(Customer customer) throws ServiceException;

	
	List<ShippingProduct> createShippingProduct(ShoppingCart cart)
			throws ServiceException;



	
	boolean isFreeShoppingCart(ShoppingCart cart) throws ServiceException;

	boolean isFreeShoppingCart(List<ShoppingCartItem> items) throws ServiceException;

	
	ShoppingCartItem populateShoppingCartItem(Product product)
			throws ServiceException;

	void deleteCart(ShoppingCart cart) throws ServiceException;


	void removeShoppingCart(ShoppingCart cart) throws ServiceException;

	
	public ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart,final ShoppingCart sessionCart,final MerchantStore store  ) throws Exception;


	boolean requiresShipping(ShoppingCart cart) throws ServiceException;






}