package com.mainiway.cloudcut.api.exchange.test;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
@Service
public class UserTest implements ICUserTest{
@Reference
ISUserTest isUserTest;
	@Override
	public String hello() {
		System.out.println("hello2");
 		return isUserTest.hello();
	}
	@Override
	public String hello3(TestEntity entity) {
 		return isUserTest.hello3(entity);
	} 

}
