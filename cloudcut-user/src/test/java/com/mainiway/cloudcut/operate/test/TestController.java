package com.mainiway.cloudcut.operate.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mainiway.cloudcut.common.beans.JsonEnvEntity;

@RestController
@RequestMapping(value = "/test")
public class TestController {


	@RequestMapping(value = "/")
	public String test(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {

		String result = "WelCome to iDinBao-operate project!";

		return result;
	}

}
