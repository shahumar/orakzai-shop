package org.orakzai.lab.shop.domain.business.shoppingcart.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.FinalPrice;
import org.orakzai.lab.shop.domain.business.catalog.product.service.PricingService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.catalog.product.service.attribute.ProductAttributeService;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingProduct;
import org.orakzai.lab.shop.domain.business.shoppingcart.dao.ShoppingCartRepository;
import org.orakzai.lab.shop.domain.business.shoppingcart.dao.ShoppingCartItemRepository;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartAttributeItem;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;

@Slf4j
@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends SalesManagerEntityServiceImpl<Long, ShoppingCart> implements ShoppingCartService {


	private final ShoppingCartRepository shoppingCartDao;

	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartItemRepository shoppingCartItemDao;

	@Autowired
	private PricingService pricingService;

	@Autowired
    private ProductAttributeService productAttributeService;


	@Autowired
	public ShoppingCartServiceImpl(
			final ShoppingCartRepository shoppingCartDao) {
		super(shoppingCartDao);
		this.shoppingCartDao = shoppingCartDao;

	}


	/**
	 * Retrieve a {@link ShoppingCart} cart for a given customer
	 */
	@Override
    @Transactional
	public ShoppingCart getShoppingCart(final Customer customer) throws ServiceException {

		try {
			Optional<ShoppingCart> shoppingCart = shoppingCartDao.findByCustomerId(customer.getId());
			if (shoppingCart.isPresent()) {
				ShoppingCart cart = shoppingCart.get();
				populateShoppingCart(cart);
				if (cart.isObsolete()) {
					delete(cart);
					return null;
				}
				return cart;
			}
			return null;
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * Save or update a {@link ShoppingCart} for a given customer
	 */
	@Override
    public void saveOrUpdate(final ShoppingCart shoppingCart) throws ServiceException {
		if(shoppingCart.getId()==null || shoppingCart.getId().longValue()==0) {
			super.create(shoppingCart);
		} else {
			super.update(shoppingCart);
		}
	}

	/**
	 * Get a {@link ShoppingCart} for a given id and MerchantStore. Will update the shopping cart
	 * prices and items based on the actual inventory. This method will remove the shopping cart if
	 * no items are attached.
	 */
	@Override
	@Transactional
	public ShoppingCart getById(final Long id, final MerchantStore store) throws ServiceException {

		try {
			ShoppingCart shoppingCart = shoppingCartDao.findByIdAndMerchantStore(id, store).orElse(null);
			if(shoppingCart==null) {
				return null;
			}
			populateShoppingCart(shoppingCart);

			if(shoppingCart.isObsolete()) {
				delete(shoppingCart);
				return null;
			} else {
				return shoppingCart;
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * Get a {@link ShoppingCart} for a given id. Will update the shopping cart
	 * prices and items based on the actual inventory. This method will remove the shopping cart if
	 * no items are attached.
	 */
	@Override
	public ShoppingCart getById(final Long id) {

//
//		try {
//			ShoppingCart shoppingCart = shoppingCartDao.getById(id);
//			if(shoppingCart==null) {
//				return null;
//			}
//			populateShoppingCart(shoppingCart);
//
//
//			if(shoppingCart.isObsolete()) {
//				delete(shoppingCart);
//				return null;
//			} else {
//				return shoppingCart;
//			}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return null;

	}

	/**
	 * Get a {@link ShoppingCart} for a given code. Will update the shopping cart
	 * prices and items based on the actual inventory. This method will remove the shopping cart if
	 * no items are attached.
	 */
	@Override
	@Transactional
	public ShoppingCart getByCode(final String code, final MerchantStore store) throws ServiceException {

		try {
			ShoppingCart shoppingCart = shoppingCartDao.findByShoppingCartCodeAndMerchantStore(code, store).get();
			if(shoppingCart==null) {
				return null;
			}
			populateShoppingCart(shoppingCart);

			if(shoppingCart.isObsolete()) {
				delete(shoppingCart);
				return null;
			} else {
				return shoppingCart;
			}

		}catch(javax.persistence.NoResultException nre) {
			return null;
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		} catch (Exception ee) {
			throw new ServiceException(ee);
		} catch (Throwable t) {
			throw new ServiceException(t);
		}



	}

	@Override
	public void deleteCart(final ShoppingCart shoppingCart) throws ServiceException {
		ShoppingCart cart = this.getById(shoppingCart.getId());
		if(cart!=null) {
			super.delete(cart);
		}
	}


	@Override
	public ShoppingCart getByCustomer(final Customer customer) throws ServiceException {

		try {
			Optional<ShoppingCart> shoppingCart = shoppingCartDao.findByCustomerId(customer.getId());
			
			if(!shoppingCart.isPresent()) {
				return null;
			}
			return populateShoppingCart(shoppingCart.get());

		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Transactional(noRollbackFor={org.springframework.dao.EmptyResultDataAccessException.class})
	private ShoppingCart populateShoppingCart(final ShoppingCart shoppingCart) throws Exception {

		try {
			boolean cartIsObsolete = true;
			if(shoppingCart!=null) {

				Set<ShoppingCartItem> items = shoppingCart.getLineItems();
				if(items==null || items.size()==0) {
					shoppingCart.setObsolete(true);
					return shoppingCart;

				}

				//Set<ShoppingCartItem> shoppingCartItems = new HashSet<ShoppingCartItem>();
				for(ShoppingCartItem item : items) {
					log.debug("Populate item " + item.getId());
					populateItem(item);
					log.debug("Obsolete item ? " + item.isObsolete());
					if(item.isObsolete()) {
					} else {
						cartIsObsolete = false;
					}
				}

				//shoppingCart.setLineItems(shoppingCartItems);
				boolean refreshCart = false;
                Set<ShoppingCartItem> refreshedItems = new HashSet<ShoppingCartItem>();
                for(ShoppingCartItem item : items) {
                	if(!item.isObsolete()) {
                		refreshedItems.add(item);
                	} else {
                		refreshCart = true;
                	}
                }

                if(refreshCart) {
                	shoppingCart.setLineItems(refreshedItems);
                	update(shoppingCart);
                }

				if(cartIsObsolete) {
					shoppingCart.setObsolete(true);
				}
				return shoppingCart;
			}

		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return shoppingCart;


	}

	@Override
	public ShoppingCartItem populateShoppingCartItem(final Product product) throws ServiceException {
		Validate.notNull(product, "Product should not be null");
		Validate.notNull(product.getMerchantStore(), "Product.merchantStore should not be null");

		ShoppingCartItem item = new ShoppingCartItem(product);

		item.setProductVirtual(product.isProductVirtual());

		//set item price
		FinalPrice price = pricingService.calculateProductPrice(product);
		item.setItemPrice(price.getFinalPrice());
		return item;


	}


	private void populateItem(final ShoppingCartItem item) throws Exception {

		Product product = null;

		Long productId = item.getProductId();
		product = productService.getById(productId);

		if(product==null) {
			item.setObsolete(true);
			return;
		}


		item.setProduct(product);

		if(product.isProductVirtual()) {
			item.setProductVirtual(true);
		}

		Set<ShoppingCartAttributeItem> attributes = item.getAttributes();
		Set<ProductAttribute> productAttributes = product.getAttributes();
		List<ProductAttribute> attributesList = new ArrayList<ProductAttribute>();
		if(productAttributes!=null && productAttributes.size()>0 && attributes!=null && attributes.size()>0) {
			for(ShoppingCartAttributeItem attribute : attributes) {
				long attributeId = attribute.getProductAttributeId().longValue();
				for(ProductAttribute productAttribute : productAttributes) {

					if(productAttribute.getId().longValue()==attributeId) {
						attribute.setProductAttribute(productAttribute);
						attributesList.add(productAttribute);
						break;
					}

				}

			}
		}

		//set item price
		FinalPrice price = pricingService.calculateProductPrice(product, attributesList);
		item.setItemPrice(price.getFinalPrice());
		item.setFinalPrice(price);



		BigDecimal subTotal = item.getItemPrice().multiply(new BigDecimal(item.getQuantity().intValue()));
		item.setSubTotal(subTotal);


	}

	@Override
	public List<ShippingProduct> createShippingProduct(final ShoppingCart cart) throws ServiceException {
		/**
		 * Determines if products are virtual
		 */
		Set<ShoppingCartItem> items = cart.getLineItems();
		List<ShippingProduct> shippingProducts = null;
		for(ShoppingCartItem item : items) {
			Product product = item.getProduct();
			if(!product.isProductVirtual() && product.isProductShipeable()) {
				if(shippingProducts==null) {
					shippingProducts = new ArrayList<ShippingProduct>();
				}
				ShippingProduct shippingProduct = new ShippingProduct(product);
				shippingProduct.setQuantity(item.getQuantity());
				shippingProducts.add(shippingProduct);
			}
		}

		return shippingProducts;

	}


	@Override
	public boolean isFreeShoppingCart(final ShoppingCart cart) throws ServiceException {
		/**
		 * Determines if products are free
		 */
		Set<ShoppingCartItem> items = cart.getLineItems();
		for(ShoppingCartItem item : items) {
			Product product = item.getProduct();
			FinalPrice finalPrice = pricingService.calculateProductPrice(product);
			if(finalPrice.getFinalPrice().longValue()>0) {
				return false;
			}
		}

		return true;

	}

	@Override
	public boolean requiresShipping(final ShoppingCart cart) throws ServiceException {

		Validate.notNull(cart,"Shopping cart cannot be null");
		Validate.notNull(cart.getLineItems(),"ShoppingCart items cannot be null");
		boolean requiresShipping = false;
		for(ShoppingCartItem item : cart.getLineItems()) {
			Product product = item.getProduct();
			if(product.isProductShipeable()) {
				requiresShipping = true;
				break;
			}
		}

		return requiresShipping;

	}

    @Override
    public void  removeShoppingCart( final ShoppingCart cart )
        throws ServiceException
    {
//         shoppingCartDao.removeShoppingCart( cart );
    }


    @Override
    public ShoppingCart mergeShoppingCarts( final ShoppingCart userShoppingModel, final ShoppingCart sessionCart,final MerchantStore store ) throws Exception
    {
        if(sessionCart.getCustomerId() !=null && sessionCart.getCustomerId() ==userShoppingModel.getCustomerId() ){
            log.info( "Session Shopping cart belongs to same logged in user" );
            if(CollectionUtils.isNotEmpty( userShoppingModel.getLineItems() ) && CollectionUtils.isNotEmpty( sessionCart.getLineItems() )){
                return userShoppingModel;
            }
        }

        log.info( "Starting merging shopping carts" );
        if(CollectionUtils.isNotEmpty( sessionCart.getLineItems() )){
            Set<ShoppingCartItem> shoppingCartItemsSet=getShoppingCartItems(sessionCart,store,userShoppingModel);
            boolean duplicateFound = false;
            if(CollectionUtils.isNotEmpty(shoppingCartItemsSet)) {
                for(ShoppingCartItem sessionShoppingCartItem : shoppingCartItemsSet){
                    if(CollectionUtils.isNotEmpty(userShoppingModel.getLineItems())){
                        for(ShoppingCartItem cartItem : userShoppingModel.getLineItems()){
                            if(cartItem.getProduct().getId().longValue()==sessionShoppingCartItem.getProduct().getId().longValue()) {
                                if(CollectionUtils.isNotEmpty(cartItem.getAttributes())) {
                                    if(!duplicateFound) {
                                        log.info( "Dupliate item found..updating exisitng product quantity" );
                                        cartItem.setQuantity(cartItem.getQuantity() + sessionShoppingCartItem.getQuantity());
                                        duplicateFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(!duplicateFound) {
                        log.info( "New item found..adding item to Shopping cart" );
                        userShoppingModel.getLineItems().add( sessionShoppingCartItem );
                    }
                }

            }

        }
        log.info( "Shopping Cart merged successfully.....");
        saveOrUpdate( userShoppingModel );
        removeShoppingCart( sessionCart );

        return userShoppingModel;
    }



    private Set<ShoppingCartItem> getShoppingCartItems( final ShoppingCart sessionCart,final MerchantStore store,final ShoppingCart cartModel )
                    throws Exception
                {

                    Set<ShoppingCartItem> shoppingCartItemsSet=null;
                    if(CollectionUtils.isNotEmpty( sessionCart.getLineItems() )){
                        shoppingCartItemsSet=new HashSet<ShoppingCartItem>();
                        for(ShoppingCartItem shoppingCartItem : sessionCart.getLineItems()){
                            Product product = productService.getById( shoppingCartItem.getProductId() );
                            if ( product == null )
                            {
                                throw new Exception( "Item with id " + shoppingCartItem.getProductId() + " does not exist" );
                            }

                            if ( product.getMerchantStore().getId().intValue() != store.getId().intValue() )
                            {
                                throw new Exception( "Item with id " + shoppingCartItem.getProductId() + " does not belong to merchant "
                                    + store.getId() );
                            }

                            ShoppingCartItem item= populateShoppingCartItem( product );
                            item.setQuantity( shoppingCartItem.getQuantity() );
                            item.setShoppingCart( cartModel );

                            List<ShoppingCartAttributeItem> cartAttributes = new ArrayList<ShoppingCartAttributeItem>( shoppingCartItem.getAttributes() );
                            if(CollectionUtils.isNotEmpty( cartAttributes )){
                                for(ShoppingCartAttributeItem shoppingCartAttributeItem :cartAttributes ){
                                    ProductAttribute productAttribute =productAttributeService.getById( shoppingCartAttributeItem.getId() );
                                    if ( productAttribute != null
                                                    && productAttribute.getProduct().getId().longValue() == product.getId().longValue() ){

                                        ShoppingCartAttributeItem attributeItem=new ShoppingCartAttributeItem(item,productAttribute);
                                        if ( shoppingCartAttributeItem.getId() > 0 )
                                        {
                                            attributeItem.setId( shoppingCartAttributeItem.getId() );
                                        }
//                                        item.addAttributes( attributeItem );

                                    }
                                }
                            }

                            shoppingCartItemsSet.add( item );
                        }

                    }
                     return shoppingCartItemsSet;
              }


	@Override
	public boolean isFreeShoppingCart(List<ShoppingCartItem> items)
			throws ServiceException {
		ShoppingCart cart = new ShoppingCart();
		Set<ShoppingCartItem> cartItems = new HashSet<ShoppingCartItem>(items);
		cart.setLineItems(cartItems);
		return this.isFreeShoppingCart(cart);
	}


}
