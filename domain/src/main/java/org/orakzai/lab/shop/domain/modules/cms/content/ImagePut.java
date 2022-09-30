package org.orakzai.lab.shop.domain.modules.cms.content;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.InputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;

public interface ImagePut {
	
	
	public void addImage(final String merchantStoreCode, InputContentFile image) throws ServiceException;
	public void addImages(final String merchantStoreCode, List<InputContentFile> imagesList) throws ServiceException;

}
