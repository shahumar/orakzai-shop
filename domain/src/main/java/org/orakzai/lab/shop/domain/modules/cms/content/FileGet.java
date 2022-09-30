package org.orakzai.lab.shop.domain.modules.cms.content;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;


/**
 * Methods to retrieve the static content from the CMS
 * @author Carl Samson
 *
 */
public interface FileGet
{

	public OutputContentFile getFile(final String merchantStoreCode, FileContentType fileContentType, String contentName) throws ServiceException;
    public List<String> getFileNames(final String merchantStoreCode,FileContentType fileContentType) throws ServiceException;
    public List<OutputContentFile> getFiles(final String merchantStoreCode, FileContentType fileContentType) throws ServiceException;
}
