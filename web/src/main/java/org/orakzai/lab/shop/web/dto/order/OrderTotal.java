package org.orakzai.lab.shop.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

import org.orakzai.lab.shop.web.dto.Entity;

import lombok.Data;

@Data
public class OrderTotal extends Entity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String title;
	private String code;
	private int order;
	private String module;
	private BigDecimal value;

}
