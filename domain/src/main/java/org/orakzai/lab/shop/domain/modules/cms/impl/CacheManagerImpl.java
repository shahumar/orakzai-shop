package org.orakzai.lab.shop.domain.modules.cms.impl;

import org.infinispan.manager.EmbeddedCacheManager;
//import org.infinispan.Cache;
//import org.infinispan.manager.EmbeddedCacheManager;
//import org.infinispan.tree.TreeCache;
//import org.infinispan.tree.TreeCacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("serviceCache")
public class CacheManagerImpl implements CacheManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheManagerImpl.class);

	@Autowired
	EmbeddedCacheManager cacheManager;
	
	
	public EmbeddedCacheManager getManager() {
		return cacheManager;
	}
	

}
