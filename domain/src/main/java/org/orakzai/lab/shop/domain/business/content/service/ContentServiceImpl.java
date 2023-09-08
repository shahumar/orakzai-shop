package org.orakzai.lab.shop.domain.business.content.service;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.dao.ContentRepository;
import org.orakzai.lab.shop.domain.business.content.model.Content;
import org.orakzai.lab.shop.domain.business.content.model.ContentDescription;
import org.orakzai.lab.shop.domain.business.content.model.ContentType;
import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.InputContentFile;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.modules.cms.content.StaticContentFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service( "contentService" )
public class ContentServiceImpl
    extends SalesManagerEntityServiceImpl<Long, Content> implements ContentService {


    private final ContentRepository contentRepository;

    @Autowired
    StaticContentFileManager staticContentFileManager;

    @Autowired
    StaticContentFileManager contentFileManager;

    @Autowired
    public ContentServiceImpl( final ContentRepository contentRepository )
    {
        super( contentRepository );

        this.contentRepository = contentRepository;
    }

    @Override
    public List<Content> listByType( final ContentType contentType, final MerchantStore store, final Language language )
        throws ServiceException
    {

//        return contentRepository.listByType( contentType, store, language );
    	return null;
    }


    @Override
    public Content getByLanguage(Long id, Language language) throws ServiceException {
//    	return contentRepository.getByLanguage(id, language);
    	return null;
    }



    @Override
    public List<Content> listByType( final List<ContentType> contentType, final MerchantStore store, final Language language )
        throws ServiceException
    {


//        return contentRepository.listByType( contentType, store, language );
    	return null;
    }

    @Override
    public List<ContentDescription> listNameByType( final List<ContentType> contentType, final MerchantStore store, final Language language )
            throws ServiceException
    {


//            return contentRepository.listNameByType(contentType, store, language);
    	return null;
    }

    @Override
    public List<Content> listByType( final List<ContentType> contentType, final MerchantStore store )
    throws ServiceException
    {


//	    return contentRepository.listByType( contentType, store );
    	return null;
    }

    @Override
    public Content getByCode( final String code, final MerchantStore store )
        throws ServiceException
    {

//        return contentRepository.getByCode( code, store );
    	return null;

    }

    @Override
    public Content getById( Long id ) {
    	return contentRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate( final Content content )
        throws ServiceException
    {

        // save or update (persist and attach entities
        if ( content.getId() != null && content.getId() > 0 )
        {
            super.update( content );
        }
        else
        {
        	contentRepository.save( content );
        }

    }

    @Override
    public Content getByCode( final String code, final MerchantStore store, final Language language )
        throws ServiceException
    {
//        return contentRepository.getByCode( code, store, language );
    	return null;
    }


    @Override
    public void addContentFile( final String merchantStoreCode, final InputContentFile contentFile )
        throws ServiceException
    {
        Assert.notNull( merchantStoreCode, "Merchant store Id can not be null" );
        Assert.notNull( contentFile, "InputContentFile image can not be null" );
        Assert.notNull( contentFile.getFileContentType(), "InputContentFile.fileContentType can not be null" );

        if(contentFile.getFileContentType().name().equals(FileContentType.IMAGE.name())
        		|| contentFile.getFileContentType().name().equals(FileContentType.STATIC_FILE.name())) {
        	addFile(merchantStoreCode,contentFile);
        } else {
        	addImage(merchantStoreCode,contentFile);
        }



    }

    @Override
    public void addLogo( final String merchantStoreCode, InputContentFile cmsContentImage )
    throws ServiceException {


		    Assert.notNull( merchantStoreCode, "Merchant store Id can not be null" );
		    Assert.notNull( cmsContentImage, "CMSContent image can not be null" );


		    cmsContentImage.setFileContentType(FileContentType.LOGO);
		    addImage(merchantStoreCode,cmsContentImage);


    }

    @Override
    public void addOptionImage( final String merchantStoreCode, InputContentFile cmsContentImage )
    throws ServiceException {


		    Assert.notNull( merchantStoreCode, "Merchant store Id can not be null" );
		    Assert.notNull( cmsContentImage, "CMSContent image can not be null" );
		    cmsContentImage.setFileContentType(FileContentType.PROPERTY);
		    addImage(merchantStoreCode,cmsContentImage);


    }


    private void addImage(final String merchantStoreCode, InputContentFile contentImage ) throws ServiceException {

    	try
	    {
	        log.info( "Adding content image for merchant id {}", merchantStoreCode);
//	        contentFileManager.addFile( merchantStoreCode, contentImage );

	    } catch ( Exception e )
		 {
		        log.error( "Error while trying to convert input stream to buffered image", e );
		        throw new ServiceException( e );

		 } finally {

			 try {
				if(contentImage.getFile()!=null) {
					contentImage.getFile().close();
				}
			} catch (Exception ignore) {}

		 }

    }

    private void addFile(final String merchantStoreCode, InputContentFile contentImage ) throws ServiceException {

    	try
	    {
	        log.info( "Adding content file for merchant id {}", merchantStoreCode);
//	        staticContentFileManager.addFile(merchantStoreCode, contentImage);

	    } catch ( Exception e )
		 {
		        log.error( "Error while trying to convert input stream to buffered image", e );
		        throw new ServiceException( e );

		 } finally {

			 try {
				if(contentImage.getFile()!=null) {
					contentImage.getFile().close();
				}
			} catch (Exception ignore) {}
		 }

    }


	@Override
	public void addContentFiles(String merchantStoreCode,
			List<InputContentFile> contentFilesList) throws ServiceException {

        Assert.notNull( merchantStoreCode, "Merchant store ID can not be null" );
        Assert.notEmpty( contentFilesList, "File list can not be empty" );
        log.info( "Adding total {} images for given merchant",contentFilesList.size() );

        log.info( "Adding content images for merchant...." );
        //contentFileManager.addImages( merchantStoreCode, contentImagesList );
//        staticContentFileManager.addFiles(merchantStoreCode, contentFilesList);

        try {
			for(InputContentFile file : contentFilesList) {
				if(file.getFile()!=null) {
					file.getFile().close();
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}


	@Override
	public void removeFile(String merchantStoreCode,
			FileContentType fileContentType, String fileName)
			throws ServiceException {
        Assert.notNull( merchantStoreCode, "Merchant Store Id can not be null" );
        Assert.notNull( fileContentType, "Content file type can not be null" );
        Assert.notNull( fileName, "Content Image type can not be null" );


        //check where to remove the file
        if(fileContentType.name().equals(FileContentType.IMAGE.name())
        		|| fileContentType.name().equals(FileContentType.STATIC_FILE.name())) {
//        	staticContentFileManager.removeFile(merchantStoreCode, fileContentType, fileName);
        } else {
//        	contentFileManager.removeFile( merchantStoreCode, fileContentType, fileName );
        }

	}


	@Override
	public void removeFiles(String merchantStoreCode) throws ServiceException {
        Assert.notNull( merchantStoreCode, "Merchant Store Id can not be null" );



//        contentFileManager.removeFiles( merchantStoreCode );
//        staticContentFileManager.removeFiles(merchantStoreCode);

	}



	@Override
	public OutputContentFile getContentFile(String merchantStoreCode, FileContentType fileContentType, String fileName)
			throws ServiceException {
        Assert.notNull( merchantStoreCode, "Merchant store ID can not be null" );
        Assert.notNull( fileName, "File name can not be null" );

        if(fileContentType.name().equals(FileContentType.IMAGE.name())
        		|| fileContentType.name().equals(FileContentType.STATIC_FILE.name())) {
        	return staticContentFileManager.getFile(merchantStoreCode, fileContentType, fileName);

        } else {
        	return contentFileManager.getFile( merchantStoreCode, fileContentType, fileName );
        }

	}


	@Override
	public List<OutputContentFile> getContentFiles(String merchantStoreCode,
			FileContentType fileContentType) throws ServiceException {
        Assert.notNull( merchantStoreCode, "Merchant store Id can not be null" );
//        return staticContentFileManager.getFiles(merchantStoreCode, fileContentType);
        return null;
	}

	@Override
	public List<String> getContentFilesNames(String merchantStoreCode,
			FileContentType fileContentType) throws ServiceException {
        Assert.notNull( merchantStoreCode, "Merchant store Id can not be null" );

//        if(fileContentType.name().equals(FileContentType.IMAGE.name())
//        		|| fileContentType.name().equals(FileContentType.STATIC_FILE.name())) {
//        	return staticContentFileManager.getFileNames(merchantStoreCode, fileContentType);
//        } else {
//        	return contentFileManager.getFileNames(merchantStoreCode, fileContentType);
//        }
        return null;
	}

	@Override
	public ContentDescription getBySeUrl(MerchantStore store,String seUrl) {
//		return contentRepository.getBySeUrl(store, seUrl);
		return null;
	}



}
