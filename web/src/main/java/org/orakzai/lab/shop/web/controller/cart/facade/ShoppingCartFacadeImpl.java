package org.orakzai.lab.shop.web.controller.cart.facade;

import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jgroups.util.UUID;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.attribute.ProductAttributeService;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartAttributeItem;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartCalculationService;
import org.orakzai.lab.shop.domain.business.shoppingcart.service.ShoppingCartService;
import org.orakzai.lab.shop.web.constants.Constants;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartAttribute;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartData;
import org.orakzai.lab.shop.web.dto.cart.ShoppingCartItem;
import org.orakzai.lab.shop.web.mapper.cart.ShoppingCartDataPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("shoppingCartFacade")
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ShoppingCartCalculationService shoppingCartCalculationService;

	@Autowired
	private PricingService pricingService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductAttributeService productAttributeService;

	@Override
	public ShoppingCartData addItemsToShoppingCart(ShoppingCartData shoppingCart, ShoppingCartItem item,
			MerchantStore store, Language language, Customer customer) throws Exception {
		ShoppingCart cartModel = null;
		if (!StringUtils.isBlank(item.getCode())) {
			cartModel = getShoppingCartModel(item.getCode(), store);
			if (cartModel == null) {
				cartModel = createCartModel(shoppingCart.getCode(), store, customer);
			}
		}
		if (cartModel == null) {
			final String shoppingCartCode = StringUtils.isNotBlank(shoppingCart.getCode()) ? shoppingCart.getCode()
					: null;
			cartModel = createCartModel(shoppingCartCode, store, customer);
		}
		org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem shoppingCartItem = createCartItem(
				cartModel, item, store);
		boolean duplicateFound = false;
		if (CollectionUtils.isEmpty(item.getShoppingCartAttributes())) {
			Set<org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem> cartModelItems = cartModel
					.getLineItems();
			for (org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem cartItem : cartModelItems) {
				if (cartItem.getProduct().getId().longValue() == shoppingCartItem.getProduct().getId().longValue()) {
					if (CollectionUtils.isEmpty(cartItem.getAttributes())) {
						if (!duplicateFound) {
							if (!shoppingCartItem.isProductVirtual()) {
								cartItem.setQuantity(cartItem.getQuantity() + shoppingCartItem.getQuantity());
							}
							duplicateFound = true;
							break;
						}
					}
				}

			}
		}

		if (!duplicateFound) {
			cartModel.getLineItems().add(shoppingCartItem);
		}

		shoppingCartService.saveOrUpdate(cartModel);
		cartModel = shoppingCartService.getById(cartModel.getId(), store);
		shoppingCartCalculationService.calculate(cartModel, store, language);

		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		return shoppingCartDataPopulator.populate(cartModel, store, language);
	}

	private org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem createCartItem(
			ShoppingCart cartModel, ShoppingCartItem item, MerchantStore store) throws Exception {
		Product product = productService.getById(item.getProductId());
		if (product == null) {
			throw new Exception("Item with id " + item.getProductId() + " does not exist");
		}
		if (product.getMerchantStore().getId().intValue() != store.getId().intValue()) {
			throw new Exception("Item with id " + item.getProductId() + " does not belong to merchant" + store.getId());
		}
		var newItem = shoppingCartService.populateShoppingCartItem(product);
		newItem.setQuantity(item.getQuantity());
		newItem.setShoppingCart(cartModel);

		var cartAttributes = item.getShoppingCartAttributes();
		if (!CollectionUtils.isEmpty(cartAttributes)) {
			for (ShoppingCartAttribute attr : cartAttributes) {
				ProductAttribute pAttr = productAttributeService.getById(attr.getAttributeId());
				if (pAttr != null && pAttr.getProduct().getId().longValue() == product.getId().longValue()) {
					ShoppingCartAttributeItem aItem = new ShoppingCartAttributeItem(newItem, pAttr);
					newItem.addAttributes(aItem);
				}

			}
		}
		return newItem;
	}

	@Override
	public ShoppingCart createCartModel(String shoppingCartCode, MerchantStore store, Customer customer)
			throws Exception {
		final Long customerId = customer != null ? customer.getId() : null;
		ShoppingCart cart = new ShoppingCart();
		if (StringUtils.isNotBlank(shoppingCartCode)) {
			cart.setShoppingCartCode(shoppingCartCode);
		} else {
			cart.setShoppingCartCode(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		cart.setMerchantStore(store);
		if (customerId != null) {
			cart.setCustomerId(customerId);
		}
		shoppingCartService.create(cart);

		return cart;
	}

	@Override
	public ShoppingCartData getShoppingCartData(Customer customer, MerchantStore store, String shoppingCartId)
			throws Exception {
		ShoppingCart cart = null;
		try {
			if (customer != null) {
				log.info("Reteriving customer shopping cart");
				cart = shoppingCartService.getShoppingCart(customer);
			} else {
				if (StringUtils.isNotBlank(shoppingCartId) && cart == null) {
					cart = shoppingCartService.getByCode(shoppingCartId, store);
				}
			}
		} catch (ServiceException e) {
			log.error("Error while reteriving  cart from customer", e);
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		if (cart == null) {
			return null;
		}
		log.info("Cart model found.");
		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		Language language = (Language) getkeyValue(Constants.LANGUAGE);
		MerchantStore merchantStore = (MerchantStore) getkeyValue(Constants.MERCHANT_STORE);
		return shoppingCartDataPopulator.populate(cart, merchantStore, language);
	}

	private Object getkeyValue(String key) {
		ServletRequestAttributes reqAttr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return reqAttr.getRequest().getAttribute(key);
	}

	@Override
	public ShoppingCartData getShoppingCartData(ShoppingCart shoppingCart) throws Exception {
		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		Language language = (Language) getKeyValue(Constants.LANGUAGE);
		MerchantStore store = (MerchantStore) getKeyValue(Constants.MERCHANT_STORE);
		
		return shoppingCartDataPopulator.populate(shoppingCart, store, language);
	}

	private Object getKeyValue(String key) {
		ServletRequestAttributes reqAttrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return reqAttrs.getRequest().getAttribute(key);
	}

	@Override
	public ShoppingCartData getShoppingCartData(String code, MerchantStore store) throws Exception {
		try {
			ShoppingCart shoppingCart = shoppingCartService.getByCode(code, store);
			if (shoppingCart != null) {
				ShoppingCartData cartData = getShoppingCartData(shoppingCart);
				return cartData;
			}
		} catch (NoResultException e) {
			// TODO: handle exception
		} catch (Exception e) {
			log.error("Cannot retrieve cart code", e);
		}
		return null;
	}

	@Override
	public ShoppingCartData removeCartItem(Long itemID, String cartId, MerchantStore store, Language language)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData updateCartItem(Long itemID, String cartId, long quantity, MerchantStore store,
			Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(Long id, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ShoppingCartData updateCartItems(List<ShoppingCartItem> shoppingCartItems, MerchantStore store,
			Language language) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart getShoppingCartModel(String shoppingCartCode, MerchantStore store) throws Exception {
		return shoppingCartService.getByCode(shoppingCartCode, store);
	}

	@Override
	public ShoppingCart getShoppingCartModel(Customer customer, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(String code, MerchantStore store) throws Exception {
		ShoppingCart cart = shoppingCartService.getByCode(code, store);
		if (cart != null) 
			shoppingCartService.delete(cart);

	}

}
