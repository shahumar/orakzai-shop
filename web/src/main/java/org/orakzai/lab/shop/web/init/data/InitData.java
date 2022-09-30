package org.orakzai.lab.shop.web.init.data;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;

public interface InitData {

	public void initInitialData() throws ServiceException;

	void initProductInitialImage() throws ServiceException;
}
