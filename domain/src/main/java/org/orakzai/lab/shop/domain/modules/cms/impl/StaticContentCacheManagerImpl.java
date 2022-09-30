/**
 * 
 */
package org.orakzai.lab.shop.domain.modules.cms.impl;


public class StaticContentCacheManagerImpl extends CacheManagerImpl
{
    private static  StaticContentCacheManagerImpl cacheManager = null;
    private final static String NAMED_CACHE = "FilesRepository";
    

   public static StaticContentCacheManagerImpl getInstance() {
        
        if(cacheManager==null) {
            cacheManager = new StaticContentCacheManagerImpl();
        }
        
        return cacheManager;
      
        
    }
}
