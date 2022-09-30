package org.orakzai.lab.shop.web.controller.cart.facade;

import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartItem;

public interface ShoppingCartFacade {

	public ShoppingCartData addItemsToShoppingCart(ShoppingCartData shoppingCart,final ShoppingCartItem item, final MerchantStore store,final Language language,final Customer customer) throws Exception;
    public ShoppingCart createCartModel(final String shoppingCartCode, final MerchantStore store,final Customer customer) throws Exception;
    public ShoppingCartData getShoppingCartData(final Customer customer,final  MerchantStore store,final String shoppingCartId) throws Exception;
    public ShoppingCartData getShoppingCartData(final ShoppingCart shoppingCart) throws Exception;
    public ShoppingCartData getShoppingCartData(String code, MerchantStore store) throws Exception;
    public ShoppingCartData removeCartItem(final Long itemID, final String cartId,final MerchantStore store,final Language language ) throws Exception;
    public ShoppingCartData updateCartItem(final Long itemID, final String cartId, final long quantity,final MerchantStore store,Language language ) throws Exception;
    public void deleteShoppingCart(final Long id, final MerchantStore store) throws Exception;
	ShoppingCartData updateCartItems(List<ShoppingCartItem> shoppingCartItems,
			MerchantStore store, Language language) throws Exception;
	public ShoppingCart getShoppingCartModel(final String shoppingCartCode, MerchantStore store) throws Exception;
	public ShoppingCart getShoppingCartModel(final Customer customer, MerchantStore store) throws Exception;
	void deleteShoppingCart(String code, MerchantStore store) throws Exception;
}
