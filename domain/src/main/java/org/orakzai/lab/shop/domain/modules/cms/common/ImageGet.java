package org.orakzai.lab.shop.domain.modules.cms.common;

import java.util.List;

import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;

public interface ImageGet
{

    public List<OutputContentFile> getImages( final String merchantStoreCode, FileContentType imageContentType )
        throws ServiceException;

}
