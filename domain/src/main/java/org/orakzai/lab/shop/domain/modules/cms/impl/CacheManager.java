package org.orakzai.lab.shop.domain.modules.cms.impl;

import org.infinispan.manager.EmbeddedCacheManager;

//import org.infinispan.tree.TreeCache;

public interface CacheManager {
	
	public EmbeddedCacheManager getManager();

}
