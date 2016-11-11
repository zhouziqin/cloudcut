package com.mainiway.cloudcut.common.utils;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyUtils extends PropertyPlaceholderConfigurer {
	
	final static ConcurrentMap<String, String> props = new ConcurrentHashMap();

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties)throws BeansException {
		
		super.processProperties(beanFactory, properties);
		
		for (Object key : properties.keySet()) {
			props.put(key.toString(), properties.getProperty(key.toString()));  
		}
	} 
	
	public static String getContextProperty(String name) {  
		return props.get(name);
	}  

}
