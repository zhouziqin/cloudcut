package com.mainiway.cloudcut.api.web.test;

 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mainiway.cloudcut.api.exchange.redis.ISRedisService;
import com.mainiway.cloudcut.api.exchange.redis.TestBean;



@RestController
public class RedisTestController {
 
	
	@Reference
	ISRedisService redisCacheService;
 
	@RequestMapping("/redis")
	public void redis() {
		 TestBean tb = new TestBean();
		 tb.setName("测试");
		 System.out.println("test service:"+redisCacheService);
		 redisCacheService.set("key",tb);
		 System.out.println("============");
		 TestBean td= redisCacheService.get("key",tb.getClass());
		 System.out.println(td.getName());
	}
}
