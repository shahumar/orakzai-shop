package org.orakzai.lab.shop.domain.business.order.dao.orderproduct;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductDownload;
import org.springframework.stereotype.Repository;

@Repository("orderProductDownloadDao")
public interface OrderProductDownloadDao extends SalesManagerEntityDao<Long, OrderProductDownload> {

//	List<OrderProductDownload> getByOrderId(Long orderId);

}
