package org.orakzai.lab.shop.domain.business.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
//import org.orakzai.lab.shop.domain.business.system.service.EmailService;
import org.orakzai.lab.shop.domain.business.user.dao.UserRepository;
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.orakzai.lab.shop.domain.modules.email.Email;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends SalesManagerEntityServiceImpl<Long, User>
		implements UserService {


	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;

	}

//	@Autowired
//	private EmailService emailService;

	@Override
	public User getByUserName(String userName) throws ServiceException {

		return userRepository.findByAdminName(userName);

	}

	@Override
	public void delete(User user) throws ServiceException {

		User u = this.getById(user.getId());
		super.delete(u);

	}

	@Override
	public List<User> listUser() throws ServiceException {
//		try {
//			return userDao.listUser();
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		}
		return null;
	}

	@Override
	public List<User> listByStore(MerchantStore store) throws ServiceException {
//		try {
//			return userDao.listUserByStore(store);
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		}
		return null;
	}


	@Override
	public void saveOrUpdate(User user) throws ServiceException {
		userRepository.save(user);
	}

}
