package org.orakzai.lab.shop.web.controller.customer.facade;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;

public interface CustomerFacade {

	Customer getCustomerByUserName(String userName, MerchantStore store) throws Exception;

	void authenticate(Customer customerModel, String userName, String encodedPassword) throws Exception;

	ShoppingCartData mergeCart(Customer customerModel, String sessionShoppingCartCode, MerchantStore store,
			Language language) throws Exception;
	
	
}
