package org.orakzai.lab.shop.domain.business.user.dao;

import java.util.List;
import java.util.Set;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.user.model.Permission;
import org.orakzai.lab.shop.domain.business.user.model.PermissionCriteria;
import org.orakzai.lab.shop.domain.business.user.model.PermissionList;
import org.springframework.stereotype.Repository;


@Repository("permissionDao")
public interface PermissionRepository extends SalesManagerEntityDao<Integer, Permission> {

//	List<Permission> listPermission();
//
//	Permission getById(Integer permissionId);
//
	List<Permission> findAllByGroups_IdIn(Set permissionIds);
//
//	PermissionList listByCriteria(PermissionCriteria criteria);



}
