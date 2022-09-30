package org.orakzai.lab.shop.domain.business.merchant.repository;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerJpaDaoSupport;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("merchantStoreRepository")
public interface MerchantStoreRepository extends SalesManagerEntityDao<Integer, MerchantStore>{


//    @Query("from Product as p join fetch p.availabilities pa join fetch p.descriptions pd " +
//            "join fetch pa.prices pap join fetch pap.descriptions papd left join fetch p.images images " +
//            "left join fetch p.attributes pattr left join fetch pattr.productOption po " +
//            "left join fetch po.descriptions pod left join fetch pattr.productOptionValue pov" +
//            "left join fetch pov.descriptions povd where pm.merchantId=:mid")
//    public Collection<Product> getProducts(@Param("mid") MerchantStore mid) throws ServiceException;

    MerchantStore findByCode(String code);
}
