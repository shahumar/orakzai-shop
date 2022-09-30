package org.orakzai.lab.shop.domain.business.system.service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.system.model.SystemConfiguration;

public interface SystemConfigurationService extends
		SalesManagerEntityService<Long, SystemConfiguration> {

	SystemConfiguration getByKey(String key) throws ServiceException;

}
