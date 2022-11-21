package org.orakzai.lab.shop.domain.utils;

import java.util.ArrayList;
import java.util.List;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.modules.cms.impl.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("cache")
public class CacheUtils {


    @Autowired
    @Qualifier("serviceCache")
    private CacheManager cache;

	public final static String REFERENCE_CACHE = "REF";

	public void putInCache(Object object, String keyName) throws Exception {
		cache.getManager().getCache("default").put(keyName, object);
	}


	public Object getFromCache(String keyName) throws Exception {
		return cache.getManager().getCache("default").get(keyName);
	}

	public List<String> getCacheKeys(MerchantStore store) throws Exception {

		List<String> returnKeys = new ArrayList<String>();
		return returnKeys;
	}

	public void shutDownCache() throws Exception {
		throw new Exception();
	}

	public void removeFromCache(String keyName) throws Exception {
		throw new Exception();
	}

	public void removeAllFromCache(MerchantStore store) throws Exception {
		throw new Exception();
	}



}
