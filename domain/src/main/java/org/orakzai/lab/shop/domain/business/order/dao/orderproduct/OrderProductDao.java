package org.orakzai.lab.shop.domain.business.order.dao.orderproduct;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProduct;
import org.springframework.stereotype.Repository;

@Repository("orderProductDao")
public interface OrderProductDao extends SalesManagerEntityDao<Long, OrderProduct> {

}
