package org.orakzai.lab.shop.domain.modules.cms.impl;



public class StoreCacheManagerImpl extends CacheManagerImpl {
	
	
	private static  StoreCacheManagerImpl cacheManager = null;
	private final static String NAMED_CACHE = "StoreRepository";
	
	
	public static StoreCacheManagerImpl getInstance() {
		
		if(cacheManager==null) {
			cacheManager = new StoreCacheManagerImpl();

		}
		
		return cacheManager;
		
		
	}



}

