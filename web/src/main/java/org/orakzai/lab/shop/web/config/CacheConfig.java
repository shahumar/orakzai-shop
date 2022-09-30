package org.orakzai.lab.shop.web.config;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	

	@Bean
	public InfinispanCacheConfigurer cacheConfigurer() {
		return cacheManager -> {
			final org.infinispan.configuration.cache.Configuration config = 
					new ConfigurationBuilder()
						.jmxStatistics()
						.enable()
						.build();
			cacheManager.defineConfiguration("shopme", config);
		};
	}
	
//	@Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("shopme");
//    }
}
