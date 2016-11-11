package com.mainiway.cloudcut.common.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Component
public class CorsConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Value("${cross.allowedOrigins}")
	protected String origins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		if (origins != null && !origins.equals("")) {

			CorsRegistration cr = registry.addMapping("/**").allowCredentials(true);

			String[] hosts = origins.split(";");
			cr.allowedOrigins(hosts);
		}
	}
}
