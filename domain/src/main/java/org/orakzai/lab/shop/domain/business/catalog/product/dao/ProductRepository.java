package org.orakzai.lab.shop.domain.business.catalog.product.dao;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductCriteria;
import org.orakzai.lab.shop.domain.business.catalog.product.model.ProductList;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.transaction.Transactional;

@Transactional
public interface ProductRepository  extends SalesManagerEntityDao<Long, Product>, JpaSpecificationExecutor<Product> {

//    Product getProductForLocale(long productId, Language language, Locale locale);
//
//    @SuppressWarnings("rawtypes")
//    List<Product> getProductsForLocale(MerchantStore store, Set categoryIds, Language language,
//                                       Locale locale);
//
//    @SuppressWarnings("rawtypes")
//    List<Product> getProductsListByCategories(Set categoryIds);
//
//    ProductList listByStore(MerchantStore store, Language language, ProductCriteria criteria);
//
//    List<Product> listByStore(MerchantStore store);
//
//    List<Product> listByTaxClass(TaxClass taxClass);
//
//    List<Product> getProductsListByCategories(Set<Long> categoryIds,
//                                              Language language);
//
//    Product getBySeUrl(MerchantStore store, String seUrl, Locale locale);
//
//    Product getByCode(String productCode, Language language);
	List<Product> findByMerchantStore(MerchantStore store); 
}
