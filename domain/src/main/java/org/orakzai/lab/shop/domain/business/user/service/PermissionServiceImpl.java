package org.orakzai.lab.shop.domain.business.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.service.MerchantStoreService;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.user.dao.PermissionRepository;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.Permission;
import org.orakzai.lab.shop.domain.business.user.model.PermissionCriteria;
import org.orakzai.lab.shop.domain.business.user.model.PermissionList;

@Service("permissionService")
public class PermissionServiceImpl extends
		SalesManagerEntityServiceImpl<Integer, Permission> implements
		PermissionService {

	PermissionRepository permissionRepository;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private MerchantStoreService merchantService;

	@Autowired
	private GroupService groupService;

	@Autowired
	public PermissionServiceImpl(PermissionRepository permissionRepository) {
		super(permissionRepository);
		this.permissionRepository = permissionRepository;

	}

	@Override
	public List<Permission> getByName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Permission getById(Integer permissionId) {
		return permissionRepository.findById(permissionId).get();

	}

	@Override
	public void saveOrUpdate(Permission permission) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePermission(Permission permission) throws ServiceException {
		permission = this.getById(permission.getId());//Prevents detached entity error
		permission.setGroups(null);

		this.delete(permission);
	}


	@Override
	public List<Permission> getPermissions(List<Integer> groupIds)
			throws ServiceException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set ids = new HashSet(groupIds);
		return permissionRepository.findAllByGroups_IdIn(ids);
	}

	@Override
	public PermissionList listByCriteria(PermissionCriteria criteria)
			throws ServiceException {
//		return permissionRepository.listByCriteria(criteria);
		return null;
	}

	@Override
	public void removePermission(Permission permission,Group group) throws ServiceException {
		permission = this.getById(permission.getId());//Prevents detached entity error

		permission.getGroups().remove(group);


//		this.delete(permission);
	}

	@Override
	public List<Permission> listPermission() throws ServiceException {
//		return permissionRepository.listPermission();
		return null;
	}


}
