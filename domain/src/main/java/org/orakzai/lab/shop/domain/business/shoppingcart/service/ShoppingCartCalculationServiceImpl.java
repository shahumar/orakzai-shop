/**
 *
 */
package org.orakzai.lab.shop.domain.business.shoppingcart.service;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.order.service.OrderService;
import org.orakzai.lab.shop.domain.business.order.service.OrderServiceImpl;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;

@Service("shoppingCartCalculationService")
public class ShoppingCartCalculationServiceImpl implements ShoppingCartCalculationService
{


    protected  final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;



    /**
     * <p>Method used to recalculate state of shopping cart every time any change has been made
     * to underlying {@link ShoppingCart} object in DB.</p>
     * Following operations will be performed by this method.
     *
     * <li>Calculate price for each {@link ShoppingCartItem} and update it. </li>
     * <p>
     * This method is backbone method for all price calculation related to shopping cart.</p>
     *
     * @see OrderServiceImpl
     *
     * @param cartModel
     * @param customer
     * @param store
     * @param language
     * @throws ServiceException
     */
    @Override
    public OrderTotalSummary calculate( final ShoppingCart cartModel ,final Customer customer, final MerchantStore store, final Language language ) throws ServiceException
    {

        Validate.notNull(cartModel,"cart cannot be null");
        Validate.notNull(cartModel.getLineItems(),"Cart should have line items.");
        Validate.notNull(store,"MerchantStore cannot be null");
        Validate.notNull(customer,"Customer cannot be null");
        OrderTotalSummary orderTotalSummary=orderService.calculateShoppingCartTotal( cartModel, customer,store, language );
        updateCartModel(cartModel);
        return orderTotalSummary;


    }


    /**
     * <p>Method used to recalculate state of shopping cart every time any change has been made
     * to underlying {@link ShoppingCart} object in DB.</p>
     * Following operations will be performed by this method.
     *
     * <li>Calculate price for each {@link ShoppingCartItem} and update it. </li>
     * <p>
     * This method is backbone method for all price calculation related to shopping cart.</p>
     *
     * @see OrderServiceImpl
     *
     * @param cartModel
     * @param store
     * @param language
     * @throws ServiceException
     */
    @Override
    public OrderTotalSummary calculate( final ShoppingCart cartModel , final MerchantStore store, final Language language ) throws ServiceException
    {

        Validate.notNull(cartModel,"cart cannot be null");
        Validate.notNull(cartModel.getLineItems(),"Cart should have line items.");
        Validate.notNull(store,"MerchantStore cannot be null");
        OrderTotalSummary orderTotalSummary=orderService.calculateShoppingCartTotal( cartModel, store, language );
        updateCartModel(cartModel);
        return orderTotalSummary;


    }



    public ShoppingCartService getShoppingCartService()
    {
        return shoppingCartService;
    }


    private void updateCartModel(final ShoppingCart cartModel) throws ServiceException{
        shoppingCartService.saveOrUpdate( cartModel );
    }




}
