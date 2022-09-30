package org.orakzai.lab.shop.domain.business.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.user.dao.GroupRepository;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.GroupType;

@Service("groupService")
public class GroupServiceImpl extends
		SalesManagerEntityServiceImpl<Integer, Group> implements GroupService {

	GroupRepository groupRepository;


	@Autowired
	public GroupServiceImpl(GroupRepository groupRepository) {
		super(groupRepository);
		this.groupRepository = groupRepository;

	}

	@Override
	public List<Group> listGroup(GroupType groupType) throws ServiceException {
		return groupRepository.findAllByGroupType(groupType);
	}

	public List<Group> listGroupByIds(Set<Integer> ids) throws ServiceException {
		return groupRepository.findAllById(ids);
	}


}
