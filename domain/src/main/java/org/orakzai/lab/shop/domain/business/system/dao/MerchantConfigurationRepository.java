package org.orakzai.lab.shop.domain.business.system.dao;

import java.util.List;
import java.util.Optional;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfigurationType;
import org.springframework.stereotype.Repository;

@Repository("merchantConfigurationDao")
public interface MerchantConfigurationRepository extends SalesManagerEntityDao<Long, MerchantConfiguration> {

	
	List<MerchantConfiguration> findAllByMerchantStore(MerchantStore store);
	List<MerchantConfiguration> findAllBymerchantConfigurationTypeAndMerchantStore(MerchantConfigurationType type,
			MerchantStore store);
	
	Optional<MerchantConfiguration> findByKeyAndMerchantStore(String key, MerchantStore store);
	
}
