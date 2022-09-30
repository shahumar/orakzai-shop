package org.orakzai.lab.shop.domain.business.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.system.dao.SystemConfigurationRepository;
import org.orakzai.lab.shop.domain.business.system.model.SystemConfiguration;
//import org.orakzai.lab.shop.domain.business.system.model.SystemConfiguration_;

@Service("systemConfigurationService")
public class SystemConfigurationServiceImpl extends
		SalesManagerEntityServiceImpl<Long, SystemConfiguration> implements
		SystemConfigurationService {


	private SystemConfigurationRepository systemConfigurationRepository;

	@Autowired
	public SystemConfigurationServiceImpl(SystemConfigurationRepository systemConfigurationRepository) {
			super(systemConfigurationRepository);
			this.systemConfigurationRepository = systemConfigurationRepository;
	}

	public SystemConfiguration getByKey(String key) throws ServiceException {
//		return super.getByField(SystemConfiguration_.key, key);
		return null;
	}




}
