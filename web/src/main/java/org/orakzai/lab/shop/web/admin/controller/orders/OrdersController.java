package org.orakzai.lab.shop.web.admin.controller.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.orakzai.lab.shop.domain.business.order.service.OrderService;
import org.orakzai.lab.shop.domain.business.system.service.ModuleConfigurationService;
import org.orakzai.lab.shop.domain.utils.ProductPriceUtils;
import org.orakzai.lab.shop.web.admin.controller.ControllerConstants;
import org.orakzai.lab.shop.web.utils.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrdersController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	LabelUtils messages;
	
	@Autowired
	private ProductPriceUtils priceUtil;
	
	@Autowired
	protected ModuleConfigurationService moduleConfigurationService;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);
	
	@PreAuthorize("hasRole('ORDER')")
	@RequestMapping(value="/admin/orders/list.html", method=RequestMethod.GET)
	public String displayOrders(Model model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		setMenu(model, request);
		
		return ControllerConstants.Templates.Order.orders;
	}

}
