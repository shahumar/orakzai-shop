package org.orakzai.lab.shop.domain.business.order.dao.orderproduct;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductDownload;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("orderProductDownloadRepository")
public interface OrderProductDownloadRepository extends SalesManagerEntityDao<Long, OrderProductDownload> {

	@Query("select pd from OrderProductDownload pd left join fetch pd.orderProduct op "
			+ "left join fetch op.order o left join fetch op.merchant where pd.id=:orderId")
	List<OrderProductDownload> getByOrderId(Long orderId);

}
