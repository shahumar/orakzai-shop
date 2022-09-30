package org.orakzai.lab.shop.domain.business.system.service;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfig;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfigurationType;

public interface MerchantConfigurationService extends
		SalesManagerEntityService<Long, MerchantConfiguration> {

	MerchantConfiguration getMerchantConfiguration(String key, MerchantStore store) throws ServiceException;

	public void saveOrUpdate(MerchantConfiguration entity) throws ServiceException;

	List<MerchantConfiguration> listByStore(MerchantStore store)
			throws ServiceException;

	List<MerchantConfiguration> listByType(MerchantConfigurationType type,
			MerchantStore store) throws ServiceException;

	MerchantConfig getMerchantConfig(MerchantStore store)
			throws ServiceException;

	void saveMerchantConfig(MerchantConfig config, MerchantStore store)
			throws ServiceException;

}
