package org.orakzai.lab.shop.domain.business.catalog.category.service;

import org.junit.Assert;
import org.orakzai.lab.shop.domain.business.catalog.category.dao.CategoryRepository;
import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.category.model.CategoryDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
//import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service("categoryService")
public class CategoryServiceImpl extends SalesManagerEntityServiceImpl<Long, Category> implements CategoryService{

    private CategoryRepository categoryRepository;

//    @Autowired
//    @Lazy
//    private ProductService productService;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);

        this.categoryRepository = categoryRepository;
    }

    @Override
    public void save(Category entity) throws ServiceException {

    }

    public void create(Category category) throws ServiceException {

        super.create(category);

        StringBuilder lineage = new StringBuilder();
        Category parent = category.getParent();
        if(parent!=null && parent.getId()!=null && parent.getId()!=0) {
            lineage.append(parent.getLineage()).append("/").append(parent.getId());
            category.setDepth(parent.getDepth()+1);
        } else {
            lineage.append("/");
            category.setDepth(0);
        }
        category.setLineage(lineage.toString());
        super.update(category);


    }

    @Override
    public List<Object[]> countProductsByCategories(MerchantStore store,
                                                    List<Long> categoryIds) throws ServiceException {

//        return categoryDao.countProductsByCategories(store, categoryIds);
    	return null;

    }

    @Override
    public List<Category> listByCodes(MerchantStore store, List<String> codes,
                                      Language language) {
//        return categoryDao.getByCodes(store, codes, language);
    	return null;
    }

    @Override
    public List<Category> listByIds(MerchantStore store, List<Long> ids,
                                    Language language) {
//        return categoryDao.getByIds(store, ids, language);
    	return null;
    	
    }

    @Override
    public Category getByLanguage(long categoryId, Language language) {
//        return categoryDao.getByLanguage(categoryId, language);
    	return null;
    }

    @Override
    public void saveOrUpdate(Category category) throws ServiceException {


        //save or update (persist and attach entities
        if(category.getId()!=null && category.getId()>0) {

            super.update(category);

        } else {

            super.save(category);

        }

    }

    @Override
    public List<Category> listByLineage(MerchantStore store, String lineage) throws ServiceException {
//        try {
//            return categoryDao.listByLineage(store, lineage);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
    	return null;


    }

    @Override
    public List<Category> listByLineage(String storeCode, String lineage) throws ServiceException {
//        try {
//            return categoryDao.listByLineage(storeCode, lineage);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
    	return null;


    }


    @Override
    public List<Category> listBySeUrl(MerchantStore store, String seUrl) throws ServiceException{

//        try {
//            return categoryDao.listBySeUrl(store, seUrl);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
    	return null;

    }

    @Override
    public Category getBySeUrl(MerchantStore store,String seUrl) {
//        return categoryDao.getBySeUrl(store, seUrl);
    	return null;
    }


    @Override
    public Category getByCode(MerchantStore store, String code) throws ServiceException {

//        try {
//            return categoryDao.getByCode(store, code);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
    	return null;

    }

    @Override
    public Category getByCode(String storeCode, String code) throws ServiceException {

//        try {
//            return categoryDao.getByCode(storeCode, code);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }

    	return null;
    }


    @Override
    public Category getById(Long id) {


//        return categoryDao.getById(id);
    	return null;


    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public List<Category> listByParent(Category category) throws ServiceException {

//        try {
//            return categoryDao.listByStoreAndParent(null, category);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }

    	return null;
    }

    @Override
    public List<Category> listByStoreAndParent(MerchantStore store, Category category) throws ServiceException {

//        try {
//            return categoryDao.listByStoreAndParent(store, category);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
    	return null;

    }

    @Override
    public List<Category> listByParent(Category category, Language language) {
        Assert.assertNotNull("Category cannot be null", category );
        Assert.assertNotNull("Language cannot be null", language);
        Assert.assertNotNull( "category.merchantStore cannot be null", category.getMerchantStore());

//        return categoryDao.listByParent(category, language);
        return null;
    }


    @Override
    public void addCategoryDescription(Category category, CategoryDescription description)
            throws ServiceException {



        try {
            category.getDescriptions().add(description);
            description.setCategory(category);
            update(category);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }


    //@Override
    public void delete(Category category) throws ServiceException {

        //get category with lineage (subcategories)
//        StringBuilder lineage = new StringBuilder();
//        lineage.append(category.getLineage()).append(category.getId()).append(Constants.SLASH);
//        List<Category> categories = this.listByLineage(category.getMerchantStore(), lineage.toString());
//
//        Category dbCategory = this.getById( category.getId() );
//
//
//        if(dbCategory != null && dbCategory.getId().longValue() == category.getId().longValue() ) {
//
//
//            categories.add(dbCategory);
//
//
//            Collections.reverse(categories);
//
//            List<Long> categoryIds = new ArrayList<>();
//
//
//            for(Category c : categories) {
//                categoryIds.add(c.getId());
//            }
//
//            List<Product> products = productService.getProducts(categoryIds);
//            System.out.println(this.categoryDao);
//            org.hibernate.Session session = this.categoryDao.getEntityManager().unwrap(org.hibernate.Session.class);//need to refresh the session to update all product categories
//
//
//            for(Product product : products) {
//                session.evict(product);//refresh product so we get all product categories
//                Product dbProduct = productService.getById(product.getId());
//                Set<Category> productCategories = dbProduct.getCategories();
//                if(productCategories.size()>1) {
//                    for(Category c : categories) {
//                        productCategories.remove(c);
//                        productService.update(dbProduct);
//                    }
//
//                    if(product.getCategories()==null || product.getCategories().size()==0) {
//                        productService.delete(dbProduct);
//                    }
//
//                } else {
//                    productService.delete(dbProduct);
//                }
//
//
//            }
//
//            Category categ = this.getById(category.getId());
//            categoryDao.delete(categ);

//        }

    }


    @Override
    public CategoryDescription getDescription(Category category, Language language) {


        for (CategoryDescription description : category.getDescriptions()) {
            if (description.getLanguage().equals(language)) {
                return description;
            }
        }
        return null;
    }

    @Override
    public void addChild(Category parent, Category child) throws ServiceException {


//        if(child==null || child.getMerchantStore()==null) {
//            throw new ServiceException("Child category and merchant store should not be null");
//        }
//
//        try {
//
//            if(parent==null) {
//
//                //assign to root
//                child.setParent(null);
//                child.setDepth(0);
//                //child.setLineage(new StringBuilder().append("/").append(child.getId()).append("/").toString());
//                child.setLineage("/");
//
//            } else {
//
//                Category p = this.getById(parent.getId());//parent
//
//
//
//
//                String lineage = p.getLineage();
//                int depth = p.getDepth();//TODO sometimes null
//
//                child.setParent(p);
//                child.setDepth(depth+1);
//                child.setLineage(new StringBuilder().append(lineage).append(p.getId()).append("/").toString());
//
//
//            }
//
//
//            update(child);
//            StringBuilder childLineage = new StringBuilder();
//            childLineage.append(child.getLineage()).append(child.getId()).append("/");
//            List<Category> subCategories = listByLineage(child.getMerchantStore(),childLineage.toString());
//
//
//            //ajust all sub categories lineages
//            if(subCategories!=null && subCategories.size()>0) {
//                for(Category subCategory : subCategories) {
//                    if(child.getId()!=subCategory.getId()) {
//                        addChild(child, subCategory);
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }


    }

    @Override
    public List<Category> listByDepth(MerchantStore store, int depth) {
//        return categoryDao.listByDepth(store, depth);
    	return null;
    }

    @Override
    public List<Category> listByDepth(MerchantStore store, int depth, Language language) {
//        return categoryDao.listByDepth(store, depth, language);
    	return null;
    }

    @Override
    public List<Category> getByName(MerchantStore store, String name, Language language) throws ServiceException {
//
//        try {
//            return categoryDao.getByName(store, name, language);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
    	return null;


    }



    @Override
    public List<Category> listByStore(MerchantStore store)
            throws ServiceException {

//        try {
//            return categoryDao.listByStore(store);
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
    	return null;
    }

    @Override
    public List<Category> listByStore(MerchantStore store, Language language)
            throws ServiceException {

        try {
            return categoryRepository.listByStore(store, language);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

}
