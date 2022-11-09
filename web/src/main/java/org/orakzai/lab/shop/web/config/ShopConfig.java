package org.orakzai.lab.shop.web.config;

import java.util.Locale;

import org.orakzai.lab.shop.web.filter.AdminFilter;
import org.orakzai.lab.shop.web.filter.ShopFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class ShopConfig implements WebMvcConfigurer {
	
	@Autowired
	private ShopFilter shopFilter;
	
	@Autowired
	private AdminFilter adminFilter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(shopFilter);
		registry.addInterceptor(adminFilter).addPathPatterns("/admin");
		
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource rbm = new ReloadableResourceBundleMessageSource();
		rbm.setBasename("classpath:/i18n/messages");
		rbm.setDefaultEncoding("UTF-8");
		return rbm;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.CANADA);
		return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("language");
		return lci;
	}
	
	
	
}
