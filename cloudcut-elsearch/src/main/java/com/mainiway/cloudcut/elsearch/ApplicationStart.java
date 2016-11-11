package com.mainiway.cloudcut.elsearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mainiway.cloudcut.common.redis.RedisService;

@Configuration //配置控制
@ComponentScan(basePackages={"com.redis","com.beans","com.es"}) //组件扫描
@RestController
@EnableAutoConfiguration //启用自动配置
public class ApplicationStart extends WebMvcConfigurerAdapter {


	@Autowired
	RedisService redisCacheService;

	//加载配置文件
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/conf/applicationContext.xml"};

	public static void main(String[] args) {

		SpringApplication.run(CLASSPATH_RESOURCE_LOCATIONS, args);

	}

	/**
	 * 配置拦截器 ，设置配置参数
	 * @author lance
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		super.addInterceptors(registry);

	}
}
