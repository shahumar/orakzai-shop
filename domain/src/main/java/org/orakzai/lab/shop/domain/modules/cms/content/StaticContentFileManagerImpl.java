/**
 * 
 */
package org.orakzai.lab.shop.domain.modules.cms.content;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.InputContentFile;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("contentFileManager")
public class StaticContentFileManagerImpl extends StaticContentFileManager
{

    @Autowired
    private FilePut uploadFile;
    
    @Autowired
    private FileGet getFile;
    
    @Autowired
    private FileRemove removeFile;


    
    @Override
    public void addFile( final String merchantStoreCode, final InputContentFile inputStaticContentData ) 
    		throws ServiceException {
    	uploadFile.addFile( merchantStoreCode, inputStaticContentData );
        
    }

    @Override
    public void addFiles( final String merchantStoreCode, final List<InputContentFile> inputStaticContentDataList ) 
    		throws ServiceException {
    	uploadFile.addFiles( merchantStoreCode, inputStaticContentDataList );
    }
    
    @Override
    public void removeFile( final String merchantStoreCode, final FileContentType staticContentType, final String fileName) 
    		throws ServiceException {
    	removeFile.removeFile(merchantStoreCode, staticContentType, fileName);        
    }

	@Override
	public OutputContentFile getFile(String merchantStoreCode, FileContentType fileContentType, String contentName)
			throws ServiceException {
		return getFile.getFile(merchantStoreCode, fileContentType, contentName);
	}

	@Override
	public List<String> getFileNames(String merchantStoreCode, FileContentType fileContentType) throws ServiceException {
		return getFile.getFileNames(merchantStoreCode, fileContentType);
	}

	@Override
	public List<OutputContentFile> getFiles(String merchantStoreCode, FileContentType fileContentType) throws ServiceException {
		return getFile.getFiles(merchantStoreCode, fileContentType);
	}

	@Override
	public void removeFiles(String merchantStoreCode) throws ServiceException {
		removeFile.removeFiles(merchantStoreCode);
	}
    
   

	public void setRemoveFile(FileRemove removeFile) {
		this.removeFile = removeFile;
	}

	public FileRemove getRemoveFile() {
		return removeFile;
	}

	public void setGetFile(FileGet getFile) {
		this.getFile = getFile;
	}

	public FileGet getGetFile() {
		return getFile;
	}

	public void setUploadFile(FilePut uploadFile) {
		this.uploadFile = uploadFile;
	}

	public FilePut getUploadFile() {
		return uploadFile;
	}
  
    
}
