/**
 *
 */
package org.orakzai.lab.shop.domain.business.shoppingcart.service;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;

/**
 * Interface declaring various methods used to calculate {@link ShoppingCart}
 * object details.
 * @author Umesh Awasthi
 * @since 1.2
 *
 */
public interface ShoppingCartCalculationService
{
    /**
     * Method which will be used to calculate price for each line items as
     * well Total and Sub-total for {@link ShoppingCart}.
     * @param cartModel ShoopingCart mode representing underlying DB object
     * @param customer
     * @param store
     * @param language
     * @throws ServiceException
     */
    public OrderTotalSummary calculate(final ShoppingCart cartModel,final Customer customer, final MerchantStore store, final Language language) throws ServiceException;


    /**
     * Method which will be used to calculate price for each line items as
     * well Total and Sub-total for {@link ShoppingCart}.
     * @param cartModel ShoopingCart mode representing underlying DB object
     * @param store
     * @param language
     * @throws ServiceException
     */
    public OrderTotalSummary calculate(final ShoppingCart cartModel, final MerchantStore store, final Language language) throws ServiceException;
}
