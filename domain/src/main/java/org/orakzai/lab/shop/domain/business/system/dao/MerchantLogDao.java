package org.orakzai.lab.shop.domain.business.system.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.system.model.MerchantLog;
import org.springframework.stereotype.Repository;


@Repository("merchantLogDao")
public interface MerchantLogDao extends SalesManagerEntityDao<Long, MerchantLog> {


//	List<MerchantLog> listByMerchant(MerchantStore store);
	
}
