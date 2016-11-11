package com.mainiway.cloudcut.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mainiway.cloudcut.common.redis.RedisService;


@Configuration //配置控制
@ComponentScan(basePackages={"com.mainiway.cloudcut.dao","com.project.cloudcut.db.model","com.mainiway.cloudcut.common","com.mainiway.cloudcut.operate"}) //组件扫描
@RestController
@EnableAutoConfiguration //启用自动配置
@ImportResource("classpath:/conf/applicationContext.xml")
public class ApplicationStart extends WebMvcConfigurerAdapter {

	@Autowired
	RedisService redisCacheService;
 	@Value("${pathpatterns}")
	protected String pathPatterns;

	public static void main(String[] args) {

		SpringApplication.run(ApplicationStart.class, args);

	}

	/**
	 * 配置拦截器 ，设置配置参数
	 * @author lance
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		//registry.addInterceptor(new SQLInjectionInterceptor(filterWords));//防SQL注入
		//registry.addInterceptor(new UserSecurityInterceptor(redisCacheService)).excludePathPatterns(pathPatterns.split(","));// 不过滤拦截
		super.addInterceptors(registry);

	}
}
