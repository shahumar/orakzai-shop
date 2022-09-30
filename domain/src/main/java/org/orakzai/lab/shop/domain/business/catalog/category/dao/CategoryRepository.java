package org.orakzai.lab.shop.domain.business.catalog.category.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface CategoryRepository extends SalesManagerEntityDao<Long, Category> {

//    List<Category> listBySeUrl(MerchantStore store, String seUrl);
//
//    List<Category> listByStoreAndParent(MerchantStore store, Category category);
//
//    List<Category> listByLineage(MerchantStore store, String lineage);
//
//    List<Category> getByName(MerchantStore store, String name, Language language);
//
//    Category getByCode(MerchantStore store, String code);
//
    List<Category> findByMerchantStore(MerchantStore store);

    @Query("select DISTINCT(cat) from Category cat left join fetch cat.descriptions catd "
    		+ "left join fetch cat.merchantStore cats "
    		+ "where catd.language=:language and cat.merchantStore =:store ")
    List<Category> listByStore(MerchantStore store, Language language);
//
//    Category getById(Long id);
//
//    List<Category> listByDepth(MerchantStore store, int depth);
//
//    List<Category> listByDepth(MerchantStore store, int depth, Language language);
//
//    List<Category> listByLineage(String merchantStoreCode, String lineage);
//
//    Category getByCode(String merchantStoreCode, String code);
//
//    Category getBySeUrl(MerchantStore store, String seUrl);
//
//    List<Category> listByParent(Category category, Language language);
//
//    Category getByLanguage(long categoryId, Language language);
//
//    List<Object[]> countProductsByCategories(MerchantStore store,
//                                             List<Long> categoryIds);
//
//    List<Category> getByCodes(MerchantStore store, List<String> codes,
//                              Language language);
//
//    List<Category> getByIds(MerchantStore store, List<Long> ids,
//                            Language language);
}
