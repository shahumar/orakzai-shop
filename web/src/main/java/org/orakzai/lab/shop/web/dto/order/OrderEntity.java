package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.orakzai.lab.shop.domain.business.order.model.orderstatus.OrderStatus;
import org.orakzai.lab.shop.domain.business.order.model.payment.CreditCard;
import org.orakzai.lab.shop.domain.business.payments.model.PaymentType;

import lombok.Data;

@Data
public class OrderEntity extends Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<OrderTotal> totals;
	
	private PaymentType paymentType;
	private String paymentModule;
	private String shippingModule;
	private List<OrderStatus> previousOrderStatus;
	private OrderStatus orderStatus;
	private CreditCard creditCard;
	private LocalDate datePurchased;
	private String currency;
}
