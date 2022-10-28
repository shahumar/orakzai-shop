package org.orakzai.lab.shop.web.controller.customer.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.customer.service.attribute.CustomerOptionService;
import org.orakzai.lab.shop.domain.business.customer.service.attribute.CustomerOptionValueService;
import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.country.service.CountryService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.reference.language.service.LanguageService;
import org.orakzai.lab.shop.domain.business.reference.zone.service.ZoneService;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartCalculationService;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartService;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.GroupType;
import org.orakzai.lab.shop.domain.business.user.model.Permission;
import org.orakzai.lab.shop.domain.business.user.service.GroupService;
import org.orakzai.lab.shop.domain.business.user.service.PermissionService;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.dto.customer.PersistableCustomer;
import org.orakzai.lab.shop.web.dto.userpassword.UserReset;
import org.orakzai.lab.shop.web.mapper.cart.ShoppingCartDataPopulator;
import org.orakzai.lab.shop.web.mapper.customer.CustomerPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("customerFacade")
public class CustomerFacadeImpl implements CustomerFacade {
	
	private static final int USERNAME_LENGTH = 6;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private AuthenticationManager customerAuthenticationManager;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ShoppingCartCalculationService shoppingCartCalculationService;

	@Autowired
	private PricingService pricingService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private GroupService groupService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CustomerOptionService customerOptionService;

	@Autowired
	private CustomerOptionValueService customerOptionValueService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private ZoneService zoneService;

	@Override
	public Customer getCustomerByUserName(String userName, MerchantStore store) throws Exception {
		
		return customerService.getByNickAndMerchantStore(userName, store);
	}

	@Override
	public void authenticate(Customer customer, String userName, String password) throws Exception {
		Validate.notNull(customer, "Customer cannot be null");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		GrantedAuthority role = new SimpleGrantedAuthority(Constants.PERMISSION_CUSTOMER_AUTHENTICATED);
		authorities.add(role);
		var groupIds = new ArrayList<Integer>();
		List<Group> groups = customer.getGroups();
		if (groups != null) {
			for (Group group : groups) {
				groupIds.add(group.getId());
			}
			
			List<Permission> permissions = permissionService.getPermissions(groupIds);
			for (Permission permission : permissions) {
				GrantedAuthority auth = new SimpleGrantedAuthority(permission.getPermissionName());
				authorities.add(auth);
			}
		}
		
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userName, password, authorities);
		Authentication authentication = customerAuthenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}
	
	@Override
	public ShoppingCartData mergeCart(Customer customerModel, String sessionShoppingCartCode, MerchantStore store,
			Language language) throws Exception {
		log.debug("Starting merge cart process");
		if (customerModel != null) {
			ShoppingCart customerCart = shoppingCartService.getByCustomer(customerModel);
			if (StringUtils.isNotBlank(sessionShoppingCartCode)) {
				ShoppingCart sessionShoppingCart = shoppingCartService.getByCode(sessionShoppingCartCode, store);
				if (sessionShoppingCart != null) {
					if (customerCart == null) {
						if (sessionShoppingCart.getCustomerId() == null) {
							log.debug("Not able to find any ShoppingCart with current customer");
							sessionShoppingCart.setCustomerId(customerModel.getId());
							shoppingCartService.saveOrUpdate(sessionShoppingCart);
							customerCart = shoppingCartService.getById(sessionShoppingCart.getId(), store);
							return populateShoppingCartData(customerCart, store, language);
							
						} else {
							return null;
						}
					} else {
						if (sessionShoppingCart.getCustomerId() == null) {
							log.debug("Customer shopping cart as well session cart is available, merging carts");
							customerCart = shoppingCartService.mergeShoppingCarts(customerCart, sessionShoppingCart, store);
							customerCart = shoppingCartService.getById(customerCart.getId(), store);
							return populateShoppingCartData(customerCart, store, language);
						} else {
							if (sessionShoppingCart.getCustomerId().longValue() == customerModel.getId().longValue()) {
								if (!customerCart.getShoppingCartCode().equals(sessionShoppingCart.getShoppingCartCode())) {
									log.debug("Customer shopping cart as well session cart is available, merging carts");
									customerCart = shoppingCartService.mergeShoppingCarts(customerCart, sessionShoppingCart, store);
									customerCart = shoppingCartService.getById(customerCart.getId(), store);
									return populateShoppingCartData(customerCart, store, language);
								} else {
									return populateShoppingCartData(sessionShoppingCart,store,language);
								}
								
							} else {
								return null;
							}
						}
					}
				} 
				
			} else {
				if (customerCart != null) {
					return populateShoppingCartData(customerCart, store, language);
				}
				return null;
			}
			
		}
		log.info("Seems some issue with system, unable to find any customer after successful authetication");
		return null;
		
	}
	
	@Override
	public Customer getCustomerModel(PersistableCustomer customer, MerchantStore store, Language language)
			throws Exception {
		log.info("Starting to populate customer model from customer data");
		Customer customerModel = null;
		CustomerPopulator populator = new CustomerPopulator();
		populator.setCountryService(countryService);
		populator.setCustomerOptionService(customerOptionService);
		populator.setCustomerOptionValueService(customerOptionValueService);
		populator.setLanguageService(languageService);
		populator.setZoneService(zoneService);
		customerModel = populator.populate(customer, store, language);
		if (!StringUtils.isBlank(customerModel.getPassword()) && !StringUtils.isBlank(customerModel.getNick())) {
			customerModel.setPassword(passwordEncoder.encode(customer.getClearPassword()));
			setCustomerModelDefaultProperties(customerModel, store);
			
		}
		return customerModel;
	}
	
	@Override
	public void setCustomerModelDefaultProperties(Customer customer, MerchantStore store) throws Exception {
		Validate.notNull(customer, "Customer object cannot be null");
		if (customer.getId() == null || customer.getId() == 0) {
			if (StringUtils.isBlank(customer.getNick())) {
				String userName = UserReset.generateRandomString(USERNAME_LENGTH);
				customer.setNick(userName);
			}
			if(StringUtils.isBlank(customer.getPassword())) {
	        	String password = UserReset.generateRandomString();
	        	String encodedPassword = passwordEncoder.encode(password);
	        	customer.setPassword(encodedPassword);
			}
		}
		
		if (CollectionUtils.isEmpty(customer.getGroups())) {
			var groups = groupService.listGroup(GroupType.CUSTOMER);
			for (Group group : groups) {
				if (group.getGroupName().equals(Constants.GROUP_CUSTOMER)) {
					customer.getGroups().add(group);
				}
			}
		}
		
	}
	
	@Override
	public Customer populateCustomerModel(Customer customerModel, PersistableCustomer customer, MerchantStore store,
			Language language) throws Exception {
		log.info( "Starting to populate customer model from customer data" );
        CustomerPopulator populator = new CustomerPopulator();
        populator.setCountryService(countryService);
        populator.setCustomerOptionService(customerOptionService);
        populator.setCustomerOptionValueService(customerOptionValueService);
        populator.setLanguageService(languageService);
        populator.setLanguageService(languageService);
        populator.setZoneService(zoneService);

        customerModel = populator.populate( customer, customerModel, store, language );
        log.info( "About to persist customer to database." );
        customerService.saveOrUpdate( customerModel );
        return customerModel;

	}

	private ShoppingCartData populateShoppingCartData(ShoppingCart cartModel, MerchantStore store,
			Language language) {
		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		try {
			return shoppingCartDataPopulator.populate(cartModel, store, language);
		} catch (ConversionException e) {
			log.error("Error in converting shopping cart", e);
		}
		return null;
	}
}
