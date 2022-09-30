package org.orakzai.lab.shop.domain.business.merchant.service;

import java.util.List;

import javax.transaction.Transactional;

import org.orakzai.lab.shop.domain.business.merchant.repository.MerchantStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.catalog.category.model.Category;
//import org.orakzai.lab.shop.domain.business.catalog.category.service.CategoryService;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.Manufacturer;
//import org.orakzai.lab.shop.domain.business.catalog.product.service.manufacturer.ManufacturerService;
//import org.orakzai.lab.shop.domain.business.catalog.product.service.type.ProductTypeService;
//import org.orakzai.lab.shop.domain.business.content.service.ContentService;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
//import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
//import org.orakzai.lab.shop.domain.business.order.service.OrderService;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfiguration;
//import org.orakzai.lab.shop.domain.business.system.service.MerchantConfigurationService;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;
//import org.orakzai.lab.shop.domain.business.tax.service.TaxClassService;
import org.orakzai.lab.shop.domain.business.user.model.User;
//import org.orakzai.lab.shop.domain.business.user.service.UserService;

@Transactional
@Service("merchantService")
public class MerchantStoreServiceImpl extends SalesManagerEntityServiceImpl<Integer, MerchantStore>
		implements MerchantStoreService {



//	@Autowired
//	protected ProductTypeService productTypeService;
//
//	@Autowired
//	private TaxClassService taxClassService;
//
//	@Autowired
//	private ContentService contentService;
//
//	@Autowired
//	private MerchantConfigurationService merchantConfigurationService;
//
//	@Autowired
//	private CategoryService categoryService;

//	@Autowired
//	private UserService userService;

//	@Autowired
//	private OrderService orderService;
//
//	@Autowired
//	private CustomerService customerService;
//
//	@Autowired
//	private ManufacturerService manufacturerService;

	private MerchantStoreRepository merchantStoreRepository;

	@Autowired
	public MerchantStoreServiceImpl(MerchantStoreRepository merchantStoreDao) {
		super(merchantStoreDao);
		this.merchantStoreRepository = merchantStoreDao;
	}


	//@Override
	public MerchantStore getMerchantStore(String merchantStoreCode) throws ServiceException {
		return merchantStoreRepository.findByCode(merchantStoreCode);
	}

	@Override
	public void saveOrUpdate(MerchantStore store) throws ServiceException {

		if(store.getId()==null) {
			merchantStoreRepository.save(store);
		} else {
			super.update(store);
		}
	}

	@Override
	public MerchantStore getByCode(String code) throws ServiceException {

		return merchantStoreRepository.findByCode(code);
	}

	@Override
	public void delete(MerchantStore merchant) {

//		merchant = this.getById(merchant.getId());
//
//
//		//reference
//		List<Manufacturer> manufacturers = manufacturerService.listByStore(merchant);
//		for(Manufacturer manufacturer : manufacturers) {
//			manufacturerService.delete(manufacturer);
//		}
//
//		List<MerchantConfiguration> configurations = merchantConfigurationService.listByStore(merchant);
//		for(MerchantConfiguration configuration : configurations) {
//			merchantConfigurationService.delete(configuration);
//		}
//
//
//		//TODO taxService
//		List<TaxClass> taxClasses = taxClassService.listByStore(merchant);
//		for(TaxClass taxClass : taxClasses) {
//			taxClassService.delete(taxClass);
//		}
//
//		//content
//		contentService.removeFiles(merchant.getCode());
//		//TODO staticContentService.removeImages
//
//		//category / product
//		List<Category> categories = categoryService.listByStore(merchant);
//		for(Category category : categories) {
//			categoryService.delete(category);
//		}
//
//		//users
////		List<User> users = userService.listByStore(merchant);
////		for(User user : users) {
////			userService.delete(user);
////		}
//
//		//customers
//		List<Customer> customers = customerService.listByStore(merchant);
//		for(Customer customer : customers) {
//			customerService.delete(customer);
//		}
//
//		//orders
//		List<Order> orders = orderService.listByStore(merchant);
//		for(Order order : orders) {
//			orderService.delete(order);
//		}
//
//		super.delete(merchant);

	}

}
