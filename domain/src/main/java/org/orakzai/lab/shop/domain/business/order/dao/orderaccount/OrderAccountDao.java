package org.orakzai.lab.shop.domain.business.order.dao.orderaccount;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.order.model.orderaccount.OrderAccount;
import org.springframework.stereotype.Repository;

@Repository("orderAccountDao")
public interface OrderAccountDao  extends SalesManagerEntityDao<Long, OrderAccount > {

}

