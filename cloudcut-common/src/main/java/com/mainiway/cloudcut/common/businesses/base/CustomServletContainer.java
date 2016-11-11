
package com.mainiway.cloudcut.common.businesses.base;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {


	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {

		//根据配置重新设定WebServer的docBase目录，因为以内嵌WebServer运行时默认的docBase指向
		//系统临时目录
		//Resource resource = new ClassPathResource("/application.properties");
		try {
			//Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String rootPath = this.getClass().getResource("/").getPath();
			logger.info("*************************rootPath: "+rootPath+"*************************");
			if(rootPath.contains("jar")){
				String docBasetemp = rootPath.substring(rootPath.indexOf(":")+1,rootPath.lastIndexOf("/"));
				String docBase = docBasetemp.substring(0,docBasetemp.lastIndexOf("/"));
				logger.info("*************************docBase: "+docBase+"*************************");
				container.setDocumentRoot(new File(docBase));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
