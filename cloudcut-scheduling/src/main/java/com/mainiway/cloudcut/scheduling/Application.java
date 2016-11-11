package com.mainiway.cloudcut.scheduling;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mainiway.cloudcut.scheduling.bean.config.CronConfig;

@ComponentScan(basePackages={"com.mainiway.cloudcut.dao","com.project.cloudcut.db.model","com.mainiway.cloudcut.common","com.mainiway.cloudcut.scheduling"}) //组件扫描
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	private static Log logger = LogFactory.getLog(Application.class);

	private static ApplicationContext context = null;

	@Value("${orderTimeoutJob.cron}")
	protected  String orderTimeoutJob_cron;

	// 加载配置文件
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:conf/applicationContext.xml", "classpath:conf/elastic-jobContext.xml"
	};

	public static void main(String[] args) {

		context = SpringApplication.run(CLASSPATH_RESOURCE_LOCATIONS, args);
		logger.info("-------scheduling initialize.....");

	}


	public static ApplicationContext getContext() {
		return context;

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		CronConfig.cronMap.put("orderTimeoutJob_cron", orderTimeoutJob_cron);

	}
}
