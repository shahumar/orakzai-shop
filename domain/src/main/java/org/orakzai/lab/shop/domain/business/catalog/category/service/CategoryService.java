package org.orakzai.lab.shop.domain.business.catalog.category.service;

import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.category.model.CategoryDescription;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

import java.util.List;

public interface CategoryService extends SalesManagerEntityService<Long, Category> {

    List<Category> listByLineage(MerchantStore store, String lineage) throws ServiceException;

    List<Category> listBySeUrl(MerchantStore store, String seUrl) throws ServiceException;

    CategoryDescription getDescription(Category category, Language language) throws ServiceException;

    void addCategoryDescription(Category category, CategoryDescription description) throws ServiceException;

    void addChild(Category parent, Category child) throws ServiceException;

    List<Category> listByParent(Category category) throws ServiceException;

    List<Category> listByStoreAndParent(MerchantStore store, Category category) throws ServiceException;


    List<Category> getByName(MerchantStore store, String name, Language language) throws ServiceException;

    List<Category> listByStore(MerchantStore store) throws ServiceException;

    Category getByCode(MerchantStore store, String code)
            throws ServiceException;

    List<Category> listByStore(MerchantStore store, Language language)
            throws ServiceException;

    void saveOrUpdate(Category category) throws ServiceException;

    List<Category> listByDepth(MerchantStore store, int depth);

    List<Category> listByDepth(MerchantStore store, int depth, Language language);

    List<Category> listByLineage(String storeCode, String lineage)
            throws ServiceException;

    Category getByCode(String storeCode, String code) throws ServiceException;

    Category getBySeUrl(MerchantStore store, String seUrl);

    List<Category> listByParent(Category category, Language language);

    Category getByLanguage(long categoryId, Language language);

    List<Object[]> countProductsByCategories(MerchantStore store,
                                             List<Long> categoryIds) throws ServiceException;

    List<Category> listByCodes(MerchantStore store, List<String> codes,
                               Language language);

    List<Category> listByIds(MerchantStore store, List<Long> ids,
                             Language language);
}
