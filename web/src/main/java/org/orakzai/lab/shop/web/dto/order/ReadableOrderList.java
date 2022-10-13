package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;
import java.util.List;

import org.orakzai.lab.shop.web.dto.ServiceEntity;

import lombok.Data;

@Data
public class ReadableOrderList extends ServiceEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int total;
	private List<ReadableOrder> orders;
}
