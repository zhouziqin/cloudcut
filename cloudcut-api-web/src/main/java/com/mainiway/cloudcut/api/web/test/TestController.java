package com.mainiway.cloudcut.api.web.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mainiway.cloudcut.api.exchange.test.ICUserTest;
import com.mainiway.cloudcut.api.exchange.test.TestEntity;

@RestController
public class TestController {

	@Reference
	ICUserTest icUserTest;
	@RequestMapping("/test")
	public void test(){
		System.out.println("111111111111");
		System.out.println(icUserTest.hello());
	}
	@RequestMapping("/test2")
	public void test2(){
		TestEntity test = new TestEntity();
		test.setName("123123name");
		System.out.println("111111111111");
		System.out.println(icUserTest.hello3(test));
	}

}
