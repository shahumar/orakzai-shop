package org.orakzai.lab.shop.domain.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class CoreConfiguration {
	

	public Properties properties = new Properties();
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public CoreConfiguration() throws IOException {
		InputStream in = getClass().getClassLoader().getResourceAsStream("shopizer-properties.properties");
		properties.load(in);
	}
	
	public String getProperty(String propertyKey) {
		
		return properties.getProperty(propertyKey);
		
		
	}
	
	public String getProperty(String propertyKey, String defaultValue) {
		
		String prop = defaultValue;
		try {
			prop = properties.getProperty(propertyKey);
		} catch(Exception e) {
			log.warn("Cannot find property " + propertyKey);
		}
		return prop;
		
		
	}

}
