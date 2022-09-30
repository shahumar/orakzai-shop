package org.orakzai.lab.shop.domain.business.system.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.springframework.stereotype.Repository;


@Repository("integrationModuleDao")
public interface ModuleConfigurationDao extends SalesManagerEntityDao<Long, IntegrationModule> {

//	List<IntegrationModule> getModulesConfiguration(String module);
//
//	IntegrationModule getByCode(String moduleCode);

}
