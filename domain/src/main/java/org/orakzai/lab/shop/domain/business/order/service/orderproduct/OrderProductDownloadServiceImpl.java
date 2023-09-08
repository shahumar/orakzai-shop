package org.orakzai.lab.shop.domain.business.order.service.orderproduct;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.order.dao.orderproduct.OrderProductDownloadRepository;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("orderProductDownloadService")
public class OrderProductDownloadServiceImpl  extends SalesManagerEntityServiceImpl<Long, OrderProductDownload> implements OrderProductDownloadService {

    private final OrderProductDownloadRepository orderProductDownloadRepository;

    @Autowired
    public OrderProductDownloadServiceImpl(final OrderProductDownloadRepository orderProductDownloadDao) {
        super(orderProductDownloadDao);
        this.orderProductDownloadRepository = orderProductDownloadDao;
    }

    @Override
    public List<OrderProductDownload> getByOrderId(Long orderId) {
    	return orderProductDownloadRepository.getByOrderId(orderId);
    }


}
