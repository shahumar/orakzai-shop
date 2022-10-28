package org.orakzai.lab.shop.domain.business.order.service.orderproduct;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.orakzai.lab.shop.domain.business.customer.service.CustomerService;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.order.dao.orderproduct.OrderProductDownloadRepository;
import org.orakzai.lab.shop.domain.business.order.model.orderproduct.OrderProductDownload;
import org.orakzai.lab.shop.domain.business.payments.service.PaymentService;
import org.orakzai.lab.shop.domain.business.payments.service.TransactionService;
import org.orakzai.lab.shop.domain.business.shipping.service.ShippingService;
import org.orakzai.lab.shop.domain.business.tax.service.TaxService;
import org.orakzai.lab.shop.domain.modules.order.InvoiceModule;


@Slf4j
@Service("orderProductDownloadService")
public abstract class OrderProductDownloadServiceImpl  extends SalesManagerEntityServiceImpl<Long, OrderProductDownload> implements OrderProductDownloadService {


//    @Autowired
//    private InvoiceModule invoiceModule;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TaxService taxService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

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
