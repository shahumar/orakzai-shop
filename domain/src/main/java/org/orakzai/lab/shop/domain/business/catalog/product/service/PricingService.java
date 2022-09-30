package org.orakzai.lab.shop.domain.business.catalog.product.service;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.FinalPrice;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public interface PricingService {

    FinalPrice calculateProductPrice(Product product) throws ServiceException;

    FinalPrice calculateProductPrice(Product product, Customer customer)
            throws ServiceException;

    FinalPrice calculateProductPrice(Product product,
                                     List<ProductAttribute> attributes) throws ServiceException;

    FinalPrice calculateProductPrice(Product product,
                                     List<ProductAttribute> attributes, Customer customer)
            throws ServiceException;

    String getDisplayAmount(BigDecimal amount, MerchantStore store)
            throws ServiceException;

    String getDisplayAmount(BigDecimal amount, Locale locale, Currency currency, MerchantStore store)
            throws ServiceException;

    String getStringAmount(BigDecimal amount, MerchantStore store)
            throws ServiceException;
}
