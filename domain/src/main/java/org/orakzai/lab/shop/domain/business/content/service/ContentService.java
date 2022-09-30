package org.orakzai.lab.shop.domain.business.content.service;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.Content;
import org.orakzai.lab.shop.domain.business.content.model.ContentDescription;
import org.orakzai.lab.shop.domain.business.content.model.ContentType;
import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.InputContentFile;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;



public interface ContentService
    extends SalesManagerEntityService<Long, Content>
{

    public List<Content> listByType( ContentType contentType, MerchantStore store, Language language )
        throws ServiceException;

    public List<Content> listByType( List<ContentType> contentType, MerchantStore store, Language language )
        throws ServiceException;

    Content getByCode( String code, MerchantStore store )
        throws ServiceException;

    void saveOrUpdate( Content content )
        throws ServiceException;

    Content getByCode( String code, MerchantStore store, Language language )
        throws ServiceException;

    void addContentFile( String merchantStoreCode, InputContentFile contentFile )
        throws ServiceException;

    void addContentFiles(String merchantStoreCode,List<InputContentFile> contentFilesList) throws ServiceException;

    public void removeFile( String merchantStoreCode, FileContentType fileContentType, String fileName) throws ServiceException;

    public void removeFiles( String merchantStoreCode ) throws ServiceException;

    public OutputContentFile getContentFile( String merchantStoreCode, FileContentType fileContentType, String fileName )
        throws ServiceException;

    public List<OutputContentFile> getContentFiles( String merchantStoreCode, FileContentType fileContentType )
                    throws ServiceException;


    List<String> getContentFilesNames(String merchantStoreCode,
			FileContentType fileContentType) throws ServiceException;

	void addLogo(String merchantStoreCode, InputContentFile cmsContentImage)
			throws ServiceException;

	void addOptionImage(String merchantStoreCode, InputContentFile cmsContentImage)
			throws ServiceException;



	List<Content> listByType(List<ContentType> contentType, MerchantStore store)
			throws ServiceException;

	List<ContentDescription> listNameByType(List<ContentType> contentType,
			MerchantStore store, Language language) throws ServiceException;

	Content getByLanguage(Long id, Language language) throws ServiceException;

	ContentDescription getBySeUrl(MerchantStore store, String seUrl);

}
