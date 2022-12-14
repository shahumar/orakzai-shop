package org.orakzai.lab.shop.domain.business.system.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.system.dao.ModuleConfigurationRepository;
import org.orakzai.lab.shop.domain.business.system.model.IntegrationModule;
import org.orakzai.lab.shop.domain.business.system.model.ModuleConfig;
import org.orakzai.lab.shop.domain.utils.CacheUtils;

@Slf4j
@Service("moduleConfigurationService")
public class ModuleConfigurationServiceImpl extends
		SalesManagerEntityServiceImpl<Long, IntegrationModule> implements
		ModuleConfigurationService {


	private ModuleConfigurationRepository integrationModuleRepository;

	@Autowired
	private CacheUtils cache;

	@Autowired
	public ModuleConfigurationServiceImpl(
			ModuleConfigurationRepository integrationModuleRepository) {
			super(integrationModuleRepository);
		this.integrationModuleRepository = integrationModuleRepository;
	}

	@Override
	public IntegrationModule getByCode(String moduleCode) {
		return integrationModuleRepository.findByCode(moduleCode).get();
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<IntegrationModule> getIntegrationModules(String module) {
		List<IntegrationModule> modules = null;
		try {
			modules = (List<IntegrationModule>) cache.getFromCache("INTEGRATION_M)" + module);
			if(modules==null) {
				modules = integrationModuleRepository.findAllByModule(module);
				for(IntegrationModule mod : modules) {
					String regions = mod.getRegions();
					if(regions!=null) {
						Object objRegions = JSONValue.parse(regions);
						JSONArray arrayRegions = (JSONArray) objRegions;
						Iterator i = arrayRegions.iterator();
						while(i.hasNext()) {
							mod.getRegionsSet().add((String)i.next());
						}
					}
					String details = mod.getConfigDetails();
					if(details!=null) {
						Map<String,String> objDetails= (Map<String, String>) JSONValue.parse(details);
						mod.setDetails(objDetails);
					}
					String configs = mod.getConfiguration();
					if(configs!=null) {
						Object objConfigs=JSONValue.parse(configs);
						JSONArray arrayConfigs=(JSONArray)objConfigs;
						Map<String,ModuleConfig> moduleConfigs = new HashMap<String,ModuleConfig>();
						Iterator i = arrayConfigs.iterator();
						while(i.hasNext()) {
							Map values = (Map)i.next();
							String env = (String)values.get("env");
		            		ModuleConfig config = new ModuleConfig();
		            		config.setScheme((String)values.get("scheme"));
		            		config.setHost((String)values.get("host"));
		            		config.setPort((String)values.get("port"));
		            		config.setUri((String)values.get("uri"));
		            		config.setEnv((String)values.get("env"));
		            		if((String)values.get("config1")!=null) {
		            			config.setConfig1((String)values.get("config1"));
		            		}
		            		if((String)values.get("config2")!=null) {
		            			config.setConfig1((String)values.get("config2"));
		            		}

		            		moduleConfigs.put(env, config);
						}
						mod.setModuleConfigs(moduleConfigs);
					}
				}
				cache.putInCache(modules, "INTEGRATION_M)" + module);
			}

		} catch (Exception e) {
			log.error("getIntegrationModules()", e);
		}
		return modules;
	}
	
}
