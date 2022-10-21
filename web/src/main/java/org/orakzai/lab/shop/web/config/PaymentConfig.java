package org.orakzai.lab.shop.web.config;

import java.util.HashMap;
import java.util.Map;

import org.orakzai.lab.shop.domain.modules.integration.payment.impl.BeanStreamPayment;
import org.orakzai.lab.shop.domain.modules.integration.payment.impl.MoneyOrderPayment;
import org.orakzai.lab.shop.domain.modules.integration.payment.impl.PayPalExpressCheckoutPayment;
import org.orakzai.lab.shop.domain.modules.integration.payment.model.PaymentModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
	
	
	@Bean
	public PaymentModule beanstream() {
		return new BeanStreamPayment();
	}
	
	@Bean
	public PaymentModule moneyorder() {
		return new MoneyOrderPayment();
	}
	
	@Bean
	public PaymentModule paypalEc() {
		return new PayPalExpressCheckoutPayment();
	}
	
	
	@Bean
	public Map<String, PaymentModule> paymentModules() {
		var modules = new HashMap<String, PaymentModule>();
		modules.put("beanstream", beanstream());
		modules.put("moneyorder", moneyorder());
		modules.put("paypal-express-checkout", paypalEc());
		return modules;
	}

}
