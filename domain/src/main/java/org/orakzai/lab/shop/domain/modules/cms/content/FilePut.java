/**
 * 
 */
package org.orakzai.lab.shop.domain.modules.cms.content;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.InputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;


/**
 * @author Umesh Awasthi
 *
 */
public interface FilePut
{
    public void addFile(final String merchantStoreCode, InputContentFile inputStaticContentData) throws ServiceException;
    public void addFiles(final String merchantStoreCode, List<InputContentFile> inputStaticContentDataList) throws ServiceException;
}
