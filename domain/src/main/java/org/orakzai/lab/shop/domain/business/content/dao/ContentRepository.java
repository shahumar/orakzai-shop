package org.orakzai.lab.shop.domain.business.content.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.Content;
import org.orakzai.lab.shop.domain.business.content.model.ContentDescription;
import org.orakzai.lab.shop.domain.business.content.model.ContentType;
import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.springframework.stereotype.Repository;


public interface ContentRepository extends SalesManagerEntityDao<Long, Content> {

//	List<Content> listByType(ContentType contentType, MerchantStore store,
//			Language language) throws ServiceException;
//
//	List<Content> listByType(List<ContentType> contentType, MerchantStore store,
//			Language language) throws ServiceException;
//
//	Content getByCode(String code, MerchantStore store)
//			throws ServiceException;
//
//	Content getByCode(String code, MerchantStore store, Language language)
//			throws ServiceException;
//
//	List<Content> listByType(List<ContentType> contentType, MerchantStore store)
//			throws ServiceException;
//
//	List<Content> listByType(ContentType contentType, MerchantStore store)
//			throws ServiceException;
//
//	List<ContentDescription> listNameByType(List<ContentType> contentType,
//			MerchantStore store, Language language) throws ServiceException;
//
//	Content getByLanguage(Long id, Language language) throws ServiceException;
//
//	ContentDescription getBySeUrl(MerchantStore store, String seUrl);



}
