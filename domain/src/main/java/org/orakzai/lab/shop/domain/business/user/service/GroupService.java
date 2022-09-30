package org.orakzai.lab.shop.domain.business.user.service;

import java.util.List;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.GroupType;

public interface GroupService extends SalesManagerEntityService<Integer, Group> {


	List<Group> listGroup(GroupType groupType) throws ServiceException;
	List<Group> listGroupByIds(Set<Integer> ids) throws ServiceException;

}
