package org.orakzai.lab.shop.domain.business.order.dao;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.order.model.OrderTotal;
import org.springframework.stereotype.Repository;

@Repository("orderTotalDao")
public interface OrderTotalDao extends SalesManagerEntityDao<Long, OrderTotal > {

}
