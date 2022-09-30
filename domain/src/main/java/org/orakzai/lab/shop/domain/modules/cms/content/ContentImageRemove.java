package org.orakzai.lab.shop.domain.modules.cms.content;


import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.modules.cms.common.ImageRemove;

public interface ContentImageRemove extends ImageRemove {
	
	
	
	public void removeImage(final String merchantStoreCode,final FileContentType imageContentType, final String imageName) throws ServiceException;

}
