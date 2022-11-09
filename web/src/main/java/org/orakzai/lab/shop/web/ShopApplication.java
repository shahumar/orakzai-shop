package org.orakzai.lab.shop.web;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.orakzai.lab.shop.domain.business.reference.init.service.InitializationDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.orakzai.lab.shop")
@ComponentScan(basePackages = "org.orakzai.lab.shop")
@EnableJpaRepositories(basePackages = "org.orakzai.lab.shop")
@EntityScan(basePackages = "org.orakzai.lab.shop")
@EnableWebSecurity
public class ShopApplication implements CommandLineRunner {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
    private ApplicationContext applicationContext;
	
//	@Autowired
//	private InitializationDatabase initService;
	
	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.print("Start here");
//		Arrays.asList(applicationContext.getBeanDefinitionNames())
//			.forEach(beanname -> System.out.println(beanname));
//		log.debug("End here");
//		System.out.print(em);
	}
}
