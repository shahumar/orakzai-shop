package org.orakzai.lab.shop.domain.business.user.dao;

import java.util.List;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.GroupType;
import org.springframework.stereotype.Repository;


@Repository("groupDao")
public interface GroupRepository extends SalesManagerEntityDao<Integer, Group> {

//	List<Group> getGroupsListBypermissions(Set<Integer> ids);
//
	List<Group> findAllByGroupType(GroupType groupType);
//
//	List<Group> listGroupByIds(Set<Integer> ids);
}
