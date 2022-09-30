package org.orakzai.lab.shop.web.init.data;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.reference.init.service.InitializationDatabase;
import org.orakzai.lab.shop.domain.business.system.model.SystemConfiguration;
import org.orakzai.lab.shop.domain.business.system.service.SystemConfigurationService;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.GroupType;
import org.orakzai.lab.shop.domain.business.user.model.Permission;
import org.orakzai.lab.shop.domain.business.user.service.GroupService;
import org.orakzai.lab.shop.domain.business.user.service.PermissionService;
import org.orakzai.lab.shop.domain.constants.SystemConstants;
import org.orakzai.lab.shop.domain.utils.CoreConfiguration;
import org.orakzai.lab.shop.web.admin.security.WebUserServices;
import org.orakzai.lab.shop.web.constants.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitializationLoader {

	@Autowired
	InitializationDatabase initializationDatabase;

	@Autowired
	GroupService groupService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	WebUserServices userDetailsService;

	@Autowired
	CoreConfiguration configuration;

	@Autowired
	private SystemConfigurationService systemConfigurationService;

	@Autowired
	private InitData initData;

	@PostConstruct
	@Transactional
	public void init() {
		try {
			log.info("start init data");
			if (initializationDatabase.isEmpty()) {
				log.info("Database is empty populate with ", "sm-shop");
				initializationDatabase.populate("sm-shop");
				populateUserAndGroupInfo();
			}

			if (initializationDatabase.isEmptyInitialData()) {
				log.info("checked initial data");
				loadData();
			}
			initData.initProductInitialImage();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in the init method");
		}
	}

	private void loadData() throws ServiceException {
		String loadTestData = configuration.getProperty(ApplicationConstants.POPULATE_TEST_DATA);
		boolean loadData = !StringUtils.isBlank(loadTestData) && loadTestData.equals(SystemConstants.CONFIG_VALUE_TRUE);
		if (loadData) {
			SystemConfiguration configuration = systemConfigurationService
					.getByKey(ApplicationConstants.TEST_DATA_LOADED);
			if (configuration != null) {
				if (configuration.getKey().equals(ApplicationConstants.TEST_DATA_LOADED)) {
					if (configuration.getValue().equals(SystemConstants.CONFIG_VALUE_TRUE)) {
						return;
					}
				}
			}

			initData.initInitialData();
			configuration = new SystemConfiguration();
			configuration.getAuditSection().setModifiedBy(SystemConstants.SYSTEM_USER);
			configuration.setKey(ApplicationConstants.TEST_DATA_LOADED);
			configuration.setValue(SystemConstants.CONFIG_VALUE_TRUE);
			systemConfigurationService.create(configuration);
		}

	}

	private void populateUserAndGroupInfo() throws Exception {
		Group gsuperadmin = new Group("SUPERADMIN");
		gsuperadmin.setGroupType(GroupType.ADMIN);
		Group gadmin = new Group("ADMIN");
		gadmin.setGroupType(GroupType.ADMIN);
		Group gcatalogue = new Group("ADMIN_CATALOGUE");
		gcatalogue.setGroupType(GroupType.ADMIN);
		Group gstore = new Group("ADMIN_STORE");
		gstore.setGroupType(GroupType.ADMIN);
		Group gorder = new Group("ADMIN_ORDER");
		gorder.setGroupType(GroupType.ADMIN);
		Group gcontent = new Group("ADMIN_CONTENT");
		gcontent.setGroupType(GroupType.ADMIN);

		groupService.create(gsuperadmin);
		groupService.create(gadmin);
		groupService.create(gcatalogue);
		groupService.create(gstore);
		groupService.create(gorder);
		groupService.create(gcontent);

		Permission storeadmin = new Permission("STORE_ADMIN");
		storeadmin.getGroups().add(gsuperadmin);

		storeadmin.getGroups().add(gadmin);
		permissionService.create(storeadmin);

		Permission superadmin = new Permission("SUPERADMIN");
		superadmin.getGroups().add(gsuperadmin);
		permissionService.create(superadmin);

		Permission admin = new Permission("ADMIN");
		admin.getGroups().add(gsuperadmin);
		admin.getGroups().add(gadmin);
		permissionService.create(admin);

		Permission auth = new Permission("AUTH");// Authenticated
		auth.getGroups().add(gsuperadmin);
		auth.getGroups().add(gadmin);
		auth.getGroups().add(gcatalogue);
		auth.getGroups().add(gstore);
		auth.getGroups().add(gorder);
		permissionService.create(auth);

		Permission products = new Permission("PRODUCTS");
		products.getGroups().add(gsuperadmin);
		products.getGroups().add(gadmin);
		products.getGroups().add(gcatalogue);
		permissionService.create(products);

		Permission order = new Permission("ORDER");
		order.getGroups().add(gsuperadmin);
		order.getGroups().add(gorder);
		order.getGroups().add(gadmin);
		permissionService.create(order);

		Permission content = new Permission("CONTENT");
		content.getGroups().add(gsuperadmin);
		content.getGroups().add(gadmin);
		content.getGroups().add(gcontent);
		permissionService.create(content);

		Permission pstore = new Permission("STORE");
		pstore.getGroups().add(gsuperadmin);
		pstore.getGroups().add(gstore);
		pstore.getGroups().add(gadmin);
		permissionService.create(pstore);

		Permission tax = new Permission("TAX");
		tax.getGroups().add(gsuperadmin);
		tax.getGroups().add(gstore);
		tax.getGroups().add(gadmin);
		permissionService.create(tax);

		Permission payment = new Permission("PAYMENT");
		payment.getGroups().add(gsuperadmin);
		payment.getGroups().add(gstore);
		payment.getGroups().add(gadmin);
		permissionService.create(payment);

		Permission customer = new Permission("CUSTOMER");
		customer.getGroups().add(gsuperadmin);
		customer.getGroups().add(gstore);
		customer.getGroups().add(gadmin);
		permissionService.create(customer);

		Permission shipping = new Permission("SHIPPING");
		shipping.getGroups().add(gsuperadmin);
		shipping.getGroups().add(gadmin);
		shipping.getGroups().add(gstore);

		permissionService.create(shipping);

		userDetailsService.createDefaultAdmin();
		Group gcustomer = new Group("CUSTOMER");
		gcustomer.setGroupType(GroupType.CUSTOMER);

		groupService.create(gcustomer);

		Permission gcustomerpermission = new Permission("AUTH_CUSTOMER");
		gcustomerpermission.getGroups().add(gcustomer);
		permissionService.create(gcustomerpermission);
	}

}
