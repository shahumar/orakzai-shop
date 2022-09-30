/**
 * 
 */
package org.orakzai.lab.shop.domain.modules.cms.content;

import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;


/**
 * @author Umesh Awasthi
 *
 */
public interface FileRemove
{
    public void removeFile(String merchantStoreCode, FileContentType staticContentType, String fileName) throws ServiceException;
    public void removeFiles(String merchantStoreCode) throws ServiceException;

}
