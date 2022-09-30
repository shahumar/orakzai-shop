package org.orakzai.lab.shop.domain.business.system.dao;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.system.model.SystemConfiguration;
import org.springframework.stereotype.Repository;


@Repository("systemConfigurationDao")
public interface SystemConfigurationRepository extends SalesManagerEntityDao<Long, SystemConfiguration> {

}
