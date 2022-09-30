package org.orakzai.lab.shop.domain.business.order.dao;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.order.model.OrderCriteria;
import org.orakzai.lab.shop.domain.business.order.model.OrderList;
import org.springframework.stereotype.Repository;

@Repository("orderDao")
public interface OrderRepository extends SalesManagerEntityDao<Long, Order> {
	
//	OrderList listByStore(MerchantStore store, OrderCriteria criteria);

}