package com.mainiway.cloudcut.user.businesses.test;

import org.springframework.data.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.cloudcut.api.exchange.test.ICUserTest;



@RestController
public class CounsterController {
	@Reference
	ICUserTest icUserTest;
	@RequestMapping("/hello")
	public String hello() {
 		System.out.println("==========" +  icUserTest.hello() );
 		return "Hello: " ;
	}
}
