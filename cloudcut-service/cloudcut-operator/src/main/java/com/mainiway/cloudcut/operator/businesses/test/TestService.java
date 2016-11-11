package com.mainiway.cloudcut.operator.businesses.test;

import com.alibaba.dubbo.config.annotation.Service;
import com.mainiway.cloudcut.api.exchange.test.ISUserTest;
import com.mainiway.cloudcut.api.exchange.test.TestEntity;

@Service
public class TestService implements ISUserTest{
  	public String hello() {
		System.out.println("hello 1");
 		return "hello";
	}

	@Override
	public String hello3(TestEntity entity) {
		System.out.println(entity.getName());
 		return "songdong";
	}


}
