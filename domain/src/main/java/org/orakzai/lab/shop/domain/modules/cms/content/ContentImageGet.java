package org.orakzai.lab.shop.domain.modules.cms.content;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.modules.cms.common.ImageGet;

public interface ContentImageGet extends ImageGet {
	
	public OutputContentFile getImage(final String merchantStoreCode, String imageName,FileContentType imageContentType) throws ServiceException;
	public List<String> getImageNames(final String merchantStoreCode, FileContentType imageContentType) throws ServiceException;

}
