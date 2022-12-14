package org.orakzai.lab.shop.domain.business.order.service.orderproduct;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductDownload;

public interface OrderProductDownloadService extends SalesManagerEntityService<Long, OrderProductDownload> {

	List<OrderProductDownload> getByOrderId(Long orderId);

}
