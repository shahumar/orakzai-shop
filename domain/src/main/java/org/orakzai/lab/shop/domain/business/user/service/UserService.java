package org.orakzai.lab.shop.domain.business.user.service;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.orakzai.lab.shop.domain.modules.email.Email;

public interface UserService extends SalesManagerEntityService<Long, User> {

	User getByUserName(String userName) throws ServiceException;

	List<User> listUser() throws ServiceException;

	void saveOrUpdate(User user) throws ServiceException;

	List<User> listByStore(MerchantStore store) throws ServiceException;



}
