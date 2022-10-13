package org.orakzai.lab.shop.domain.business.system.dao;

import java.util.List;
import java.util.Optional;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.springframework.stereotype.Repository;


@Repository("integrationModuleRepository")
public interface ModuleConfigurationRepository extends SalesManagerEntityDao<Long, IntegrationModule> {

	List<IntegrationModule> findAllByConfiguration(String module);

	Optional<IntegrationModule> findByCode(String moduleCode);

}
