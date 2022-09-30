package org.orakzai.lab.shop.domain.business.user.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends SalesManagerEntityDao<Long, User> {

//	User getByUserName(String userName);
//
//	List<User> listUser();
//
//	List<User> listUserByStore(MerchantStore store);
	
	User findByAdminName(String username);



}
