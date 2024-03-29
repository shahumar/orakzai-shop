package org.orakzai.lab.shop.domain.business.order.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.catalog.product.model.price.FinalPrice;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.dao.OrderRepository;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.order.model.OrderCriteria;
import org.orakzai.lab.shop.domain.business.order.model.OrderList;
import org.orakzai.lab.shop.domain.business.order.model.OrderSummary;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotal;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalSummary;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotalType;
import org.orakzai.lab.shop.domain.business.order.model.OrderValueType;
//import org.orakzai.lab.shop.domain.business.order.model.Order_;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatus;
import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatusHistory;
import org.orakzai.lab.shop.domain.business.payments.model.Payment;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
//import org.orakzai.lab.shop.domain.business.payments.service.PaymentService;
//import org.orakzai.lab.shop.domain.business.payments.service.TransactionService;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingConfiguration;
//import org.orakzai.lab.shop.domain.business.shipping.service.ShippingService;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCart;
import org.orakzai.lab.shop.domain.business.shoppingcart.model.ShoppingCartItem;
import org.orakzai.lab.shop.domain.business.tax.model.TaxItem;
//import org.orakzai.lab.shop.domain.business.tax.service.TaxService;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.orakzai.lab.shop.domain.modules.order.InvoiceModule;


@Service("orderService")
public class OrderServiceImpl  extends SalesManagerEntityServiceImpl<Long, Order> implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

//    @Autowired
//    private InvoiceModule invoiceModule;

//    @Autowired
//    private ShippingService shippingService;
//
//    @Autowired
//    private PaymentService paymentService;
//
//    @Autowired
//    private TaxService taxService;
//
//    @Autowired
//    private CustomerService customerService;
//
//    @Autowired
//    private TransactionService transactionService;

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(final OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrderStatusHistory(final Order order, final OrderStatusHistory history) throws ServiceException {
        order.getOrderHistory().add(history);
        history.setOrder(order);
        update(order);
    }

    @Override
    public Order processOrder(Order order, Customer customer, List<ShoppingCartItem> items, OrderTotalSummary summary, Payment payment, MerchantStore store) throws ServiceException {

    	return this.process(order, customer, items, summary, payment, null, store);
    }

    @Override
    public Order processOrder(Order order, Customer customer, List<ShoppingCartItem> items, OrderTotalSummary summary, Payment payment, Transaction transaction, MerchantStore store) throws ServiceException {

    	return this.process(order, customer, items, summary, payment, transaction, store);
    }

    private Order process(Order order, Customer customer, List<ShoppingCartItem> items, OrderTotalSummary summary, Payment payment, Transaction transaction, MerchantStore store) throws ServiceException {


    	Validate.notNull(order, "Order cannot be null");
    	Validate.notNull(customer, "Customer cannot be null (even if anonymous order)");
    	Validate.notEmpty(items, "ShoppingCart items cannot be null");
    	Validate.notNull(payment, "Payment cannot be null");
    	Validate.notNull(store, "MerchantStore cannot be null");
    	Validate.notNull(summary, "Order total Summary cannot be null");

    	//first process payment
//    	Transaction processTransaction = paymentService.processPayment(customer, store, payment, items, order);
//    	//transactionService.save(processTransaction);
//
//    	if(order.getOrderHistory()==null || order.getOrderHistory().size()==0 || order.getStatus()==null) {
//    		OrderStatus status = order.getStatus();
//    		if(status==null) {
//    			status = OrderStatus.ORDERED;
//    			order.setStatus(status);
//    		}
//    		Set<OrderStatusHistory> statusHistorySet = new HashSet<OrderStatusHistory>();
//    		OrderStatusHistory statusHistory = new OrderStatusHistory();
//    		statusHistory.setStatus(status);
//    		statusHistory.setDateAdded(LocalDate.now());
//    		statusHistory.setOrder(order);
//    		statusHistorySet.add(statusHistory);
//    		order.setOrderHistory(statusHistorySet);
//
//    	}
//
//    	if(customer.getId()==null || customer.getId()==0) {
//    		customerService.create(customer);
//    	}
//
//    	order.setCustomerId(customer.getId());
//
//    	this.create(order);
//
//    	if(transaction!=null) {
//    		transaction.setOrder(order);
//    		if(transaction.getId()==null || transaction.getId()==0) {
//    			transactionService.create(transaction);
//    		} else {
//    			transactionService.update(transaction);
//    		}
//    	}
//
//    	if(processTransaction!=null) {
//    		processTransaction.setOrder(order);
//    		if(processTransaction.getId()==null || processTransaction.getId()==0) {
//    			transactionService.create(processTransaction);
//    		} else {
//    			transactionService.update(processTransaction);
//    		}
//    	}

    	return order;


    }

    private OrderTotalSummary calculateOrder(final OrderSummary summary, final Customer customer, final MerchantStore store, final Language language) throws Exception {

        OrderTotalSummary totalSummary = new OrderTotalSummary();
        List<OrderTotal> orderTotals = new ArrayList<OrderTotal>();
        Map<String,OrderTotal> otherPricesTotals = new HashMap<String,OrderTotal>();

        ShippingConfiguration shippingConfiguration = null;

        BigDecimal grandTotal = new BigDecimal(0);
        var gtScale = grandTotal.setScale(2, RoundingMode.HALF_UP);

        //price by item
        /**
         * qty * price
         * subtotal
         */
        BigDecimal subTotal = new BigDecimal(0);
        var stScale = subTotal.setScale(2, RoundingMode.HALF_UP);
        for(ShoppingCartItem item : summary.getProducts()) {

            BigDecimal st = item.getItemPrice().multiply(new BigDecimal(item.getQuantity()));
            item.setSubTotal(st);
            subTotal = subTotal.add(st);
            //Other prices
            FinalPrice finalPrice = item.getFinalPrice();
            if(finalPrice!=null) {
                List<FinalPrice> otherPrices = finalPrice.getAdditionalPrices();
                if(otherPrices!=null) {
                    for(FinalPrice price : otherPrices) {
                        if(!price.isDefaultPrice()) {
                            OrderTotal itemSubTotal = otherPricesTotals.get(price.getProductPrice().getCode());

                            if(itemSubTotal==null) {
                                itemSubTotal = new OrderTotal();
                                itemSubTotal.setModule(Constants.OT_ITEM_PRICE_MODULE_CODE);
                                itemSubTotal.setText(Constants.OT_ITEM_PRICE_MODULE_CODE);
                                itemSubTotal.setTitle(Constants.OT_ITEM_PRICE_MODULE_CODE);
                                itemSubTotal.setOrderTotalCode(price.getProductPrice().getCode());
                                itemSubTotal.setOrderTotalType(OrderTotalType.PRODUCT);
                                itemSubTotal.setSortOrder(0);
                                otherPricesTotals.put(price.getProductPrice().getCode(), itemSubTotal);
                            }

                            BigDecimal orderTotalValue = itemSubTotal.getValue();
                            if(orderTotalValue==null) {
                                orderTotalValue = new BigDecimal(0);
                                var otv = orderTotalValue.setScale(2, RoundingMode.HALF_UP);
                            }

                            orderTotalValue = orderTotalValue.add(price.getFinalPrice());
                            itemSubTotal.setValue(orderTotalValue);
                            if(price.getProductPrice().getProductPriceType().name().equals(OrderValueType.ONE_TIME)) {
                                subTotal = subTotal.add(price.getFinalPrice());
                            }
                        }
                    }
                }
            }

        }


        totalSummary.setSubTotal(subTotal);
        grandTotal=grandTotal.add(subTotal);

        OrderTotal orderTotalSubTotal = new OrderTotal();
        orderTotalSubTotal.setModule(Constants.OT_SUBTOTAL_MODULE_CODE);
        orderTotalSubTotal.setOrderTotalType(OrderTotalType.SUBTOTAL);
        orderTotalSubTotal.setOrderTotalCode("order.total.subtotal");
        orderTotalSubTotal.setTitle(Constants.OT_SUBTOTAL_MODULE_CODE);
        orderTotalSubTotal.setText("order.total.subtotal");
        orderTotalSubTotal.setSortOrder(5);
        orderTotalSubTotal.setValue(subTotal);

        //TODO autowire a list of post processing modules for price calculation - drools, custom modules
        //may affect the sub total

        orderTotals.add(orderTotalSubTotal);


        //shipping
        if(summary.getShippingSummary()!=null) {


	            OrderTotal shippingSubTotal = new OrderTotal();
	            shippingSubTotal.setModule(Constants.OT_SHIPPING_MODULE_CODE);
	            shippingSubTotal.setOrderTotalType(OrderTotalType.SHIPPING);
	            shippingSubTotal.setOrderTotalCode("order.total.shipping");
	            shippingSubTotal.setTitle(Constants.OT_SHIPPING_MODULE_CODE);
	            shippingSubTotal.setText("order.total.shipping");
	            shippingSubTotal.setSortOrder(10);

	            orderTotals.add(shippingSubTotal);

            if(!summary.getShippingSummary().isFreeShipping()) {
                shippingSubTotal.setValue(summary.getShippingSummary().getShipping());
                grandTotal=grandTotal.add(summary.getShippingSummary().getShipping());
            } else {
                shippingSubTotal.setValue(new BigDecimal(0));
                grandTotal=grandTotal.add(new BigDecimal(0));
            }

            //check handling fees
//            shippingConfiguration = shippingService.getShippingConfiguration(store);
//            if(summary.getShippingSummary().getHandling()!=null && summary.getShippingSummary().getHandling().doubleValue()>0) {
//                if(shippingConfiguration.getHandlingFees()!=null && shippingConfiguration.getHandlingFees().doubleValue()>0) {
//                    OrderTotal handlingubTotal = new OrderTotal();
//                    handlingubTotal.setModule(Constants.OT_HANDLING_MODULE_CODE);
//                    handlingubTotal.setOrderTotalType(OrderTotalType.HANDLING);
//                    handlingubTotal.setOrderTotalCode("order.total.handling");
//                    handlingubTotal.setTitle(Constants.OT_HANDLING_MODULE_CODE);
//                    handlingubTotal.setText("order.total.handling");
//                    handlingubTotal.setSortOrder(12);
//                    handlingubTotal.setValue(summary.getShippingSummary().getHandling());
//                    orderTotals.add(handlingubTotal);
//                    grandTotal=grandTotal.add(summary.getShippingSummary().getHandling());
//                }
//            }
        }

        //tax
//        List<TaxItem> taxes = taxService.calculateTax(summary, customer, store, language);
//        if(taxes!=null && taxes.size()>0) {
//        	BigDecimal totalTaxes = new BigDecimal(0);
//        	totalTaxes.setScale(2, RoundingMode.HALF_UP);
//            int taxCount = 20;
//            for(TaxItem tax : taxes) {
//
//                OrderTotal taxLine = new OrderTotal();
//                taxLine.setModule(Constants.OT_TAX_MODULE_CODE);
//                taxLine.setOrderTotalType(OrderTotalType.TAX);
//                taxLine.setOrderTotalCode(tax.getLabel());
//                taxLine.setSortOrder(taxCount);
//                taxLine.setTitle(Constants.OT_TAX_MODULE_CODE);
//                taxLine.setText(tax.getLabel());
//                taxLine.setValue(tax.getItemPrice());
//
//                totalTaxes = totalTaxes.add(tax.getItemPrice());
//                orderTotals.add(taxLine);
//                //grandTotal=grandTotal.add(tax.getItemPrice());
//
//                taxCount ++;
//
//            }
//            grandTotal = grandTotal.add(totalTaxes);
//            totalSummary.setTaxTotal(totalTaxes);
//        }

        // grand total
        OrderTotal orderTotal = new OrderTotal();
        orderTotal.setModule(Constants.OT_TOTAL_MODULE_CODE);
        orderTotal.setOrderTotalType(OrderTotalType.TOTAL);
        orderTotal.setOrderTotalCode("order.total.total");
        orderTotal.setTitle(Constants.OT_TOTAL_MODULE_CODE);
        orderTotal.setText("order.total.total");
        orderTotal.setSortOrder(300);
        orderTotal.setValue(grandTotal);
        orderTotals.add(orderTotal);

        totalSummary.setTotal(grandTotal);
        totalSummary.setTotals(orderTotals);
        return totalSummary;

    }


    @Override
    public OrderTotalSummary calculateOrderTotal(final OrderSummary orderSummary, final Customer customer, final MerchantStore store, final Language language) throws ServiceException {
        Validate.notNull(orderSummary,"Order summary cannot be null");
        Validate.notNull(orderSummary.getProducts(),"Order summary.products cannot be null");
        Validate.notNull(store,"MerchantStore cannot be null");
        Validate.notNull(customer,"Customer cannot be null");

        try {
            return calculateOrder(orderSummary, customer, store, language);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }



    @Override
    public OrderTotalSummary calculateOrderTotal(final OrderSummary orderSummary, final MerchantStore store, final Language language) throws ServiceException {
        Validate.notNull(orderSummary,"Order summary cannot be null");
        Validate.notNull(orderSummary.getProducts(),"Order summary.products cannot be null");
        Validate.notNull(store,"MerchantStore cannot be null");

        try {
            return calculateOrder(orderSummary, null, store, language);
        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

    private OrderTotalSummary caculateShoppingCart( final ShoppingCart shoppingCart, final Customer customer, final MerchantStore store, final Language language) throws Exception {





    	OrderSummary orderSummary = new OrderSummary();

    	List<ShoppingCartItem> itemsSet = new ArrayList<ShoppingCartItem>(shoppingCart.getLineItems());
    	orderSummary.setProducts(itemsSet);


    	return this.calculateOrder(orderSummary, customer, store, language);

    }

    @Override
    public OrderTotalSummary calculateShoppingCartTotal(
                                                        final ShoppingCart shoppingCart, final Customer customer, final MerchantStore store,
                                                        final Language language) throws ServiceException {
        Validate.notNull(shoppingCart,"Order summary cannot be null");
        Validate.notNull(customer,"Customery cannot be null");
        Validate.notNull(store,"MerchantStore cannot be null.");
        try {
            return caculateShoppingCart(shoppingCart, customer, store, language);
        } catch (Exception e) {
            LOGGER.error( "Error while calculating shopping cart total" +e );
            throw new ServiceException(e);
        }

    }


    @Override
    public OrderTotalSummary calculateShoppingCartTotal(
                                                        final ShoppingCart shoppingCart, final MerchantStore store, final Language language)
                                                                        throws ServiceException {
        Validate.notNull(shoppingCart,"Order summary cannot be null");
        Validate.notNull(store,"MerchantStore cannot be null");

        try {
            return caculateShoppingCart(shoppingCart, null, store, language);
        } catch (Exception e) {
            LOGGER.error( "Error while calculating shopping cart total" +e );
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(final Order order) throws ServiceException {


        orderRepository.delete(order);
    }


    @Override
    public ByteArrayOutputStream generateInvoice(final MerchantStore store, final Order order, final Language language) throws ServiceException {

        Validate.notNull(order.getOrderProducts(),"Order products cannot be null");
        Validate.notNull(order.getOrderTotal(),"Order totals cannot be null");

        try {
//            ByteArrayOutputStream stream = invoiceModule.createInvoice(store, order, language);
//            return stream;
            return null;
        } catch(Exception e) {
            throw new ServiceException(e);
        }



    }

    @Override
    public Order getOrder(final Long orderId ) {
        return getById(orderId);
    }



    @Override
    public List<Order> listByStore(final MerchantStore merchantStore) {
//        return listByField(Order_.merchant, merchantStore);
        return null;
    }

    @Override
    public OrderList listByStore(final MerchantStore store, final OrderCriteria criteria) {

//        return orderRepository.listByStore(store, criteria);
    	return null;
    }


    @Override
    public void saveOrUpdate(final Order order) throws ServiceException {

        if(order.getId()!=null && order.getId()>0) {
            LOGGER.debug("Updating Order");
            super.update(order);

        } else {
            LOGGER.debug("Creating Order");
            super.create(order);

        }
    }

	@Override
	public boolean hasDownloadFiles(Order order) throws ServiceException {

		Validate.notNull(order,"Order cannot be null");
		Validate.notNull(order.getOrderProducts(),"Order products cannot be null");
		Validate.notEmpty(order.getOrderProducts(),"Order products cannot be empty");

		boolean hasDownloads = false;
		for(OrderProduct orderProduct : order.getOrderProducts()) {

			if(CollectionUtils.isNotEmpty(orderProduct.getDownloads())) {
				hasDownloads = true;
				break;
			}
		}

		return hasDownloads;
	}



}
