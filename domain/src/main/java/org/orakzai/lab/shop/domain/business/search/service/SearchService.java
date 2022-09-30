package org.orakzai.lab.shop.domain.business.search.service;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.search.model.SearchKeywords;
import org.orakzai.lab.shop.domain.business.search.model.SearchResponse;

public interface SearchService {

	void index(MerchantStore store, Product product) throws ServiceException;

	void deleteIndex(MerchantStore store, Product product)
			throws ServiceException;

	SearchKeywords searchForKeywords(String collectionName,
			String jsonString, int entriesCount) throws ServiceException;

	SearchResponse search(MerchantStore store, String languageCode, String jsonString,
			int entriesCount, int startIndex) throws ServiceException;

	void initService();

}
