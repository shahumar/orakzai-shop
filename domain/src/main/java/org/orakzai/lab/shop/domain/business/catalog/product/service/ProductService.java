package org.orakzai.lab.shop.domain.business.catalog.product.service;

import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductCriteria;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductList;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

public interface ProductService extends SalesManagerEntityService<Long, Product> {

    void addProductDescription(Product product, ProductDescription description) throws ServiceException;

    ProductDescription getProductDescription(Product product, Language language);

    Product getProductForLocale(long productId, Language language, Locale locale) throws ServiceException;

    List<Product> getProductsForLocale(Category category, Language language, Locale locale) throws ServiceException;

    List<Product> getProducts(List<Long> categoryIds) throws ServiceException;



    ProductList listByStore(MerchantStore store, Language language,
                            ProductCriteria criteria);



    void saveOrUpdate(Product product) throws ServiceException;

    List<Product> listByStore(MerchantStore store);

    List<Product> listByTaxClass(TaxClass taxClass);

    List<Product> getProducts(List<Long> categoryIds, Language language)
            throws ServiceException;

    Product getBySeUrl(MerchantStore store, String seUrl, Locale locale);

    Product getByCode(String productCode, Language language);
    
    void saveProductImage(ProductImage image) throws ServiceException;
}
