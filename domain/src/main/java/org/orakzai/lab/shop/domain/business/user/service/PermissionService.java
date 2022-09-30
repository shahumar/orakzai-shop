package org.orakzai.lab.shop.domain.business.user.service;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.Permission;
import org.orakzai.lab.shop.domain.business.user.model.PermissionCriteria;
import org.orakzai.lab.shop.domain.business.user.model.PermissionList;

public interface PermissionService extends SalesManagerEntityService<Integer, Permission> {

	List<Permission> getByName();

	List<Permission> listPermission()  throws ServiceException;

	Permission getById(Integer permissionId);

	void saveOrUpdate(Permission permission);

//	void deletePermission(Permission permission) throws ServiceException;

	List<Permission> getPermissions(List<Integer> groupIds) throws ServiceException;

	void deletePermission(Permission permission) throws ServiceException;

	PermissionList listByCriteria(PermissionCriteria criteria) throws ServiceException ;

	void removePermission(Permission permission, Group group) throws ServiceException;

}
