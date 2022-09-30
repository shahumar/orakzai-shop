package org.orakzai.lab.shop.domain.modules.cms.common;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;


public interface ImageRemove {
	
	
	public void removeImages(final String merchantStoreCode) throws ServiceException;
	
}
