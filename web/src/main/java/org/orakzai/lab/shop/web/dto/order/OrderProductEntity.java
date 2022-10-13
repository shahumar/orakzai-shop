package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;

import org.orakzai.lab.shop.web.dto.catalog.product.ReadableProduct;

import lombok.Data;


@Data
public class OrderProductEntity extends OrderProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	private int orderedQuantity;
	private ReadableProduct product;
}
